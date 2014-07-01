<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="viewportWidth" value="${param.x + 2}"/>
<c:set var="viewportHeight" value="${param.y + 2}"/>
<c:set var="viewportRatio" value="${viewportHeight / viewportWidth}"/>
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
        var svgRatio = ${viewportRatio};

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
            var height = window.innerWidth * svgRatio;
            var svg  = document.getElementsByTagName('svg')[0];
            svg.setAttribute("height", height.toString());
        }
    </script>
</head>
<body>
<div class="non-printable">
    <p>
        This will not be printed.
    </p>
</div>
<svg xmlns="http://www.w3.org/2000/svg" viewbox="0 0 ${viewportWidth} ${viewportHeight}">
    <rect x="1" y="1" width="${param.x}" height="${param.y}"
          style="fill: #bbb"/>
</svg>
<script type="application/javascript">
    resizeSvg();
</script>
</body>
</html>
