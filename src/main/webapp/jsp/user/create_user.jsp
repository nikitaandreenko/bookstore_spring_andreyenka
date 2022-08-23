<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user</title>
    <link href="jsp/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="user">
<c:if test="${requestScope.message!=null}">
    <h3><em> ${requestScope.message}</em></h3>
</c:if>
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
    <input type="submit" value="save"/>
</form>
</body>
</html>
