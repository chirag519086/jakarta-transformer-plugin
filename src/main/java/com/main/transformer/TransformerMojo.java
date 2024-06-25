package com.main.transformer;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.tomcat.jakartaee.Migration;

/*
 * This plugin transforms javax classes to jakarta classes in spring boot application package using jakartaee-migration library.
 * It scans whole input jar recursively, transforms classes of all jars based on default rules and put them back to original jar.
 * Few jars packaged under BOOT/lib are excluded from transformation. 
 */
@Mojo(name = "jakarta-transform", defaultPhase = LifecyclePhase.PACKAGE)
public class TransformerMojo extends AbstractMojo {
	
	@Parameter(property = "fileLocation", required = true)
	private String fileLocation;

	public void execute() throws MojoExecutionException {
		File inputFile = new File(fileLocation);
		File outputFile = new File(fileLocation);
		Migration migrate = new Migration();
		migrate.setSource(inputFile);
		migrate.setDestination(outputFile);
		migrate.setMatchExcludesAgainstPathName(true);

		// Adding jars in exclude list
		migrate.addExclude("*BOOT-INF/lib/jakarta*.jar");
		migrate.addExclude("*BOOT-INF/lib/tomcat*.jar");
		
		try {
			migrate.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
