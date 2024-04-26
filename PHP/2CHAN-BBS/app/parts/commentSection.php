<?php
include_once("app/function/comment_get.php");
?>
<section>
    <?php foreach ($comment_array as $cmt) : ?>
        <!-- When the thread id and comment thread_id match -->
        <?php
        // var_dump($thread["id"]);
        // var_dump($cmt["thread_id"]);
        ?>
        <?php if ($thread["id"] == $cmt["thread_id"]) : ?>
            <article>
                <div class="wrapper">
                    <div class="nameArea">
                        <span>Name：</span>
                        <p class="username"> <?php echo $cmt["username"]; ?> </p>
                        <time>: <?php echo $cmt["post_date"]; ?></time>
                    </div>
                    <p class="comment"><?php echo $cmt["body"]; ?></p>
                </div>
            </article>
        <?php endif ?>
    <?php endforeach ?>
</section>