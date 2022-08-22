<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <title>Create user</title>
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
    <input type="submit" value="save"/>
</form>
</body>
</html>
