<%@ page contentType="text/html;charset=UTF-8" language="java" %><!doctype html>
<%--
    The DOCTYPE declaration above will set the
    browser's rendering engine into
    "Standards Mode". Replacing this declaration
    with a "Quirks Mode" doctype may lead to some
    differences in layout.
--%>
<%!
private String getGwtModule() {
    ServletConfig config = getServletConfig();
    if (config == null) throw new RuntimeException("No Servlet Config");
    String gwtModule = config.getServletContext().getInitParameter("gwtModule");
    if (gwtModule == null) throw new RuntimeException("No gwtModule");
    return gwtModule;
}
%>

<%
    String gwtModule = getGwtModule();
%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="-1">

    <!-- For GXT -->
    <link rel="stylesheet" type="text/css" href="<%=gwtModule%>/reset.css"/>
    <link type="text/css" rel="stylesheet" href="App.css">

    <title>GWTP Learning Project</title>


    <%-- This script loads your compiled module. --%>
    <%-- If you add any GWT meta tags, they must --%>
    <%-- be added before this line.              --%>
    <script type="text/javascript" language="javascript" src="<%=gwtModule%>/<%=gwtModule%>.nocache.js"></script>
</head>

<body>

<div id="loading"
     style="display: block; position: absolute; top: 30%; left: 50%; text-align: center;">
    Loading...<br/>
</div>

<!-- OPTIONAL: include this if you want history support -->
<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
        style="position:absolute;width:0;height:0;border:0"></iframe>

<!-- RECOMMENDED if your web app will not function without JavaScript enabled -->
<noscript>
    <div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
    </div>
</noscript>

</body>
</html>
