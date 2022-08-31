<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
 <link href="jsp/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Books</h1>
<c:if test="${requestScope.message!=null}">
    <h3><em> ${requestScope.message}</em></h3>
</c:if>
<h1><a href="controller?command=create_book_form"><button>add book</button></a></h1>
<table>
    <tr>
        <th>#</th>
        <th>Title</th>
        <th>Author</th>
        <th>Year Publishing</th>
        <th>More info</th>
    </tr>
    <c:forEach items="${requestScope.all_books}" var="book" varStatus="counter">
        <tr>
            <td>${counter.count}</td>
            <td>${book.bookName}</td>
            <td>${book.author}</td>
            <td>${book.yearPublishing}</td>
            <td><a href="controller?command=book&id=${book.id}"><button>Click me</button></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>