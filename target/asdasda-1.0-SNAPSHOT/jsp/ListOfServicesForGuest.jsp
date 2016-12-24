<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damir
  Date: 09.11.16
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guest page</title>
</head>
<body>
<p>Добро пожаловать, гость.</p>
<p>Войдите или зарегистрируйтесь для доступа к услугам.</p>
<form action="/registration" method="post">
    <button type="submit">Зарегистрироваться</button>
</form>
<form action="/login" method="post">
    <button type="submit">Войти</button>
</form>
<p>Список услуг:</p>
<c:forEach items="${services}" var="item">
    <img src="${item.getImage()}" height="250" width="250">
    <p>Услуга: ${item.getName()} <br>
        Цена: ${item.getCost()}
    <form action="/servicedescription" method="post">
        <button type="submit" name="description" value="${item.getDescription()}">Описание</button>
    </form>
    <form action="/comments" method="post">
        <button type="submit"name="service" value="${item.getName()}">Комментарии</button>
    </form>
</c:forEach>
</body>
</html>
