<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>

<body>
<h1>User</h1>
<c:if test="${message!=null}">
    <h3><em> ${message}</em></h3>
</c:if>
<table>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Age</th>
        <th>Email</th>
        <th>Role</th>
<%--        <th>Life cycle</th>--%>

    </tr>
    <tr>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${user.role}</td>
<%--        <td>${user.lifeCycle}</td>--%>
    </tr>
</table>
<div>
    <<form action="${pageContext.request.contextPath}/users/update/${user.id}" method="get" target = "_blank">
        <button>update</button></form>
    <form action="${pageContext.request.contextPath}/users/delete/${user.id}" method="post" target = "_blank">
        <button>delete</button></form>
</div>
</body>
</html>
