<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>
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
<h1>Order</h1>
<table>
    <tr>
        <th>User</th>
        <th>Status</th>
        <th>Total cost</th>
        <th>All orders this user</th>
    </tr>
    <tr>
        <td><a href="/users/${order.user.id}">
            <button class="new_button">${order.user.email}</button>
        </a></td>
        <td>
            <form action="/orders/update/${order.id}" method="post">
                <select name="status">
                    <option selected value="${order.status}">${order.status}</option>
                    <option>CONFIRMED</option>
                    <option>DELIVERED</option>
                </select>
                <input type="submit" value="update"/>
            </form>
        </td>
        <td><c:out value="${order.totalCost}"/></td>
        <td><a href="/orders/order/${order.user.id}">
            <button>Click me</button>
        </a></td>
    </tr>
</table>
<h1>Order details</h1>
<table>
    <c:forEach items="${order.items}" var="items">
        <tr>
            <td><a href="/books/${items.book.id}">
                <button class="new_button">${items.book.bookName}</button>
            </a></td>
            <td><c:out value="${items.quantity}"/></td>
            <td><c:out value="${items.price}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
