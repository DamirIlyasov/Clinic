<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damir
  Date: 08.11.16
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/RegistrationCss.css" rel="stylesheet">
    <title>Registration</title>
</head>
<body>

<p>Добро пожаловать на сайт стоматологического кабинета ИП"Ильясова"</p>
<div class="registration">
<p class="head">Регистрация на сайте</p>


<form method="post" action="addingnewuser" >
    <p >Имя <input name = "firstname" type="text"></p>

    <p >Фамилия<input name = "lastname" type="text"></p>

    <p >Пароль<input name = "password" type="password"></p>

    <p>Повторите пароль<input name = "passwordconf" type="password"></p>

    <p >Телефон<input name = "phonenumber" type="text"></p>

    <p >E-mail<input name = "email" type="text"></p>

    <p >Возраст<input name = "age" type="text"></p>
    <c:if test="${errorMessage != null}">
        <p>${errorMessage}</p>
    </c:if>
    <button type="submit">Зарегистрироваться </button> </form>
</form>
<form action="login" method="post"><button type="submit">Уже есть аккаунт</button> </form>
</div>
</body>
</html>
