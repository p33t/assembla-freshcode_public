<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pkg.AppUtil" %>
<%@ page import="waffle.apache.GenericWindowsPrincipal" %>
<%@ page import="java.security.Principal" %>
<%
    Principal p = request.getUserPrincipal();
    String roles = "";
    if (p != null) {
        for (String r : AppUtil.ROLES) {
            roles += "<br/>" + r + "=" + request.isUserInRole(r);
        }
    }
%>
<html>
<body>
<h2>Hello World!</h2>

<p>
    <br/>Request Class: <%=request.getClass()%>
    <br/>Principal info: <%=audit(p)%>
    <br/><%=roles%>
</p>

<p>
    <a href="role-check.jsp">Check for more roles?</a>
</p>

<p>
    <a href="logout.jsp">Logout</a>
</p>
</body>
</html>
<%!
    private String audit(Principal p) {
        String s = "";
        if (p == null) return "<No Principal>";
        s += "Class: " + p.getClass();
        s += eol(1) + "Name: " + p.getName();
        if (p instanceof GenericWindowsPrincipal) {
            GenericWindowsPrincipal gwp = (GenericWindowsPrincipal) p;
            s += eol(1) + "Sid: " + gwp.getSidString();
            s += eol(1) + "Groups:";
            for (String group : gwp.getGroups().keySet()) s += eol(2) + group;
        }
        return s;
    }

    private String eol(int indents) {
        String s = "<br/>";
        for (int i = 0; i < indents; i++) s += "&nbsp;";
        return s;
    }
//    private String audit(Principal p) {
//        StringBuilder b = new StringBuilder();
//        if (p == null) return "No Principal";
//        b.append("Class: ").append(p.getClass());
//        eol(b, 1).append("name: ").append(p.getName());
//        if (p instanceof GenericWindowsPrincipal) {
//            GenericWindowsPrincipal gwp = (GenericWindowsPrincipal) p;
//            eol(b, 1).append("sidString: ").append(gwp.getSidString());
////            eol(b, 1).append("rolesString: ").append(g.getRolesString());
//            eol(b, 1).append("Groups:");
//            for (String group : gwp.getGroups().keySet()) {
//                eol(b, 2).append(group);
//            }
//        }
//        return b.toString();
//    }
//
//    private StringBuilder eol(StringBuilder b, int indents) {
//        b.append("<br/>");
//        for (int i = 0; i < indents; i++) b.append("&nbsp;");
//        return b;
//    }
%>