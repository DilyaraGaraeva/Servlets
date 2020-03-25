<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 25.03.2020
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Entry</title>
</head>
<body>
<h2>Войдите в систему</h2>
<form action="" method="post">
    <input type="text" required placeholder="Login" name="login"><br>
    <input type="password" required placeholder="Password" name="password"><br><br>
    <input type="submit" value="Войти">
    ${message}
</form>
</body>
</html>
