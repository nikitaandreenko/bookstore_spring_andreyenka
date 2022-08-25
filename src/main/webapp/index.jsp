<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link href="jsp/styles.css" rel="stylesheet" type="text/css">
    </head>
        <body>
        <h1>Bookstore made by Andreyenka with Java</h1>
        <div>
            <a href="controller?command=all_books"> <button>books</button></a>
            <a href="controller?command=all_users"><button>users</button></a>
            <a href="controller?command=all_orders"><button>orders</button></a>
        </div>
        </body>

</html>