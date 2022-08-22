<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<style>
    * {
        box-sizing: border-box;
    }
    input[type=text], select, textarea {
        width: 100%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        resize: vertical;
    }
    input[type=submit] {
        background-color: #04AA6D;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
</style>
<body>
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
