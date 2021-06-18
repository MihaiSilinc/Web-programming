<%--
  Created by IntelliJ IDEA.
  User: forest
  Date: 5/17/2018
  Time: 7:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="/index.css">
  <title>Login</title>
</head>
<div id = "login">
<h5>Welcome, please login here: </h5>
<form name="Login Form" action=/Login method="POST">
  <label for="username">Username: </label></br>
  <input type="text" id="username" name="username"/></br>
  <label for="password">Password:</label></br>
  <input type="password" id="password" name="password"/></br>
  <input type="submit" value="login"/>
</form>

<h5>No account? Press here to register: </h5>
<form name="Register Form" action="register.jsp">
  <input type="submit" value="Create New Account" >
</form>
</div>
</body>
</html>
