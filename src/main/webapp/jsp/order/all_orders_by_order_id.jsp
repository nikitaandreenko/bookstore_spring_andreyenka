<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Order by order id</title>
  <link href="jsp/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Order</h1>
<c:if test="${requestScope.message!=null}">
  <h3><em> ${requestScope.message}</em></h3>
</c:if>
<table>
  <tr><th>#</th>
    <th>User</th>
    <th>Status</th>
    <th>Total price</th>
    <th>More info</th>

  </tr>
  <c:forEach items="${requestScope.all_orders_by_order_id}" var="order" varStatus="counter" >
    <tr>
      <td>${counter.count}</td>
      <td>${order.user.email}</td>
      <td>${order.status}</td>
      <td>${order.totalCost}</td>
      <td><a href="controller?command=order&id=${order.id}"><button>Click me</button></a></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>