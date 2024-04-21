<?php
$error_message = array();

if (isset($_POST["submitButton"])) {

    $userName = $_POST["username"];
    $boby = $_POST["body"];

    // Username input check
    if (empty($userName)) {
        $error_message["username"] = "Please enter your name";
    } else {
        // Anti-scraping measures
        $escaped["username"] = htmlspecialchars($userName, ENT_QUOTES, "UTF-8");
    }

    // Comment input check
    if (empty($boby)) {
        $error_message["body"] = "Please enter your comment";
    } else {
        // Anti-scraping measures
        $escaped["boby"] = htmlspecialchars($boby, ENT_QUOTES, "UTF-8");
    }

    // Not error
    if (empty($error_message)) {

        $post_date = date("Y-m-d H:i:s");

        $sql = "INSERT INTO `comment` (`username`, `body`, `post_date`) VALUES (:username, :body, :post_date); ";
        $statement = $pdo->prepare($sql);

        // Set value
        $statement->bindParam(":username", $escaped["username"], PDO::PARAM_STR);
        $statement->bindParam(":body", $escaped["boby"], PDO::PARAM_STR);
        $statement->bindParam(":post_date", $post_date, PDO::PARAM_STR);
        $statement->execute();

        // リダイレクト
        header("Location: " . $_SERVER['REQUEST_URI']);
        exit();
    }
}