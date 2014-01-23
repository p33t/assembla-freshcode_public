To get this application working....
-----------------------------------
- Enable HTTPS in $CATALINA_HOME/conf/server.xml

For JDBC Realm:
- postgres jdbc jar in $CATALINA_HOME/lib
- database connection params set up in 'context.xml'
- setup views...
    -- create views for tomcat to use !!!!!!!!!!!!! BEWARE view owner / permissions
    create or replace view authuser as
    select username, username as password from appuser;

    -- only needed if doing container roles (see context.xml)
    create or replace view authrole as
    select username, rolename from appuser join userrole on appuser.id = userrole.parentid;

    -- test
    select * from authuser;
    select * from authrole;
-- NOTE: the salting of passwords is not supported by tomcat.  Password is the username.

For LDAP Realm:
(TODO)