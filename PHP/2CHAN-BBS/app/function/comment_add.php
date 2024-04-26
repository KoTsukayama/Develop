<?php
$error_message = array();

try {

    if (isset($_POST["submitButton"])) {

        $userName = $_POST["username"];
        $body = $_POST["body"];
        $thread_id = $_POST["thread_id"];
    
        // Username input check
        if (empty($userName)) {
            $error_message["username"] = "Please enter your name";
        } else {
            // Anti-scraping measures
            $escaped["username"] = htmlspecialchars($userName, ENT_QUOTES, "UTF-8");
        }
    
        // Comment input check
        if (empty($body)) {
            $error_message["body"] = "Please enter your comment";
        } else {
            // Anti-scraping measures
            $escaped["body"] = htmlspecialchars($body, ENT_QUOTES, "UTF-8");
        }
    
        // No errors
        if (empty($error_message)) {
    
            // Database connection assuming $pdo is properly initialized
    
            $post_date = date("Y-m-d H:i:s");
    
            $sql = "INSERT INTO `comment` (`username`, `body`, `post_date`, `thread_id`) VALUES (:username, :body, :post_date, :thread_id); ";
            $statement = $pdo->prepare($sql);
    
            // Set values
            $statement->bindParam(":username", $escaped["username"], PDO::PARAM_STR);
            $statement->bindParam(":body", $escaped["body"], PDO::PARAM_STR); // Corrected variable name
            $statement->bindParam(":post_date", $post_date, PDO::PARAM_STR);
            $statement->bindParam(":thread_id", $thread_id, PDO::PARAM_STR);
            $statement->execute();
    
            // Redirect
            header("Location: " . $_SERVER['REQUEST_URI']);
            exit();
        }
    }
}catch (PDOException $e) {  
    $error_message[''] = $e->getMessage();  
}