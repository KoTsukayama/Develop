<form class="formWrapper" method="POST">
    <div>
        <input type="submit" value="write" name="submitButton">
        <label>Name : </label>
        <input type="text" name="username">
        <input type="hidden" name="thread_id" value="<?php echo $thread["id"]; ?>">
    </div>
    <div>
        <textarea class="commentTextArea" name="body"></textarea>
    </div>
</form>
