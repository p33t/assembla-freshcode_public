<%@ page import="java.io.File" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><a href="./confidential">Confidential area</a></p>
        <p><a href="./test-db.jsp">DB Test</a></p>
    <p>
        <%

            // working out getRealPath()
            String real = application.getRealPath("/");
            out.print("getRealPath() = " + real);

            String appHome = application.getInitParameter("app.home");
            out.println("<br/>getInitParameter('app.home') = " + appHome);

            String tempDir = System.getProperty("java.io.tmpdir");
            out.println("<br/>java.io.tmpdir = " + tempDir);

            String[] files = new File(tempDir).list();
            for (String file: files) {
                out.println("<br/>&nbsp;" + file);
            }
        %>
    </p>


    </body>
</html>
