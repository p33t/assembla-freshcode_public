<html>
<head>
    <title>SVG Demo</title>
</head>
<body>
<a href="render">Render</a>

<form target="output" method="get" action="render">
    <label>Width
        <input name="x" type="number" min="1" value="1" max="100"/>
    </label>
    by
    <label>
        Height
        <input name="y" type="number" min="1" value="2" max="100"/>
    </label>
    <br/>
    <label>
        Page Count
        <input name="count" type="number" min="1" value="1" max="5"/>
    </label>
    <label>
        Download
        <input name="download" type="checkbox" value="0"/>
    </label>
    <input type="submit"/>
</form>
<iframe name="output" width="100%" height="80%">
</iframe>
</body>
</html>
