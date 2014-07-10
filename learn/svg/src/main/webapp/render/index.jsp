<%
    if ("Download".equals(request.getParameter("command"))) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=svg-download.html");
    } else {
        response.setContentType("text/html;charset=UTF8");
    }
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="viewboxWidth" value="${param.x + 2}"/>
<c:set var="viewboxHeight" value="${param.y + 2}"/>
<c:set var="viewboxRatio" value="${viewboxHeight / viewboxWidth}"/>
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
        var svgRatio = ${viewboxRatio};

        // From http://stackoverflow.com/a/13651455
        if (window.attachEvent) {
            window.attachEvent('onresize', resizeSvg);
        }
        else if (window.addEventListener) {
            window.addEventListener('resize', resizeSvg, true);
        }
        else {
            //The browser does not support Javascript event binding
        }

        function resizeSvg() {
            var height = window.innerWidth * svgRatio;
            var svgs = document.getElementsByTagName('svg');
            // Argh.. for each not allowed: http://stackoverflow.com/a/22754453
            for (var i = 0; i < svgs.length; i++) {
                svgs[i].setAttribute("height", height.toString());
            }
        }
    </script>
</head>
<body>
<c:forEach var="ixPage" begin="0" end="${param.count - 1}" varStatus="pageStatus">
    <div class="non-printable">
        <p id="pageIndex${ixPage}">
            <c:if test="${!pageStatus.first}">
            <a href="#pageIndex${ixPage - 1}">
                </c:if>
                Previous
                <c:if test="${!pageStatus.first}">
            </a>
            </c:if>
            &nbsp;
            <c:if test="${!pageStatus.last}">
            <a href="#pageIndex${ixPage + 1}">
                </c:if>
                Next
                <c:if test="${!pageStatus.last}">
            </a>
            </c:if>
        </p>
    </div>
    <p>Page #${ixPage + 1}</p>
    <svg xmlns="http://www.w3.org/2000/svg" width="100%" viewbox="0 0 ${viewboxWidth} ${viewboxHeight}">
        <rect x="1" y="1" width="${param.x}" height="${param.y}"
              style="fill: #bbb"/>
    </svg>
</c:forEach>
<script type="application/javascript">
    resizeSvg();
</script>
</body>
</html>
