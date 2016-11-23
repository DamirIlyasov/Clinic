<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damir
  Date: 09.11.16
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
</head>
<body>
Добро пожаловать,  Администратор!
<form action="/logout" method="post"><button type="submit">Выйти</button> </form>
<form action="/addingnewservice" method="post">
    Название услуги: <input name="name" type="text">
    Описание услуги: <input name="description" type="text">
    Цена услуги: <input name = "cost" type="text">
    <input type="submit" value="Добавить">
</form>
<c:if test="${text != null}">
    <p>${text}</p>
</c:if>

<p>Cписок уже имеющихся услуг:</p>

<c:forEach items="${services}" var="item">
<p>${item.getNumber()}. Услуга: ${item.getName()} <br>
    Описание: ${item.getDescription()} <br>
    Цена: ${item.getCost()}
</c:forEach>

<p>Какую услугу вы хотите удалить? Введите номер услуги</p>
<form action="/removeservice" method="post">
    <input type="number" name="number">
    <button type="submit" onclick="return confirm('Вы действительно хотите удалить данную услугу?')">Удалить</button>
</form>
</body>
</html>
