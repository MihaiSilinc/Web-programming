<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet">
</head>
<body>
<form name="Register Form" action="/Register" method="POST">
    <label for="username">Username: </label></br>
    <input type="text" id="username" name="username"/></br>
    <label for="password">Password:</label></br>
    <input type="password" id="password" name="password"/></br>
    <label for="againPassword">Retype Password:</label></br>
    <input type="password" id="againPassword" name="againPassword"/></br>
    <input type="submit" value="Register"/>
</form>
</body>
</html>