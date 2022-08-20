<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<form align=center action="controller" method="post">
  <input type="hidden" name="command" value="update_book">
  <input type="hidden" name="id" value="${requestScope.book.id}">
  Name: <input type="text" name="bookName" value="${requestScope.book.book_name}"/>
  <br><br>
  Author: <input type="text" name="author" value="${requestScope.book.author}"/>
  <br><br>
  Isbn: <input type="text" name="isbn" value="${requestScope.book.isbn}"/>
  <br><br>
  Price: <input type="text" name="price" value="${requestScope.book.price}"/>
  <br><br>
  Pages: <input type="text" name="pages" value="${requestScope.book.pages}"/>
  <br><br>
  Binding: <input type="text" name="binding" value="${requestScope.book.binding}"/>
  <br><br>
  Year_publishing: <input type="text" name="year_publishing" value="${requestScope.book.year_publishing}"/>
  <br><br>
  Language: <select name="language" value="${requestScope.book.language}" >
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
  <input type="submit" value="add"/>
</form>
</body>
</html>
