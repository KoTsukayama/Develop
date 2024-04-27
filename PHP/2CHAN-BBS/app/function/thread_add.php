<?php
$error_message = array();

try {
    if (isset($_POST["threadSubmitButton"])) {

        $title = $_POST["title"];
        $userName = $_POST["username"];
        $body = $_POST["body"];

        // Thread input check
        if (empty($title)) {
            $error_message["title"] = "Please enter title";
        } else {
            // Anti-scraping measures
            $escaped["title"] = htmlspecialchars($title, ENT_QUOTES, "UTF-8");
        }

        // Anti-scraping measures
        $escaped["username"] = htmlspecialchars($userName, ENT_QUOTES, "UTF-8");
        $escaped["body"] = htmlspecialchars($body, ENT_QUOTES, "UTF-8");

        // Not error
        if (empty($error_message)) {

            $post_date = date("Y-m-d H:i:s");
            
            // Transaction
            $pdo->beginTransaction();

            try {
                // Add thread
                $sql = "INSERT INTO `thread` (`title`) VALUES (:title);";
                $statement = $pdo->prepare($sql);

                // Set value
                $statement->bindParam(":title", $escaped["title"], PDO::PARAM_STR);
                $statement->execute();

                // Add comment
                $sql = "INSERT INTO `comment` (`username`, `body`, `post_date`, `thread_id`) 
            VALUES (:username, :body, :post_date, (SELECT id FROM thread WHERE title = :title));";
                $statement = $pdo->prepare($sql);

                // Set value
                $statement->bindParam(":username", $escaped["username"], PDO::PARAM_STR);
                $statement->bindParam(":body", $escaped["body"], PDO::PARAM_STR);
                $statement->bindParam(":post_date", $post_date, PDO::PARAM_STR);
                $statement->bindParam(":title", $escaped["title"], PDO::PARAM_STR);

                $statement->execute();

                $pdo->commit();
                // Transition to bulletin board page
                header("Location: http://localhost:80/2chan-bbs");
            } catch (PDOException $e) {
                $pdo->rollBack();
            }
        }
    }
} catch (Exception $e) {
    $error_message[] = $e->getMessage();
    var_dump($e->getTraceAsString());
}
