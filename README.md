# jakarta-transformer-plugin


## Name
jakarta-transformer-plugin

## Description
This project creates a custom maven plugin which performes javax to jakarta transformation on given input jar recursively.

## Usage
This plugin is created to apply jakarta transformation to spring boot application during package phase.
Plugin accepts jakarta-transform as goal and fileLocation parameter for input jar location.
```xml
<build>
<plugins>
       <plugin>
            <groupId>com.main.transformer</groupId>
            <artifactId>jakarta-transformer-plugin</artifactId>
            <version>1.0.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>jakarta-transform</goal>
                    </goals>
                    <configuration>
                        <fileLocation>\dir\input.jar</fileLocation>
                    </configuration>
                </execution>
            </executions>
        </plugin>
</plugins>
</build>
