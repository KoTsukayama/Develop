<?php
$comment_array = array();

// Get comment data from table
$sql = "SELECT * FROM comment";
$statement = $pdo->prepare($sql);
$statement->execute();

// $comment_array = $statement;

// Fetch data and store in $comment_array
while ($row = $statement->fetch(PDO::FETCH_ASSOC)) {
    $comment_array[] = $row;
}
