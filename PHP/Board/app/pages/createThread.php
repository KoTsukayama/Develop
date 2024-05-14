<?php
include_once("../databese/connect.php");
include_once("../function/thread_add.php");
include_once("../function/thread_get.php");
?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Langle</title>
    <link rel="stylesheet" href="../assets/css/style.css" class="css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-qRkwXZzXyV8Aekk6/vxnyeytnoKD5NWq8ECN4Z3Xy5raDvdVi7PKn1FjDJ4DlT/z1BzDGU+4fMUKPbls+M+6lA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="../assets/js/scripts.js"></script>
</head>

<body>
    <div class="element">
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
    </div>
</body>

</html>