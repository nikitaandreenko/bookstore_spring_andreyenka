<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Create book</title>
  <style>
    * {
      box-sizing: border-box;
    }
    input[type=text], select, textarea {
      width: 100%;
      padding: 12px;
      border: 1px solid #ccc;
      border-radius: 4px;
      resize: vertical;
    }
    input[type=submit] {
      background-color: #04AA6D;
      color: white;
      padding: 12px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
  </style>
</head>
<body>
<form align=center action="controller" method="post">
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
  Year_publishing: <input type="text" name="year_publishing"/>
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
