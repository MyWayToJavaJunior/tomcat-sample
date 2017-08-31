<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Articles</title>
    <style>
        body {
            margin: 0;
        }
        .container {
            margin-left: 15%;
            margin-right: 15%;
            padding: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>${title}</h1>
        <c:forEach var="i" items="${data}">
            <p><b>${i.title}</b></p>
            <p>${i.text}</p>
            <p><i>${i.createdAt}</i></p>
            <hr>
        </c:forEach>
    </div>
</body>
</html>
