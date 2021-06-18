<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Over</title>
    <link rel="stylesheet" href="/failed.css">
</head>
<body>
<div>
    <h4>Hello <p id="username"><%String username = (String)session.getAttribute("username");out.println(username);%></p></h4>
    <h4>You <% String status = (String)session.getAttribute("status");out.println(status);%> after
        <% String moves = (String)session.getAttribute("moves");out.println(moves); %> moves!</h4>
    <form action=/Login method="GET">
        Do you want to start another game?
        <input name="username" style="display: none" value=<%out.println(username);%>>
        <input type="submit" value="Start">
    </form>
</div>
</body>
</html>