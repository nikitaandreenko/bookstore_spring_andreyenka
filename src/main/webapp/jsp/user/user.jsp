<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        table {
            width: 100%;
            margin-bottom: 20px;
            border-collapse: collapse;
            outline: 3px solid #ffd300;
            font-size: 15px;
        }

        table th {
            font-weight: bold;
            padding: 7px;
            background: #ffd300;
            border: none;
            text-align: left;
            font-size: 15px;
            border-bottom: 3px solid #ffd300;
        }

        table td {
            padding: 7px;
            border: none;
        }
    </style>
<body>
<h1 align="center" style="color:#ff0000">User</h1>
<c:if test="${requestScope.message!=null}">
    <h3 align="center" style="color:#0000ff"><em> ${requestScope.message}</em></h3>
</c:if>
<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Role</th>
        <th>update</th>
    </tr>
    <tr>
        <td>${requestScope.user.firstName}</td>
        <td>${requestScope.user.lastName}</td>
        <td>${requestScope.user.role}</td>
        <td><a href="controller?command=update_user_form&id=${requestScope.user.id}">update</a></td>
    </tr>
</table>
</body>
</head>
</html>
