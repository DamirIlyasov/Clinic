<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: damir
  Date: 23.12.16
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Comments</title>
</head>
<body>
Комментарии к товару ${service}: <br>
<c:forEach items="${comments}" var="item">
    <p> ${item.getAuthor()} <br>
    ${item.getComment()} </p>
</c:forEach>
</body>
</html>
