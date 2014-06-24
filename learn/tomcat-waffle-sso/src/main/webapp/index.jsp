<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.security.Principal" %>
<%
    Principal p = request.getUserPrincipal();
    String user = "<none>";
    boolean hasRole = false;
    if (p != null) {
        user = p.getName();
        hasRole = request.isUserInRole("BUILTIN\\Users");
    }
%>
<html>
<body>
<h2>Hello World!</h2>

<p>
    User: <%=user%><br/>
    BUILTIN\Users: <%=hasRole%>
</p>
<a href="logout.jsp">Logout</a>
</body>
</html>
