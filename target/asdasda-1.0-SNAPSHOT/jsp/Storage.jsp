<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damir
  Date: 12.11.16
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Storage</title>
</head>
<body>
<p>Ваша корзина:</p>
<c:if test="${storage.size() == 0}">
    <p>Корзина пуста.</p>
</c:if>
<c:forEach items="${storage}" var="item">
    <p>${item.getName()}. Цена: ${item.getCost()}
    <form action="/removefromstorage" method="post"><button type="submit" value="${item.getNumber()}" name="number">Удалить из корзины</button> </form> </p>
</c:forEach>
<p> Общая стоимость корзины: ${sumcost} </p>
<form action="/cleanstorage" method="post"><button type="submit">Очистить корзину</button> </form>
<form action="/listofservices" method="post"><button type="submit">Обратно к списку услуг</button> </form>
</body>
</html>
