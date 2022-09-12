<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="login">
<ul>
    <li><a href="/">Home</a></li>
    <li><a href=/books/getAll>books</a></li>
    <c:if test="${sessionScope.user == null}">
        <li style="float:right"><a class="active" href="/users/registration">Registration</a></li>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <li style="float:right"><a class="active" href="/logout">Logout</a></li>
    </c:if>
    <c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
        <li style="float:right"><a class="active" href="/users/create">New user</a></li>
        <li><a href="/books/create">Add book</a></li>
        <li><a href="/orders/getAll">orders</a></li>
    </c:if>
</ul>
<br><br>
<form class="form" align="center" action="/login" method="post">
    <fieldset>
        <legend>Login</legend>
    <label>Email:<input type="text" placeholder= "email" name="email"/></label>
    <br><br>
    <label>Password:<input type="password" placeholder= "password" name="password"/></label>
    <br><br>
    <input type="submit" value="continue"/>
        </fieldset>
</form>
</body>
</html>
