<?php
include_once("app/function/comment_get.php");
?>
<section>
    <?php foreach ($comment_array as $cmt) : ?>
        <?php if ($thread["id"] == $cmt["thread_id"]) : ?>
            <article>
                <div class="wrapper">
                    <div class="nameArea">
                        <span>Name：</span>
                        <p class="username"> <?php echo $cmt["username"]; ?> </p>
                        <time>　　　<?php echo $cmt["post_date"]; ?></time>
                    </div>
                    <div class="commentArea">
                        <p class="comment"><?php echo $cmt["body"]; ?></p>
                    </div>
                </div>
            </article>
        <?php endif ?>
    <?php endforeach ?>
</section>