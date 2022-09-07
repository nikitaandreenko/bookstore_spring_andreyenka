<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book</title>
    <link href="../css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Book</h1>
<c:if test="${message!=null}">
    <h3><em> ${message}</em></h3>
</c:if>
<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Isbn</th>
        <th>Price, USD</th>
        <th>Pages</th>
        <th>Binding</th>
        <th>Year Publishing</th>
        <th>Language</th>

    </tr>
    <tr>
        <td>${book.bookName}</td>
        <td>${book.author}</td>
        <td>${book.isbn}</td>
        <td>${book.price}</td>
        <td>${book.pages}</td>
        <td>${book.binding}</td>
        <td>${book.yearPublishing}</td>
        <td>${book.language}</td>
    </tr>
</table>
<div>
    <a href="${pageContext.request.contextPath}/books/update/${book.id}" method="get" target = "_blank">
        <button>update</button></a>
    <a href="${pageContext.request.contextPath}/books/delete/${book.id}" method="get" target = "_blank">
        <button>delete</button></a>
</div>
</body>
</html>
