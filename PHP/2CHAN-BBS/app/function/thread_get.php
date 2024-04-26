<?php
$thread_array = array();

// Get thread data from table
$sql = "SELECT * FROM thread";
$statement = $pdo->prepare($sql);
$statement->execute();

$thread_array = $statement;
