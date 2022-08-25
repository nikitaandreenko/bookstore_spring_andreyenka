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

    </tr>
    <tr>
        <td><a href="controller?command=user&id=${requestScope.order.user.id}">${requestScope.order.user.email}</a></td>
        <td>${requestScope.order.status}</td>
        <td>${requestScope.order.totalCost}</td>
        <td>
            <table class="table_mini">
                <c:forEach items="${requestScope.order.items}" var="items">
                    <tr>
                        <td>${items.book.book_name}</td>
                        <td>${items.quantity}</td>
                        <td>${items.price}</td>
<%--                        <td><a href="controller?command=book&id=${book.id}"><button>Click me</button></a></td>--%>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
</body>
</html>