<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DatabaseMetaData" %>
<%@ page import="java.sql.SQLException" %>
<%@include file="/WEB-INF/includes/header.jsp" %>
<html>
<head><title>Connection Test</title></head>
<body>
<p>
    Connection status: <%=examineConnection()%>
</p>
</body>
</html>

<%!
    String examineConnection() throws NamingException, SQLException {
        InitialContext cxt = new InitialContext();
        if (cxt == null) {
            return "No initial context.";
        }

        DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/appDb");

        if (ds == null) {
            return "No datasource found.";
        }

        Connection conn = null;
        try {
            conn = ds.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            return "url=" + meta.getURL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) conn.close();
        }
    }
%>
