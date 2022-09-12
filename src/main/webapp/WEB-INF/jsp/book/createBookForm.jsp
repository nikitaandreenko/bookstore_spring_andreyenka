<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create book</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="login">
<ul>
    <li><a href="/">Home</a></li>
    <li><a href=/books/getAll>books</a></li>
    <c:if test="${sessionScope.user == null}">
        <li style="float:right" ><a  class="active" href="/login">Login</a></li>
        <<li style="float:right"><a class="active" href="/users/registration">Registration</a></li>
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
<form class="form" align=center action="/books/create" method="post">
    <fieldset>
        <legend>New book</legend>
    <label>Name: <input placeholder="title" type="text" name="bookName"/></label>
    <br><br>
    <label>Author: <input placeholder="author" type="text" name="author"/></label>
    <br><br>
    <label>Isbn: <input placeholder="isbn" type="text" name="isbn"/></label>
    <br><br>
    <label>Price: <input placeholder="price" type="text" name="price"/></label>
    <br><br>
    <label>Pages: <input placeholder="pages" type="text" name="pages"/></label>
    <br><br>
    <label>Binding: <input placeholder="binding" type="text" name="binding"/></label>
    <br><br>
    <label>Year_publishing: <input type="text" name="yearPublishing"/></label>
    <br><br>
    <label>Language:
        <select name="language">
            <option>ENGLISH</option>
            <option>RUSSIAN</option>
            <option>SPANISH</option>
            <option>FRENCH</option>
            <option>DEUTSCH</option>
            <option>ARABIC</option>
            <option>CHINESE</option>
            <option>JAPANESE</option>
        </select>
    </label>
    <br><br>
    <label>
        Availability:
        <select name = availability">
            <option>in stock</option>
            <option>out of stock</option>
        </select>
    </label>
    <br><br>
        </fieldset>
    <input type="submit" value="save"/>
</form>
</body>
</html>
