<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<ul>
    <li><a href="/">Home</a></li>
    <li><a href=/books/getAll>books</a></li>
    <c:if test="${sessionScope.user == null}">
        <li style="float:right" ><a  class="active" href="/login">Login</a></li>
        <li style="float:right"><a class="active" href="/users/create">Registration</a></li>
        <li style="float:right"><a class="active" href="/cart/cart">Cart</a></li>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <li style="float:right"><a class="active" href="/logout">Logout</a></li>
        <li style="float:right"><a class="active" href="/cart/cart">Cart</a></li>
        <li style="float:right"><a class="active" href="/users/${user.id}" method="get">My profile</a></li>
    </c:if>
    <c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
        <li><a href="/users/create">New user</a></li>
        <li style="float:right"><a class="active" href="/cart/cart">Cart</a></li>
        <li><a href="/books/create">Add book</a></li>
        <li><a href="/orders/getAll">orders</a></li>
    </c:if>
</ul>
<h1>Users</h1>
<table>
    <tr>
        <th>#</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Role</th>
        <th>Life cycle</th>
        <th>More info</th>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="counter">
        <tr>
            <td><c:out value="${counter.count}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.lifeCycle}"/></td>
            <td><form action="/users/${user.id}" method="get"><button>Click me</button></form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>