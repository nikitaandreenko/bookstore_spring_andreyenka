<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="jsp/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Users</h1>
<c:if test="${requestScope.message!=null}">
    <h3><em> ${requestScope.message}</em></h3>
</c:if>
<h1><a href="controller?command=create_user_form"><button>add user</button></a></h1>
<table>
    <tr>
        <th>#</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Role</th>
        <th>More info</th>
    </tr>
    <c:forEach items="${requestScope.all_users}" var="user" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.role}</td>
            <td><a href="controller?command=user&id=${user.id}"><button>Click me</button></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>