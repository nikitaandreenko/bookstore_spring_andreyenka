<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Update user</title>
    <link href="../../css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="user">
<c:if test="${message!=null}">
    <h3><em> ${message}</em></h3>
</c:if>
<form align=center action="${pageContext.request.contextPath}/users/update/${user.id}" method="post">
    First name: <input type="text" name="firstName" value="${user.firstName}"/>
    <br><br>
    Last name: <input type="text" name="lastName" value="${user.lastName}"/>
    <br><br>
    Age: <input type="text" name="age" value="${user.age}"/>
    <br><br>
    Email: <input type="text" name="email" value="${user.email}"/>
    <br><br>
    Role: <select type="text" name="role" value="${user.role}">
    <option>USER</option>
    <option>MANAGER</option>
    <option>ADMIN</option>
</select>
    <br><br>
    <input type="submit" value="save"/>
</form>
</body>
</html>
