<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <%--
    TODO: Trying moving this to WEB-INF... much nicer there.
    --%>
    <form method="POST" name="loginform" action="<%=contextPath%>?j_security_check">
    <table style="vertical-align: middle;">
        <tr>
            <td>Username:</td>
            <td><input type="text" name="j_username" /></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="j_password" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Login" /></td>
        </tr>
    </table>
    </form>
    <hr>
    <form method="POST" name="loginform" action="<%=contextPath%>?j_negotiate_check">
        <input type="submit" name="loginbutton" value="Login (Negotiate)" />
    </form>
</body>
</html>
