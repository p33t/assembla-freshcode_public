This project illustrates a Maven GWT project.

Any JNDI requirement prevents the use of regular Dev Mode because embedded Jetty is not very configurable.
SuperDevMode requirement removes the need to configure Jetty container directly so this project uses Tomcat.
For a Maven Tomcat (non-GWT) app that uses HTTPS and JNDI see:
https://subversion.assembla.com/svn/freshcode_public/learn/tomcat-maven-plugin

Known HTTPS Issue
-----------------
The only known issue is HTTPS cannot be used when using SuperDevMode.
This project does not use HTTPS.
Mangling the 'url-pattern' in a would-be 'web.xml' is an easy way to skip HTTPS.
Note that packaging and running an application on it's own (mvn tomcat7:run-war) would work with HTTPS.

Steps:
------
mvn clean install -Dgwt.draftCompile -DskipTests

mvn gwt:run-codeserver
- Navigate as directed
- Set up 'Dev Mode On' and 'Dev Mode Off' bookmarklets
- Open a 2nd console

mvn tomcat7:run-war -Dgwt.compiler.skip -DskipTests
- Repeat for serverside changes

Restart code server if 'shared' classes change

