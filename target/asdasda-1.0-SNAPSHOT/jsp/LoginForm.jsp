<%@ page import="Classes.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damir
  Date: 08.11.16
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/LoginFormCSS.css" rel="stylesheet">
    <title>Login</title>
</head>
<body>
<p class="header">Форма входа</p>
<div class="login">
<p class="head">Введите свои e-mail и пароль</p>
<form method="post" action="logincheck">
    <p>E-mail  <input class="input" type="text" placeholder="Введите свой e-mail" name="email"></p>
    <p>Пароль <input class="input" type="password" placeholder="Введите свой пароль" name="password"></p>
    <p><input type="checkbox" name="createcookie" value="true" />Запомнить меня</p>
    <button type="submit" class ="button">Войти</button>
</form>
<c:if test="${errormessage != null}">
    <p>${errormessage}</p>
</c:if>
<form method="post" action="/registration"> <button type="submit" class ="button">Ещё не зарегистрирован </button> </form>
<form method="post" action="/listofservices">
    <%
        User user = new User(1);
        session.setAttribute("user", user);
    %>
    <button type="submit" class ="button">Войти как гость</button>
</form>
</div>

</body>
</html>
