<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>
<%@ page contentType="text/javascript;charset=UTF-8" language="java" %>
var clientStartTime = new Date().getTime();
var serverStartTime = <%=System.currentTimeMillis()%>;
// add client time to get rough server time
var serverTimeDiff = serverStartTime - clientStartTime;
// Adjust this with Maven Runner parameter (Intellij)
var serverTimeZone = '<%=System.getProperty("user.timezone")%>';
var desiredTimeZone = 'GMT';
