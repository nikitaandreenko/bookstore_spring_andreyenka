<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create user</title>
    <link href="../css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="user">
<c:if test="${message!=null}">
    <h3><em> ${message}</em></h3>
</c:if>
<form align=center action="${pageContext.request.contextPath}/users/create" method="post">
    First name: <input type="text" name="firstName"/>
    <br><br>
    Last name: <input type="text" name="lastName"/>
    <br><br>
    Age: <input type="text" name="age"/>
    <br><br>
    Email: <input type="text" name="email"/>
    <br><br>
    Role: <select name="role">
    <option>USER</option>
    <option>MANAGER</option>
    <option>ADMIN</option>
</select>
    <br><br>
    <input type="submit" value="save"/>
</form>
</body>
</html>
