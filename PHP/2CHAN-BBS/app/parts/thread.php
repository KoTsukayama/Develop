<?php
include_once("./app/databese/connect.php");
include_once("app/function/comment_add.php");
// include_once("app/function/comment_get.php");
include_once("app/function/thread_get.php");
?>

<?php foreach ($thread_array as $thread): ?>
<div class="threadWrapper">
    <div class="childWrapper">
        <div class="threadTitle">
            <span>【 Title 】</span>
            <h1><?php echo $thread["title"] ?></h1>
        </div>

        <?php include("commentSection.php"); ?>
        <?php include("commentForm.php"); ?>

    </div>
</div>
<?php endforeach ?>