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

