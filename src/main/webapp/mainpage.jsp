<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 25.03.2020
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<h1>Hello ${user.name}</h1><br>

<form action="/setCookie" method="post">
    <label>
        Ввод сообщения:
        <input type="text" required name="message">
    </label>
    <input type="submit" value="Отправить">
</form>
${message}

<br><br>
<a href="/logout">Выход</a>
</body>
</html>

