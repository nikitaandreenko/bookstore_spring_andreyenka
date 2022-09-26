<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Books</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
        <button type="submit">search</button>
    </form>
    <form action="/books/getBookTitle" method="get">
        <input type="text" name="title" placeholder="enter title">
        <button type="submit">search</button>
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
            <td><c:out value="${book.id}"/></td>
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
<br>
<div>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="/books/getAll?page=${currentPage - 1}">Previous</a></li>
            <li class="page-item"><a class="page-link" href="/books/getAll?page=1">1</a></li>
            <li class="page-item"><a class="page-link" href="/books/getAll?page=2">2</a></li>
            <li class="page-item"><a class="page-link" href="/books/getAll?page=3">3</a></li>
            <li class="page-item"><a class="page-link" href="/books/getAll?page=4">4</a></li>
            <li class="page-item"><a class="page-link" href="/books/getAll?page=5">5</a></li>
            <li class="page-item"><a class="page-link" href="/books/getAll?page=6">6</a></li>
            <li class="page-item"><a class="page-link" href="/books/getAll?page=${currentPage +1}">Next</a></li>
        </ul>
    </nav>
</div>
</body>
</html>