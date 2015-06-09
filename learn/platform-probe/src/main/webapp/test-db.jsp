<%@ page import="pkg.DbTest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DB Test</title>
</head>
<body>
<p><a href="./">Home</a></p>
<pre>
<%=DbTest.run()%>
</pre>
</body>
</html>
