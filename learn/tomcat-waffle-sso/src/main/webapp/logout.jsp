<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    HttpSession sessIfAny = request.getSession(false);
    if (sessIfAny != null) sessIfAny.invalidate();
%>
<head>
    <title>Logout</title>
</head>
<body>
  <p>You are now logged out.</p>
  <p><a href="<%=request.getContextPath()%>">Login</a></p>
</body>
</html>
