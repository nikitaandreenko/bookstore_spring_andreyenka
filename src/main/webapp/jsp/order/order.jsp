<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="jsp/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Order</h1>
<c:if test="${requestScope.message!=null}">
    <h3><em> ${requestScope.message}</em></h3>
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
        <td><a href="controller?command=user&id=${requestScope.order.user.id}"><button class="new_button">${requestScope.order.user.email}</button></a></td>
        <td>${requestScope.order.status}</td>
        <td>${requestScope.order.totalCost}</td>
        <td>
            <table class="table_mini">
                <c:forEach items="${requestScope.order.items}" var="items">
                    <tr>
                        <td><a href="controller?command=book&id=${items.book.id}"><button class="new_button">${items.book.bookName}</button></a></td>
                        <td>${items.quantity}</td>
                        <td>${items.price}</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td><a href="controller?command=order_by_user_id&user_id=${requestScope.order.user.id}"><button>Click me</button></a></td>
    </tr>
</table>
</body>
</html>