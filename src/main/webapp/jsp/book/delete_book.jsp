<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Delete book</title>
  <link href="jsp/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Delete</h1>
<c:if test="${requestScope.message!=null}">
  <h3><em> ${requestScope.message}</em></h3>
</c:if>
<c:if test="${requestScope.messageDelete!=null}">
  <h3><em>${requestScope.messageDelete}</em></h3>
</c:if>
</body>
</html>