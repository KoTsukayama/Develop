<?php

$user = "root";
$pass = "root";

// connect DB
try {
    $pdo = new PDO('mysql:host=localhost;dbname=langle', $user, $pass);
    // echo "Success connecting to the database.";
} catch (Exception $e) {
    echo "Error: " . $e->getMessage();
}

