<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <link href="../css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Users</h1>
<c:if test="${message!=null}">
    <h3><em> ${message}</em></h3>
</c:if>
<h1><a href="${pageContext.request.contextPath}/users/create"><button>add user</button></a></h1>
<table>
    <tr>
        <th>#</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Role</th>
        <th>Life cycle</th>
        <th>More info</th>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.role}</td>
            <td>${user.lifeCycle}</td>
            <td><a href="${pageContext.request.contextPath}/users/${user.id}" method="get"><button>Click me</button></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>