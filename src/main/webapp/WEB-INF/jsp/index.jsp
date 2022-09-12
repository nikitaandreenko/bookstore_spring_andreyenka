<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bookstore by Andreyenka</title>
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
<%--<c:if test="${sessionScope.user != null}">--%>
<%--<h1>Welcome again my friend!!!, ${sessionScope.user.firstName} </h1>--%>
<%--</c:if>--%>
    <ul>
        <li><a href=/books/getAll>Books</a></li>
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
            <li><a href="/users/getAll">users</a></li>
        </c:if>
    </ul>
</body>
</html>