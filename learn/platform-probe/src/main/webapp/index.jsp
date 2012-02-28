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

    <p>
        <%

            // working out getRealPath()
            String real = application.getRealPath("/");
            out.print("getRealPath() = " + real);

            String appHome = application.getInitParameter("app.home");
            out.println("<br/>getInitParameter('app.home') = " + appHome);
        %>
    </p>


    </body>
</html>
