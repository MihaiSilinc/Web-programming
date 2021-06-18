<?php

function OpenConnection(): mysqli
{
    $dbhost = "127.0.0.1";
    $dbusername = "mihai";
    $dbpassword = "mihai";
    $dbname = "phplab";

    return mysqli_connect($dbhost, $dbusername, $dbpassword, $dbname);
}

function CloseConnection(mysqli $con)
{
    $con->close();
}