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

<p>Mod1 does not work.  Transfer necessary elements to Mod2 if they are needed.</p>
<p><a href="Mod2.html">Mod2</a></p>
<input type="button" value="Dates" onclick="showDates();return false;"/>
</body>
</html>