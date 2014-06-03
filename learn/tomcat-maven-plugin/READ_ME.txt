Example use of tomcat maven plugin with:
- JNDI Datasource
- Form-based authentication
- Custom security realm.

Steps:
- Install the 'tomcat-parent'.
- Install the 'tomcat-salted-passwords' module
- Run goal 'tomcat7:run-war' and note log message connecting to JNDI supplied datasource.
- Open a browser to 'localhost:8080' and confirm working on HTTPS.
- Navigate to secured page if desired.
