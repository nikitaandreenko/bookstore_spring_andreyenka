<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete user</title>
</head>
<body>
<h1 align="center" style="color:#ff0000">Delete</h1>
<c:if test="${requestScope.message!=null}">
    <h3 align="center" style="color:#0000ff"><em> ${requestScope.message}</em></h3>
</c:if>
<c:if test="${requestScope.message2!=null}">
    <h3 align="center" style="color:#008080"><em>${requestScope.message2}</em></h3>
</c:if>
</body>
</html>
