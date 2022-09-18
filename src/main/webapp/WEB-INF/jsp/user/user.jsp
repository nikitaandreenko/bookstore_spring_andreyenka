<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
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
<table>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Age</th>
        <th>Email</th>
        <c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
            <th>Role</th>
            <th>Life cycle</th>
        </c:if>
    </tr>
    <tr>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.lastName}"/></td>
        <td><c:out value="${user.age}"/></td>
        <td><c:out value="${user.email}"/></td>
        <c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
            <td><c:out value="${user.role}"/></td>
            <td><c:out value="${user.lifeCycle}"/></td>
        </c:if>
    </tr>
</table>
<div>
    <form action="/users/update/${user.id}" method="get" target="_blank">
        <button>update</button>
    </form>
    <c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
        <form action="/users/delete/${user.id}" method="post" target="_blank">
            <button>delete</button>
        </form>
    </c:if>
    <form action="/orders/order/${user.id}" target="_blank">
        <button>my orders</button>
    </form>
</div>
</body>
</html>
