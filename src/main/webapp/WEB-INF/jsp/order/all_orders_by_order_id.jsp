<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Order by order id</title>
  <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<ul>
  <li><a href="/">Home</a></li>
  <li><a href=/books/getAll>books</a></li>
  <li><a href="/">Home</a></li>
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
    <li><a href="/orders/getAll">orders</a></li>
  </c:if>
</ul>
<h1>Order</h1>
<table>
  <tr><th>#</th>
    <th>User</th>
    <th>Status</th>
    <th>Total price</th>
    <th>More info</th>

  </tr>
  <c:forEach items="${all_orders_by_order_id}" var="order" varStatus="counter" >
    <tr>
      <td><c:out value="${counter.count}"/></td>
      <td><c:out value="${order.user.email}"/></td>
      <td><c:out value="${order.status}"/></td>
      <td><c:out value="${order.totalCost}"/></td>
      <td><form action="/orders/${order.id}" method="get"><button>Click me</button></form></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>