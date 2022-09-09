<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update book</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="book">
<c:if test="${message!=null}">
    <h3><em> ${message}</em></h3>
</c:if>
<form align=center action="${pageContext.request.contextPath}/books/update/${book.id}" method="post">
    <label>Name: <input type="text" name="bookName" value="${book.bookName}"/></label>
    <br><br>
    <label>Author: <input type="text" name="author" value="${book.author}"/></label>
    <br><br>
    <label>Isbn: <input type="text" name="isbn" value="${book.isbn}"/></label>
    <br><br>
    <label>Price: <input type="text" name="price" value="${book.price}"/></label>
    <br><br>
    <label>Pages: <input type="text" name="pages" value="${book.pages}"/></label>
    <br><br>
    <label>Binding: <input type="text" name="binding" value="${book.binding}"/></label>
    <br><br>
    <label>Year_publishing: <input type="text" name="yearPublishing" value="${book.yearPublishing}"/></label>
    <br><br>
    <label>Language: <select name="language" value="${book.language}">
        <option>ENGLISH</option>
        <option>RUSSIAN</option>
        <option>SPANISH</option>
        <option>FRENCH</option>
        <option>DEUTSCH</option>
        <option>ARABIC</option>
        <option>CHINESE</option>
        <option>JAPANESE</option>
    </select>
    </label>
    <br><br>
<%--    <label>--%>
<%--        Availability:--%>
<%--        <select name = availability">--%>
<%--            <option>in stock</option>--%>
<%--            <option>out of stock</option>--%>
<%--        </select>--%>
<%--    </label>--%>
<%--    <br><br>--%>
    <input type="submit" value="save"/>
</form>
</body>
</html>
