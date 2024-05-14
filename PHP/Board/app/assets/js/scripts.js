$(document).ready(function () {

    // 要素をフェードアウトしてからフェードインする
    $(".element").fadeOut(500, function () {
        $(this).fadeIn(500); // フェードイン
    });

    // サイドバーを初期表示では非表示にする
    $('#sidebar').removeClass('visible');
    $('#content').removeClass('with-sidebar');

    $('#toggleSidebarVisibility').click(function (e) {
        e.stopPropagation();
        $('#sidebar').toggleClass('visible');
        $('#content').toggleClass('with-sidebar');
        toggleIconVisibility(); // アイコンの表示/非表示を切り替える
    });

    $(document).click(function (e) {
        if (!$(e.target).closest('#sidebar').length && !$(e.target).is('#toggleSidebarVisibility') && $('#sidebar').hasClass('visible')) {
            $('#sidebar').removeClass('visible');
            $('#content').removeClass('with-sidebar');
            toggleIconVisibility(); // アイコンの表示/非表示を切り替える
        }
    });

    // アイコンの表示/非表示を切り替える関数
    function toggleIconVisibility() {
        var sidebarVisible = $('#sidebar').hasClass('visible');
        if (sidebarVisible) {
            $('#toggleSidebarVisibility i').hide(); // サイドバーが表示されている場合はアイコンを非表示にする
        } else {
            $('#toggleSidebarVisibility i').show(); // サイドバーが非表示の場合はアイコンを表示する
        }
    }
    // ページ読み込み時にもアイコンの表示を切り替える
    toggleIconVisibility();
});