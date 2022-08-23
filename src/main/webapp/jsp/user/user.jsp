<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="jsp/styles.css" rel="stylesheet" type="text/css">
</head>

<body>
<h1>User</h1>
<c:if test="${requestScope.message!=null}">
    <h3><em> ${requestScope.message}</em></h3>
</c:if>
<c:if test="${requestScope.messageUpdate!=null}">
    <h3><em> ${requestScope.messageUpdate}</em></h3>
</c:if>
<c:if test="${requestScope.messageCreate!=null}">
    <h3><em> ${requestScope.messageCreate}</em></h3>
</c:if>
<table>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Age</th>
        <th>Email</th>
        <th>Role</th>

    </tr>
    <tr>
        <td>${requestScope.user.firstName}</td>
        <td>${requestScope.user.lastName}</td>
        <td>${requestScope.user.age}</td>
        <td>${requestScope.user.email}</td>
        <td>${requestScope.user.role}</td>
    </tr>
</table>
<div>
    <a href="controller?command=update_user_form&id=${requestScope.user.id}" target = "_blank">
        <button>update</button></a>
    <a href="controller?command=delete_user&id=${requestScope.user.id}" target = "_blank">
        <button>delete</button></a>
</div>
</body>
</html>
