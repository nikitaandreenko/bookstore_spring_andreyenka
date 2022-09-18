<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Login</title>
  <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="login">
<ul>
  <li><a href="/">Home</a></li>
  <li><a href=/books/getAll>books</a></li>
  <c:if test="${sessionScope.user == null}">
    <li style="float:right"><a class="active" href="/users/registration">Registration</a></li>
    <li style="float:right"><a class="active" href="/cart/cart">Cart</a></li>
  </c:if>
  <c:if test="${sessionScope.user != null}" >
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
<form class="form" align=center action="/orders/create" method="post">
  <fieldset>
    <legend>Create order</legend>
    <label>User: <input type="text" name="user" value="${sessionScope.order.user.id}"/></label>
    <br><br>
    <label>Total cost: <input type="text" name="totalCost" value="${sessionScope.order.totalCost}"/></label>
    <br><br>
    <label>Status: <input type="text"  name="status" value="${sessionScope.order.status}"/></label>
    <br><br>
  </fieldset>
  <input type="submit" value="save"/>
</form>
</body>
</html>
