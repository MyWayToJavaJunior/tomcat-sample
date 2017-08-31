<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/login" method="POST">
        Username: <input type="text" name="username"/><br/><br/>
        Password: <input type="password" name="password"/><br/><br/>
        <input type="submit" value="Submit"><br/><br/>
        <c:if test="${not empty error}"><p style="color:red;">${error}</p></c:if>
    </form>
</body>
</html>
