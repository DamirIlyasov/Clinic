<%--
  Created by IntelliJ IDEA.
  User: damir
  Date: 23.12.16
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EditService</title>
</head>
<body>

    <form action="/editservicehelper" method="post">
        Какую услугу вы хотите изменить?
        <input type="text" name="oldName"> <br> <br>
        Введите новое название услуги
        <input type="text" name="name"> <br> <br>
        Введите новое описание услуги
        <input type="text" name="description"> <br> <br>
        Введите новую стоимость услуги
        <input type="text" name="cost"> <br> <br>
        <button type="submit">Изменить</button>
    </form>
</body>
</html>
