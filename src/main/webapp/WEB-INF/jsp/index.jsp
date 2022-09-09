<%@ page contentType="text/html;UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Bookstore by Andreyenka</title>
        <link href="/css/styles.css" rel="stylesheet">
    </head>
        <body>
        <h1>Bookstore made by Andreyenka with Java</h1>
        <div>
            <a href=${pageContext.request.contextPath}/books/getAll> <button>books</button></a>
            <a href="${pageContext.request.contextPath}/users/getAll"><button>users</button></a>
            <a href="${pageContext.request.contextPath}/orders/getAll"><button>orders</button></a>
        </div>
        </body>

</html>