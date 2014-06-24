<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.security.Principal" %>
<%
    Principal p = request.getUserPrincipal();
    String user = p == null ? "<none>" : p.getName();
%>
<html>
<body>
<h2>Hello World!</h2>

<p>
    User: <%=user%>
</p>
<a href="logout.jsp">Logout</a>
</body>
</html>
