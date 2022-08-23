<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<h1>Delete</h1>
<c:if test="${requestScope.message!=null}">
    <h3><em> ${requestScope.message}</em></h3>
</c:if>
<c:if test="${requestScope.message2!=null}">
    <h3><em>${requestScope.message2}</em></h3>
</c:if>
</body>
</html>
