<?php
$comment_array = array();

// Get comment data from table
$sql = "SELECT * FROM comment";
$statement = $pdo->prepare($sql);
$statement->execute();

$comment_array = $statement;
