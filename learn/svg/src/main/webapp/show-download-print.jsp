<html>
<head>
    <title>SVG Show / Download Demo</title>
</head>
<body>
<p>
    This page demonstrates the rendering of SVG in an HTML document with the following features:
</p>
<ul>
    <li>
        Width dominant auto resizing
    </li>
    <li>
        Download vs Show using same content
    </li>
    <li>
        Suppression of browser-only elements during print
    </li>
    <li>
        Addition of page breaks during print
    </li>
</ul>

<form target="output" method="get" action="render">
    <label>Width
        <input name="x" type="number" min="1" value="10" max="100"/>
    </label>
    by
    <label>
        Height
        <input name="y" type="number" min="1" value="8" max="100"/>
    </label>
    <br/>
    <label>
        Page Count
        <input name="count" type="number" min="1" value="1" max="5"/>
    </label>
    <input type="submit" name="command" value="Show"/>
    <input type="submit" name="command" value="Download"/>
</form>
<iframe name="output" width="100%" height="70%">
</iframe>
</body>
</html>
