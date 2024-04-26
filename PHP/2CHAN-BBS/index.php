<?php
include_once("app/databese/connect.php");
include_once("app/function/comment_add.php");
include_once("app/function/comment_get.php");
?>

<!-- localhost:80/2chan-bbs -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Langle</title>
    <link rel="stylesheet" href="App/assets/css/style.css" class="css">
</head>

<body>
    <?php include("app/parts/header.php"); ?>
    <?php include("app/parts/validation.php"); ?>
    <?php include("app/parts/thread.php"); ?>
    <?php include("app/parts/newThreadButton.php"); ?>
</body>

</html>