<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Admin</title>
    <style>
        body {
            margin: 0;
        }
        .container {
            margin-left: 15%;
            margin-right: 15%;
            padding: 5px;
        }
        table {
            width: 400px;
        }

        td, th {
            border: 0px solid #dddddd;
            text-align: left;
        }
    </style>
</head>
<body>
    <div class="container">
        <h3>Add new article</h3>
        <form action="/admin" method="POST">
            <p>Title:</p>
                <input type="text" name="title"/><br/><br/>
            <p>Text:</p>
                <textarea name="text" cols="40" rows="3"></textarea><br/><br/>
            <input type="submit" value="Add"><br/><br/>
        </form>

        <br/>

        <h3>Your articles</h3>
        <table>
            <c:forEach var="i" items="${articles}">
                <tr>
                    <th><b>${i.title}</b></th>
                    <th><form action="/admin?id=${i.id}&method=delete" method="POST"><input type="submit" value="Delete"></form></th>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
