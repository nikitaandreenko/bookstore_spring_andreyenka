<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
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
        <li style="float:right"><a class="active" href="/users/${user.id}" method="get">My profile</a></li>
    </c:if>
    <c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
        <li><a href="/users/create">New user</a></li>
        <li><a href="/books/create">Add book</a></li>
        <li><a href="/orders/getAll">orders</a></li>
    </c:if>
</ul>
<h1>Book</h1>
<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Isbn</th>
        <th>Price, USD</th>
        <th>Pages</th>
        <th>Binding</th>
        <th>Year Publishing</th>
        <th>Language</th>
        <th>availability</th>

    </tr>
    <tr>
        <td><c:out value="${book.bookName}"/></td>
        <td><c:out value="${book.author}"/></td>
        <td><c:out value="${book.isbn}"/></td>
        <td><c:out value="${book.price}"/></td>
        <td><c:out value="${book.pages}"/></td>
        <td><c:out value="${book.binding}"/></td>
        <td><c:out value="${book.yearPublishing}"/></td>
        <td><c:out value="${book.language}"/></td>
        <td><c:out value="${book.availability}"/></td>
    </tr>
</table>
<div>
    <c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
    <form action="/books/update/${book.id}" method="get" target="_blank">
        <button>update</button>
    </form>
    <form action="/books/delete/${book.id}" method="post" target="_blank">
        <button>delete</button>
    </form>
</div>
</c:if>
</body>
</html>
