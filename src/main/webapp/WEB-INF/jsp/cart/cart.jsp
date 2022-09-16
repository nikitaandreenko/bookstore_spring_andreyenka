<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cart Page</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<ul>
    <li><a href=/books/getAll>Books</a></li>
    <c:if test="${sessionScope.user == null}">
        <li style="float:right" ><a  class="active" href="/login">Login</a></li>
        <li style="float:right"><a class="active" href="/users/registration">Registration</a></li>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <li style="float:right"><a class="active" href="/logout">Logout</a></li>
        <li style="float:right"><a class="active" href="/cart/index">Cart</a></li>
        <li style="float:right"><a class="active" href="/users/${user.id}" method="get">My profile</a></li>
    </c:if>
    <c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
        <li><a href="/users/create">New user</a></li>
        <li><a href="/books/create">Add book</a></li>
        <li><a href="/orders/getAll">orders</a></li>
        <li><a href="/users/getAll">users</a></li>
    </c:if>
</ul>
<br><br>
<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Isbn</th>
        <th>Quantity Book</th>
        <th>Price, USD</th>
        <th>Pages</th>
        <th>Binding</th>
        <th>Year Publishing</th>
        <th>Language</th>
        <th>Availability</th>
        <th>Remove</th>
    </tr>
    <c:set var="total" value="0"></c:set>
    <c:forEach var="item" varStatus="i" items="${sessionScope.cart}">
        <c:set var="total"
               value="${total + item.bookDto.price * item.quantity }"></c:set>
        <tr>
            <td><c:out value="${item.bookDto.bookName}"/></td>
            <td><c:out value="${item.bookDto.author}"/></td>
            <td><c:out value="${item.bookDto.isbn}"/></td>
            <td><c:out value="${item.quantity}"/></td>
            <td><c:out value="${item.bookDto.price}"/></td>
            <td><c:out value="${item.bookDto.pages}"/></td>
            <td><c:out value="${item.bookDto.binding}"/></td>
            <td><c:out value="${item.bookDto.yearPublishing}"/></td>
            <td><c:out value="${item.bookDto.language}"/></td>
            <td><c:out value="${item.bookDto.availability}"/></td>
            <td><form action="/cart/remove/${i.index}" method="post"><button>remove</button></form></td>
        </tr>
    </c:forEach>
</table>
<table>
   <tr>
       <th>Total</th>
       <th>Checkout</th>
   </tr>
    <tr>
        <td>${total}</td>
        <td><form action="/orders/create" method="post"><button>checkout</button></form></td>
    </tr>
</table>
<br>
</body>
</html>