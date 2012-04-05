Learning project for GWT using Maven.

Initial command...
mvn archetype:generate -DarchetypeRepository=repo1.maven.org -DarchetypeGroupId=org.codehaus.mojo \
  -DarchetypeArtifactId=gwt-maven-plugin -DarchetypeVersion=2.4.0 -DgroupId=biz.freshcode.learn.gwt \
  -DartifactId=learn-gwt

  entered 'm1' when prompted for a module.
  !!!! But should have been captialised like M1 or Mod1

Tips:
 - Bootstrap dev with call to maven plugin goal, say, 'package'.
 -- Will trigger generation of src files from gwt:generateAsync, gwt:i18n (?)
 - Create IDEA project from 'external model'
 - Needed this in pom.xml to get Tomcat working in IDEA...
                        </goals>
                        <configuration>
                            <!-- Needed to prevent 'OutOfMemoryError: GC overhead limit exceeded' -->
                            <extraJvmArgs>-Xmx512m -Xms256m</extraJvmArgs>
                        </configuration>
                    </execution>
 - Need Plugin for browser for Development Mode.. https://dl-ssl.google.com/gwt/plugins/chrome/gwt-dev-plugin.crx
 -- Or use Getting Started guide to prime system: http://code.google.com/webtoolkit/gettingstarted.html
 - Use get:debug to launch in debug mode
 -- Will await debugger to attach on 8000 before completing startup
 -- Intellij IDEA: Use the 'Remote' run configuration and specify the 8000 port to connect to
 - Added GWT_HOME environment variable to make life easier when loading up samples
 - GXT 3.0.0-beta4: Repository indicated in doco is wrong.  Used https://oss.sonatype.org/content/repositories/releases
 - SmartGXT is a wrapper for a JS library and is difficult to debug

 Notes
 -----
 - Use layout panels for non-document-like outer structure of UI.  Otherwise use html or widgets
 - Use ResizeComposite (not Composite) to auto propagate resize events properly
 - Opt for XxxLayoutPanel (not XxxPanel).  They work properly with 'standards mode'
 -- Dock, Vertical, Split, Stack Panels are discouraged
 -- Horizontal can be used with caveats
- Autobeans for reducing boilerplate that arises in Java Beans
-- Interface with get/set declared
-- Use AutoBeanFactory to instantiate (Or AutoBeanFactorySource outside of GWT code (?)
-- Streamlined JSON serial/deserial
-- 'Category' used to implement additional non-property methods
