<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Update book</title>
  <link href="../../css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="book">
<c:if test="${message!=null}">
  <h3><em> ${message}</em></h3>
</c:if>
<form align=center action="${pageContext.request.contextPath}/books/update/${book.id}" method="post">
  Name: <input type="text" name="bookName" value="${book.bookName}"/>
  <br><br>
  Author: <input type="text" name="author" value="${book.author}"/>
  <br><br>
  Isbn: <input type="text" name="isbn" value="${book.isbn}"/>
  <br><br>
  Price: <input type="text" name="price" value="${book.price}"/>
  <br><br>
  Pages: <input type="text" name="pages" value="${book.pages}"/>
  <br><br>
  Binding: <input type="text" name="binding" value="${book.binding}"/>
  <br><br>
  Year_publishing: <input type="text" name="yearPublishing" value="${book.yearPublishing}"/>
  <br><br>
  Language: <select name="language" value="${book.language}" >
  <option>ENGLISH</option>
  <option>RUSSIAN</option>
  <option>SPANISH</option>
  <option>FRENCH</option>
  <option>DEUTSCH</option>
  <option>ARABIC</option>
  <option>CHINESE</option>
  <option>JAPANESE</option>
</select>
  <br><br>
  Availability: <select type="text" name="availability" value="${book.availability}">
  <option>in stock</option>
  <option>out of stock</option>
  <br><br>
</select>
  <input type="submit" value="save"/>
</form>
</body>
</html>
