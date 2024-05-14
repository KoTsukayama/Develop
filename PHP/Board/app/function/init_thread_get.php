<?php
$init_thread_array = array();

// Get thread data from table
$sql = "SELECT * FROM thread";
$statement = $pdo->prepare($sql);
$statement->execute();

$init_thread_array = $statement;
