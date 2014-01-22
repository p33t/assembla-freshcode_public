<%@ page import="java.security.Principal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
    Principal principal = request.getUserPrincipal();
    String username = principal == null? "(none)": principal.getName();
%><!doctype html>
<html>
<body>
<h2>Hello!</h2>

<p>Your username is: <%=username%>
</p>
</body>
</html>
