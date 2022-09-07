<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Create book</title>
  <link href="../css/styles.css" rel="stylesheet" type="text/css">
</head>
<body class="book">
<c:if test="${message!=null}">
  <h3><em> ${message}</em></h3>
</c:if>
<form align=center action="${pageContext.request.contextPath}/books/create" method="post">
  <input type="hidden" name="command" value="create_book">
  Name: <input type="text" name="bookName"/>
  <br><br>
  Author: <input type="text" name="author"/>
  <br><br>
  Isbn: <input type="text" name="isbn"/>
  <br><br>
  Price: <input type="text" name="price"/>
  <br><br>
  Pages: <input type="text" name="pages"/>
  <br><br>
  Binding: <input type="text" name="binding"/>
  <br><br>
  Year_publishing: <input type="text" name="yearPublishing"/>
  <br><br>
  Language: <select name="language">
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
  <input type="submit" value="save"/>
</form>
</body>
</html>
