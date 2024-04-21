<section>
    <?php foreach ($comment_array as $cmt) : ?>
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
    <?php endforeach ?>
</section>