<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
    <link href="jsp/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="user">
<c:if test="${requestScope.message!=null}">
    <h3><em> ${requestScope.message}</em></h3>
</c:if>
<form align=center action="controller" method="post">
    <input type="hidden" name="command" value="update_user">
    <input type="hidden" name="id" value="${requestScope.user.id}">
    <br><br>
    First name: <input type="text" name="firstName" value="${requestScope.user.firstName}"/>
    <br><br>
    Last name: <input type="text" name="lastName" value="${requestScope.user.lastName}"/>
    <br><br>
    Age: <input type="text" name="age" value="${requestScope.user.age}"/>
    <br><br>
    Email: <input type="text" name="email" value="${requestScope.user.email}"/>
    <br><br>
    Role: <select name="role" value="${requestScope.user.role}">
    <option>USER</option>
    <option>MANAGER</option>
    <option>ADMIN</option>
</select>
    <br><br>
    <input type="submit" value="save"/>
</form>
</body>
</html>