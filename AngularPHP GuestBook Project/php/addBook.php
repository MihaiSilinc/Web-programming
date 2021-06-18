<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
if (isset($_POST['author']) && !empty(trim($_POST['author']))) {
    $username = $_POST['username'];
    $password = $_POST['password'];
    $author=$_POST['author'];
    $title=$_POST['title'];
    $comment=$_POST['comment'];
    $posted_on=$_POST['posted_on'];
    $sql_query = "INSERT INTO GuestBook (author, title, comment, posted_on) VALUES ('$author','$title','$comment','$posted_on')";
    global $connection;
    $result = mysqli_query($connection, $sql_query);
    mysqli_close($connection);
} else {
    $postdata = file_get_contents("php://input");
    $request = json_decode($postdata);
    $author = $request->author;
    $username = $request->username;
    $password = $request->password;
    $title = $request->title;
    $comment = $request->comment;
    $posted_on = $request->posted_on;
    $sql_query = "INSERT INTO GuestBook (author, title, comment, posted_on) VALUES ('$author','$title','$comment','$posted_on')";
    global $connection;
    $result = mysqli_query($connection, $sql_query);
    mysqli_close($connection);
}
