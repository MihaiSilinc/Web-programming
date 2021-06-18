<?php
include ('connection.php');
session_start();
if (isset($_SESSION['id'])){
    $userId = $_SESSION['id'];
    $username = $_SESSION['username'];

}
else {
    header('Location: loginclient.php');
    die();
}

?>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome user</title>
    <script src="main.js"></script>
    <link type="text/css" href="main.css">
</head>
<body>
<h3>Welcome <?php echo $username; ?>. </h3>

<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST'){

    $connection = OpenConnection();
    $id = $_POST['id'];
    echo $id;
    $query = "DELETE FROM lab7.LogReport WHERE id='$id' AND user_id='$userId'";

    $connection->query($query);

    header('location:user.php');
    CloseConnection($connection);

}
?>
<div>
<form action="user.php" method="post">
    <input type="text" name="id" placeholder="id">
    <input id="delete" type="submit" name="delete" value="Delete">
</form>
<br>
</div>
<div>
<p>Your reports</p>
<?php

$con = OpenConnection();
$query = "SELECT * FROM lab7.LogReport WHERE user_id='$userId'";
$result = mysqli_query($con, $query);
if(mysqli_num_rows($result)>0){
    echo "<table>";
    echo "<tr>";
    echo "<th>ID</th>";
    echo "<th>Message</th>";
    echo "<th>Type</th>";
    echo "<th>Severity</th>";
    echo "<th>Date Posted</th>";
    echo "</tr>";
    while ($row = mysqli_fetch_array($result)){
        echo "<tr>";
        echo "<th>".$row['id']."</th>";
        echo "<th>".$row['message']."</th>";
        echo "<th>".$row['type']."</th>";
        echo "<th>".$row['severity']."</th>";
        echo "<th>".$row['posted_on']."</th>";
        echo "</tr>";
    }
    echo "</table>";
}

CloseConnection($con);
?>
</div>
<br>
<form action="logout.php">
    <input type="submit" name="logout" value="Logout">
</form>
<br>

</body>
</html>