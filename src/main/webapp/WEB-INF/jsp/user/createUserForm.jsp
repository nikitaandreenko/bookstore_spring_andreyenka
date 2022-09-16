<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create user</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="login">
<ul>
    <li><a href="/">Home</a></li>
    <li><a href=/books/getAll>books</a></li>
    <c:if test="${sessionScope.user == null}">
        <li style="float:right"><a class="active" href="/login">Login</a></li>
        <li style="float:right"><a class="active" href="/users/registration">Registration</a></li>
        <li style="float:right"><a class="active" href="/cart/cart">Cart</a></li>

    </c:if>
    <c:if test="${sessionScope.user != null}">
        <li style="float:right"><a class="active" href="/logout">Logout</a></li>
        <li style="float:right"><a class="active" href="/cart/cart">Cart</a></li>
    </c:if>
    <c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
        <li><a href="/books/create">Add book</a></li>
        <li><a href="/orders/getAll">orders</a></li>
    </c:if>
</ul>
<br><br>
<form class="form" align=center action="/users/create" method="post">
    <fieldset>
        <legend>Registration</legend>
        <label>First name: <input type="text" placeholder="first name" name="firstName"/></label>
        <br><br>
        <label>Last name: <input type="text" placeholder="last name" name="lastName"/></label>
        <br><br>
        <label>Age:<input type="text" placeholder="age" name="age"/></label>
        <br><br>
        <label>Email: <input type="text" placeholder="email" name="email"/></label>
        <br><br>
        <label>Password: <input type="text" placeholder="password" name="password"/></label>
        <br><br>
        <label>Confirm password: <input type="text" placeholder="password" name="confirmPassword"/></label>
        <br><br>
        <label>Role: <select name="role">
            <option>MANAGER</option>
            <option>ADMIN</option>
        </select>
        </label>
        <br><br>
        <label>Life cycle: <select name="lifeCycle">
            <option>active</option>
            <option>not active</option>
        </select>
        </label>
        <br><br>
    </fieldset>
    <input type="submit" value="save"/>
</form>
</body>
</html>
