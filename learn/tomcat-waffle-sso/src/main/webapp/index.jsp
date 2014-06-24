<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pkg.AppUtil" %>
<%@ page import="java.security.Principal" %>
<%
    Principal p = request.getUserPrincipal();
    String user = "<none>";
    String roles = "";
    if (p != null) {
        user = p.getName();
        for (String r : AppUtil.ROLES) {
            roles += "<br/>" + r + "=" + request.isUserInRole(r);
        }
    }
%>
<html>
<body>
<h2>Hello World!</h2>

<p>
    User: <%=user%><br/>
    <%=roles%>
</p>
<a href="logout.jsp">Logout</a>
</body>
</html>
