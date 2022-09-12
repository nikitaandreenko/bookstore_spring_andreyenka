<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update book</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="login">
<ul>
    <li><a href="/">Home</a></li>
    <li><a href=/books/getAll>books</a></li>
    <c:if test="${sessionScope.user == null}">
        <li style="float:right" ><a  class="active" href="/login">Login</a></li>
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
<form class="form" align=center action="/books/update/${book.id}" method="post">
    <fieldset>
        <legend>Update book</legend>
    <label>Name: <input type="text" placeholder="title" name="bookName" value="${book.bookName}"/></label>
    <br><br>
    <label>Author: <input type="text" placeholder="author" name="author" value="${book.author}"/></label>
    <br><br>
    <label>Isbn: <input type="text" placeholder="isbn" name="isbn" value="${book.isbn}"/></label>
    <br><br>
    <label>Price: <input type="text" placeholder="price" name="price" value="${book.price}"/></label>
    <br><br>
    <label>Pages: <input type="text" placeholder="pages" name="pages" value="${book.pages}"/></label>
    <br><br>
    <label>Binding: <input type="text" placeholder="binding" name="binding" value="${book.binding}"/></label>
    <br><br>
    <label>Year_publishing: <input placeholder="year publishing" type="text" name="yearPublishing" value="${book.yearPublishing}"/></label>
    <br><br>
    <label>Language: <select placeholder ="language" name="language" value="${book.language}">
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
