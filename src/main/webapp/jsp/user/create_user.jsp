<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form align=center action="controller" method="post">
    <input type="hidden" name="command" value="create_user">
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
    <input type="submit" value="add"/>
</form>
</body>
</html>
