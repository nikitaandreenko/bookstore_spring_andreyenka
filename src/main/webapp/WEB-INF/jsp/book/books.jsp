<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Books</title>
 <link href="../css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Books</h1>
<c:if test="${message!=null}">
    <h3><em> ${message}</em></h3>
</c:if>
<h1><a href="${pageContext.request.contextPath}/books/create"><button>add book</button></a></h1>
<table>
    <tr>
        <th>#</th>
        <th>Title</th>
        <th>Author</th>
        <th>Year Publishing</th>
        <th>More info</th>
    </tr>
    <c:forEach items="${books}" var="book" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${book.bookName}</td>
            <td>${book.author}</td>
            <td>${book.yearPublishing}</td>
            <td><a href="${pageContext.request.contextPath}/books/${book.id}" method="get"><button>Click me</button></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>