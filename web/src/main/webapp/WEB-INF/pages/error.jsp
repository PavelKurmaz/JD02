<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%@ include file="/resources/include/head.htm" %>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>Error Page</title>
</head>
<body>
<h2>Application Error</h2>

<h3>Debug Info:</h3>
Requested URL=${url}<br>
<strong>Exception Stacktrace</strong>
<c:forEach items="${exception.stackTrace}" var="item">
    ${item}
</c:forEach>

</body>
</html>


