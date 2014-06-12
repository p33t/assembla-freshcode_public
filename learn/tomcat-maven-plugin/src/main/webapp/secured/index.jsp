<%@ page import="java.security.Principal" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<String> ROLE_LIST = Arrays.asList("authentic", "privilege");
    String username = null;
    String roles = "";
    Principal p = request.getUserPrincipal();
    if (p != null) {
        username = p.getName();
        for (String role : ROLE_LIST) {
            roles += "<br/>&nbsp;" + role + ": " + request.isUserInRole(role);
        }
    }
%>
<html>
<head>
    <title>Secured Page</title>
</head>
<body>
<p>This page should have required authentication.</p>

<p>
    Current user: <%=username%><br/>
    Current roles: <%=roles%>
</p>

<p><a href="../logout.jsp">Logout</a></p>
</body>
</html>
