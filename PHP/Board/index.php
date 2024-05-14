<?php
include_once("./app/databese/connect.php");
include_once("app/function/comment_add.php");
include_once("app/function/comment_get.php");
include_once("app/function/init_thread_get.php");
?>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><?php $title = "BOARD";
            echo $title ?></title>
    <link rel="stylesheet" href="app/assets/css/style.css" class="css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-qRkwXZzXyV8Aekk6/vxnyeytnoKD5NWq8ECN4Z3Xy5raDvdVi7PKn1FjDJ4DlT/z1BzDGU+4fMUKPbls+M+6lA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="app/assets/js/scripts.js"></script>
</head>

<body>
    <div id="sidebar" class="sidebar visible">
        <button id="toggleSidebarVisibility">
            <i class="fas fa-comment">>></i>
        </button>
        <?php foreach ($init_thread_array as $thread) : ?>
            <h2>
                <a href="<?php echo $thread["link"]; ?>"><?php echo $thread["title"]; ?>
                </a>
            </h2>
        <?php endforeach; ?>
    </div>
    <div id="content" class="main-content">
        <div class="element">
        <?php include("app/parts/header.php"); ?>
        <?php include("app/parts/validation.php"); ?>
        <?php include("app/parts/thread.php"); ?>
        <?php include("app/parts/newThreadButton.php"); ?>
        </div>
    </div>
</body>

<script>
    // ページが読み込まれたときに実行する処理
    window.onload = function() {
        // 各リンク要素にクリックイベントリスナーを追加
        var links = document.querySelectorAll('#sidebar a');
        links.forEach(function(link) {
            link.addEventListener('click', function(event) {
                // デフォルトのクリック動作をキャンセル
                event.preventDefault();
                // クリックされたリンクのhref属性を取得
                var targetId = link.getAttribute('href').substring(1);

                // 対応するスレッドタイトル要素を取得
                var targetElement = document.querySelector('h1[data-title="' + targetId + '"]');
                // 対応するスレッドタイトル要素が存在する場合
                if (targetElement) {
                    // 対応するスレッドタイトル要素までスクロール
                    targetElement.scrollIntoView({
                        behavior: 'smooth' // スムーズにスクロール
                    });
                }
            });
        });
    };
</script>

</html>