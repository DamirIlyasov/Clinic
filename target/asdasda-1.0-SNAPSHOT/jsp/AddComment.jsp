<%--
  Created by IntelliJ IDEA.
  User: damir
  Date: 23.12.16
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddComment</title>
</head>
<body>
Введите свой комментарий: <br>
<form action="/addcommenthelper" method="post">
    <input type="text" name="comm">
    <button type="submit" name="service" value="${service}">Отправить</button>
</form>
</body>
</html>
