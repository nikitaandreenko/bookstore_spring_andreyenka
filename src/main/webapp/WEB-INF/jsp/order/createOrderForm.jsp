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
    <li><a href="/orders/getAll">orders</a></li>
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

<%--<<table>--%>
<%--  <tr>--%>
<%--    <th>User</th>--%>
<%--    <th>Status</th>--%>
<%--    <th>Total cost</th>--%>
<%--&lt;%&ndash;    <th>Order items</th>&ndash;%&gt;--%>
<%--    <th>All orders this user</th>--%>
<%--  </tr>--%>
<%--  <tr>--%>
<%--    <td><a href="/users/${sessionScope.order.user.id}"><button class="new_button">${sessionScope.order.user.email}</button></a></td>--%>
<%--    <td><c:out value="${sessionScope.order.status}"/></td>--%>
<%--    <td><c:out value="${sessionScope.order.totalCost}"/></td>--%>
<%--    <td>--%>
<%--      <table class="table_mini">--%>
<%--        <c:forEach items="${sessionScope.order.items}" var="items">--%>
<%--          <tr>--%>
<%--            <td><a href="/books/${items.book.id}"><button class="new_button">${items.book.bookName}</button></a></td>--%>
<%--            <td><c:out value="${items.quantity}"/></td>--%>
<%--            <td><c:out value="${items.price}"/></td>--%>
<%--          </tr>--%>
<%--        </c:forEach>--%>
<%--      </table>--%>
<%--    </td>--%>
<%--    <td><a href="/orders/order/${sessionScope.order.user.id}"><button>Click me</button></a></td>--%>
<%--  </tr>--%>
<%--</table>--%>
    <br><br>
  </fieldset>
  <input type="submit" value="save"/>
</form>
</body>
</html>