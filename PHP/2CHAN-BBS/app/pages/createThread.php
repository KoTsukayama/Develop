<?php
include_once("../databese/connect.php");
include_once("../function/thread_add.php");
include_once("../function/thread_get.php");
?>

<!-- localhost:80/2chan-bbs -->
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Langle</title>
    <link rel="stylesheet" href="../assets/css/style.css" class="css">
</head>

<body>
    <?php include("../parts/header.php"); ?>
    <?php include("../parts/validation.php"); ?>

    <div style="padding-left: 36px; color:blue">
        <h2>New thread launcher</h2>
    </div>
    <form method="POST" class="formWrapper">
        <!-- <div>
            <label>Thread name</label>
            <input type="text" name="title">
            <label>Name</label>
            <input type="text" name="username">
        </div>
        <div>
            <textarea name="body" class="commentTextArea"></textarea>
        </div>
        <input type="submit" value="Launch" name="threadSubmitButton"> -->

        <div>
            <input type="submit" value="Launch" name="threadSubmitButton">
            <label>Thread name</label>
            <input type="text" name="title">
            <label>Name</label>
            <input type="text" name="username">
        </div>
        <div>
            <textarea class="commentTextArea" name="body"></textarea>
        </div>
    </form>
</body>

</html>