<%@ page import="Classes.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damir
  Date: 09.11.16
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User page</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
%>
Добро пожаловать, ${user.getFirstName()}
список услуг:
<form action="/storage" method="post"><button type="submit">Перейти к корзине</button> </form>
<form action="/logout" method="post"><button type="submit">Выйти</button> </form>
<c:forEach items="${services}" var="item">
    <img src="${item.getImage()}" height="250" width="250">
    <p>Услуга: ${item.getName()} <br>
    Описание: ${item.getDescription()} <br>
    Цена: ${item.getCost()}
    <form action="/servicedescription" method="post">
        <button type="submit" name="description" value="${item.getDescription()}">Описание</button>
    </form>
    <form action="/comments" method="post">
        <button type="submit"name="service" value="${item.getName()}">Комментарии</button>
    </form>
    <form action="addcomment" method="post">
        <button type="submit" name="service" value="${item.getName()}">Добавить комментарий</button>
    </form>
        <form action="/addtostorage" method="post"><button type="submit" value="${item.getNumber()}" name="number" onclick="alert('Добавлено!')">Добавить в корзину</button> </form> </p>
</c:forEach>


</body>
</html>
