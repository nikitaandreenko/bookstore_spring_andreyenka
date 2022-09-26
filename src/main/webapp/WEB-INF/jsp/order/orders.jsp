<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<ul>
    <li><a href="/">Home</a></li>
    <li><a href=/books/getAll>books</a></li>
    <c:if test="${sessionScope.user == null}">
        <li style="float:right" ><a  class="active" href="/login">Login</a></li>
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
<h1>Orders</h1>
<table>
    <tr><th>#</th>
        <th>User</th>
        <th>Status</th>
        <th>Total price</th>
        <th>More info</th>

    </tr>
<c:forEach items="${orders}" var="order" varStatus="counter" >
    <tr>
        <td><c:out value="${order.id}"/></td>
        <td><c:out value="${order.user.email}"/></td>
        <td><c:out value="${order.status}"/></td>
        <td><c:out value="${order.totalCost}"/></td>
        <td><form class="form_table" action="/orders/${order.id}" method="get"><button>Click me</button></form></td>
    </tr>
</c:forEach>
</table>
<br>
<div>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="/orders/getAll?page=${currentPage - 1}">Previous</a></li>
            <li class="page-item"><a class="page-link" href="/orders/getAll?page=1">1</a></li>
            <li class="page-item"><a class="page-link" href="/orders/getAll?page=2">2</a></li>
            <li class="page-item"><a class="page-link" href="/orders/getAll?page=3">3</a></li>
            <li class="page-item"><a class="page-link" href="/orders/getAll?page=4">4</a></li>
            <li class="page-item"><a class="page-link" href="/orders/getAll?page=5">5</a></li>
            <li class="page-item"><a class="page-link" href="/orders/getAll?page=6">6</a></li>
            <li class="page-item"><a class="page-link" href="/orders/getAll?page=${currentPage +1}">Next</a></li>
        </ul>
    </nav>
</div>
</body>
</html>
