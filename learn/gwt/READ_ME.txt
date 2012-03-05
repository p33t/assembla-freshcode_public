Learning project for GWT using Maven.

Initial command...
mvn archetype:generate -DarchetypeRepository=repo1.maven.org -DarchetypeGroupId=org.codehaus.mojo \
  -DarchetypeArtifactId=gwt-maven-plugin -DarchetypeVersion=2.4.0 -DgroupId=biz.freshcode.learn.gwt \
  -DartifactId=learn-gwt

  entered 'm1' when prompted for a module.

Tips:
 - Bootstrap dev with, say, 'package'.  Will trigger generation of src files from gwt:generateAsync, gwt:i18n (?)
 - Create IDEA project from 'external model'
 - Needed this in pom.xml to get Tomcat working in IDEA...
                        </goals>
                        <configuration>
                            <!-- Needed to prevent 'OutOfMemoryError: GC overhead limit exceeded' -->
                            <extraJvmArgs>-Xmx512m -Xms256m</extraJvmArgs>
                        </configuration>
                    </execution>


