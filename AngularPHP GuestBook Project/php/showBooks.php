<?php
header("Access-Control-Allow-Origin: *");
require_once 'configuration.php';
$sql_query = "SELECT * FROM GuestBook;";
global $connection;
$result = mysqli_query($connection, $sql_query);

if ($result) {
    $number_of_rows = mysqli_num_rows($result);
    $requested_users = array();
    $author = $_GET["author"];
    $title = $_GET["title"];
    for ($i = 0; $i < $number_of_rows; $i++) {
        $row = mysqli_fetch_array($result);
        if (str_contains($row["author"], $author) && str_contains($row["title"], $title))
            array_push($requested_users, array(
                "id" => (int)$row['id'],
                "title" => $row['title'],
                "author" => $row['author'],
                "comment" => $row['comment'],
                "posted_on" => $row['posted_on']));
    }
    mysqli_free_result($result);
    echo json_encode($requested_users);
}
mysqli_close($connection);
