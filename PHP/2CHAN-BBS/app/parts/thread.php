<?php
include_once("./app/databese/connect.php");
include_once("app/function/comment_add.php");
include_once("app/function/comment_get.php");
?>

<div class="threadWrapper">
    <div class="childWrapper">
        <div class="threadTitle">
            <span>【 Title 】</span>
            <h1>I made a bulletin board site</h1>
        </div>

        <?php include("commentSection.php"); ?>
        <?php include("commentForm.php"); ?>

    </div>
</div>