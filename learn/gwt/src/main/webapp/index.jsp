<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", -1);
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

        function download() {
            var debug = {hello: "world"};
            var blob = new Blob([JSON.stringify(debug, null, 2)], {type: 'application/json'});

            var a = document.createElement("a");
            a.style = "display: none";
            document.body.appendChild(a);
            //Create a DOMString representing the blob
            //and point the link element towards it
            var url = window.URL.createObjectURL(blob);
            a.href = url;
            a.download = 'myFile.json';
            //programatically click the link to trigger the download
            a.click();
            //release the reference to the file by revoking the Object URL
            window.URL.revokeObjectURL(url);
        }
    </script>
</head>
<body>
<p>Select desired module...</p>

<p>Mod1 does not work. Transfer necessary elements to Mod2 if they are needed.</p>
<p><a href="Mod2.html">Mod2</a> (Login with user 'bruce', password 'bruce')</p>

<p><input type="button" value="Dates" onclick="showDates();return false;"/></p>

<p><input type="button" value="Simulate Download" onclick="download(); return false;"/></p>

</body>
</html>