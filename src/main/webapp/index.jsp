<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
    <style>
            body {
            background: url(bookstore.png) no-repeat;
    		color: #000;
    		background-size: cover;
    	}
    </style>
        <body>
            <h1 align=center>Bookstore made by Andreyenka with Java</h1>
            <h1 align=center><a href="controller?command=all_books">books</a></h1>
            <h1 align=center><a href="controller?command=all_users">users</a></h1>
            <h1 align=center><a href="controller?command=create_user_form">add user</a></h1>
        </body>
    </head>
</html>