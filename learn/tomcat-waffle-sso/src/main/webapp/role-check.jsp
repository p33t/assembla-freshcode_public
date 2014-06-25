<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String role = request.getParameter("role");
    boolean hasRole = request.isUserInRole(role);
%>
<html>
<head>
    <title>Role Check</title>
</head>
<body>
<%
    if (role != null) {
%>
<p>
    Role '<%=role%>' is <%=hasRole ? "" : "NOT"%> present.
</p>
<%
    }
%>

<form action="role-check.jsp" method="POST">
    <label title="Role to test">
        <input name="role" type="text" value="<%=role%>"/>
    </label>
    <input type="submit"/>
</form>

<p><a href="<%=request.getContextPath()%>">Home</a></p>
</body>
</html>
