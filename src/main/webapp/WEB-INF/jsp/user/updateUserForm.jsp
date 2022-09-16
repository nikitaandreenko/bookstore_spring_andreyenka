<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Update user</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="login">
<ul>
    <li><a href="/">Home</a></li>
    <li><a href=/books/getAll>books</a></li>
    <c:if test="${sessionScope.user == null}">
        <li style="float:right"><a class="active" href="/login">Login</a></li>
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
        <li><a href="/books/create">Add book</a></li>
        <li><a href="/orders/getAll">orders</a></li>
    </c:if>
</ul>
<c:if test="${sessionScope.user.role.toString()=='ADMIN'}">
    <form class="form" align=center action="/users/update/${user.id}" method="post">
        <label>First name: <input type="text" placeholder="first name" name="firstName"
                                  value="${user.firstName}"/></label>
        <br><br>
        <label>Last name: <input type="text" placeholder="last name" name="lastName" value="${user.lastName}"/></label>
        <br><br>
        <label>Age: <input type="text" placeholder="age" name="age" value="${user.age}"/></label>
        <br><br>
        <label>Email: <input type="text" placeholder="email" name="email" value="${user.email}"/></label>
        <br><br>
        <label>Password: <input type="password" placeholder="password" name="password" value="${user.password}"/></label>
        <br><br>
        <label>Role: <select name="role" value="${user.role}">
            <option>USER</option>
            <option>MANAGER</option>
            <option>ADMIN</option>
        </select>
        </label>
        <br><br>
        <label>Life cycle: <select name="lifeCycle" value="${user.lifeCycle}">
            <option>active</option>
            <option>not active</option>
        </select>
        </label>
        <br><br>
        <input type="submit" value="save"/>
    </form>
</c:if>
<c:if test="${sessionScope.user.role.toString()=='USER'}">
<form class="form" align=center action="/users/updateRegistration/${user.id}" method="post">
    <label>First name: <input type="text" placeholder="first name" name="firstName" value="${user.firstName}"/></label>
    <br><br>
    <label>Last name: <input type="text" placeholder="last name" name="lastName" value="${user.lastName}"/></label>
    <br><br>
    <label>Age: <input type="text" placeholder="age" name="age" value="${user.age}"/></label>
    <br><br>
    <label>Email: <input type="text" placeholder="email" name="email" value="${user.email}"/></label>
    <br><br>
    <label>Password: <input type="password" placeholder="password" name="password" value="${user.password}"/></label>
    <br><br>
    <input type="submit" value="save"/>
</form>
</c:if>
</body>
</html>
