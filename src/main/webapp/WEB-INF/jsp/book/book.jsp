<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book</title>
    <link href="/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Book</h1>
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
        <td>${requestScope.book.bookName}</td>
        <td>${requestScope.book.author}</td>
        <td>${requestScope.book.isbn}</td>
        <td>${requestScope.book.price}</td>
        <td>${requestScope.book.pages}</td>
        <td>${requestScope.book.binding}</td>
        <td>${requestScope.book.yearPublishing}</td>
        <td>${requestScope.book.language}</td>
    </tr>
</table>
<div>
    <a href="controller?command=update_book_form&id=${requestScope.book.id}" target = "_blank">
        <button>update</button></a>
    <a href="controller?command=delete_book&id=${requestScope.book.id}" target = "_blank">
        <button>delete</button></a>
</div>
</body>
</html>
