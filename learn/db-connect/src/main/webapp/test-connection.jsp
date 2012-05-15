<%@ page import="pkg.ConnectionOp" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DatabaseMetaData" %>
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
    String examineConnection() {
        final String[] arr = {null};
        pkg.Util.withConnection(new ConnectionOp() {
            @Override
            public void run(Connection conn) throws Exception {
                DatabaseMetaData meta = conn.getMetaData();
                arr[0] = "url=" + meta.getURL() + " - conn=" + conn + " - conn class=" + conn.getClass();

            }
        });
        return arr[0];
    }
%>
