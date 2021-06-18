<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
if (isset($_POST['author']) && !empty(trim($_POST['author']))) {
    $username = $_POST['username'];
    $password = $_POST['password'];
    $id = $_POST['id'];
    $author=$_POST['author'];
    $title=$_POST['title'];
    $comment=$_POST['comment'];
    $posted_on=$_POST['posted_on'];
    $sql_query = "UPDATE GuestBook SET author='$author', title='$title', comment='$comment', `posted_on`='$posted_on' WHERE id='$id'";
    global $connection;
    $result = mysqli_query($connection, $sql_query);
    mysqli_close($connection);
} else {
    $postdata = file_get_contents("php://input");
    $request = json_decode($postdata);
    $id = $request->id;
    $author = $request->author;
    $username = $request->username;
    $password = $request->password;
    $title = $request->title;
    $comment = $request->comment;
    $posted_on = $request->posted_on;
    $sql_query = "UPDATE GuestBook SET author='$author', title='$title', comment='$comment', `posted_on`='$posted_on' WHERE id='$id'";
    global $connection;
    $result = mysqli_query($connection, $sql_query);
    mysqli_close($connection);
}

