<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%><!doctype html>
<html>
<head>
  <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
  <META HTTP-EQUIV="Expires" CONTENT="-1">
  <title>Landing Page</title>
  <script type="text/javascript" src="appInfo.jsp"></script>
  <script type="text/javascript">
      function showDates() {
          var s = "";
          s += "Client time: " + new Date(clientStartTime);
          s += "\nServer time: " + new Date(serverStartTime);
          s += "\nServer timezone: " + serverTimeZone;
          alert(s);
      }
  </script>
</head>
<body>
<p>Select desired module...</p>

<p><a href="Mod1.html">Mod1</a></p>
<input type="button" value="Dates" onclick="showDates();return false;"/>
</body>
</html>