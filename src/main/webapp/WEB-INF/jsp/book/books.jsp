<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Books</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<ul>
    <li><a href="/">Home</a></li>
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
        <li><a href="/users/getAll">Users</a></li>
        <li><a href="/orders/getAll">Orders</a></li>
    </c:if>
</ul>
<h1>Books</h1>
<div class="d1">
    <form action="/books/getAllByAuthor" method="get">
        <input type="text" name="author" placeholder="enter author">
        <button type="submit">find</button>
    </form>
    <form action="/books/getBookTitle" method="get">
        <input type="text" name="title" placeholder="enter title">
        <button type="submit">find</button>
    </form>
</div>
<br><br>
<table>
    <tr>
        <th>#</th>
        <th>Title</th>
        <th>Author</th>
        <th>Year Publishing</th>
        <th>Availability</th>
        <th>More info</th>
        <th></th>
    </tr>
    <c:forEach items="${books}" var="book" varStatus="counter">
        <tr>
            <td><c:out value="${counter.count}"/></td>
            <td><c:out value="${book.bookName}"/></td>
            <td><c:out value="${book.author}"/></td>
            <td><c:out value="${book.yearPublishing}"/></td>
            <td><c:out value="${book.availability}"/></td>
            <td>
                <form class="form_table" action="/books/${book.id}" method="get">
                    <button>Click me</button>
                </form>
            </td>
            <td>
                <form class="form_table" action="/cart/buy/${book.id}" method="get">
                    <button>Add to cart</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>