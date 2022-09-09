<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Order</h1>
<c:if test="${message!=null}">
    <h3><em> ${message}</em></h3>
</c:if>
<table>
    <tr>
        <th>User</th>
        <th>Status</th>
        <th>Total cost</th>
        <th>Order items</th>
        <th>All orders this user</th>
    </tr>
    <tr>
        <td><a href="${pageContext.request.contextPath}/users/${order.user.id}"><button class="new_button">${order.user.email}</button></a></td>
        <td>${order.status}</td>
        <td>${order.totalCost}</td>
        <td>
            <table class="table_mini">
                <c:forEach items="${order.items}" var="items">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/books/${items.book.id}"><button class="new_button">${items.book.bookName}</button></a></td>
                        <td>${items.quantity}</td>
                        <td>${items.price}</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td><a href="${pageContext.request.contextPath}/orders/order/${order.user.id}"><button>Click me</button></a></td>
    </tr>
</table>
</body>
</html>