<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            margin: 0;
        }
        .container {
            margin-left: 15%;
            margin-right: 15%;
            margin-top: 50px;
            padding: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="/login" method="POST">
            Username: <input type="text" name="username"/><br/><br/>
            Password: <input type="password" name="password"/><br/><br/>
            <input type="submit" value="Submit"><br/><br/>
            <c:if test="${not empty error}"><p style="color:red;">${error}</p></c:if>
        </form>
    </div>
</body>
</html>
