<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Rendering </title>
    <style type="text/css">
        <!--
        @media print {
            .non-printable {
                display: none;
            }
        }

        body {
            background: #eee;
            margin: 0
        }

        svg {
            background: #fff;
        }

        -->
    </style>
    <script type="application/javascript">
        var ratio = 2;

        // From http://stackoverflow.com/a/13651455
        if(window.attachEvent) {
            window.attachEvent('onresize', resizeSvg);
        }
        else if(window.addEventListener) {
            window.addEventListener('resize', resizeSvg, true);
        }
        else {
            //The browser does not support Javascript event binding
        }

        function resizeSvg() {
            var height = window.innerWidth * ratio;
            var svg  = document.getElementsByTagName('svg')[0];
            svg.setAttribute("height", height.toString());
        }
    </script>
</head>
<body>
<div class="non-printable">
    <p>
        This will not be printed. TODO: Page nav links.
    </p>
</div>
<svg xmlns="http://www.w3.org/2000/svg" viewbox="0 0 120 220">
    <rect x="10" y="10" height="200" width="100"
          style="stroke:#ff0000; fill: #bbb"/>
</svg>
<script type="application/javascript">
    resizeSvg();
</script>
</body>
</html>
