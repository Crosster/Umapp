<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email驗證結果</title>
<script>
        // 在 3 秒後自動跳轉到首頁
        setTimeout(function() {
            window.location.href = "http://localhost:8080/UM-app/members/login";
        }, 3000);
    </script>
</head>
<body>
<h1>驗證結果</h1>
 <p>${message}</p>
 <div id="countdown">將在3 秒後自動跳轉到登入頁面</div>
 
 <script>
    // 定義倒數計時的秒數
    var seconds = 3;

    function countdown() {
        var countdownElem = document.getElementById("countdown");
        countdownElem.innerHTML = "將在 " + seconds + " 秒後自動跳轉到登入頁面";
        seconds--;

        if (seconds < 0) {
            // 倒數計時結束後進行跳轉
            window.location.href = "http://localhost:8080/UM-app/members/login";
        } else {
            // 每秒更新倒數計時
            setTimeout(countdown, 1000);
        }
    }

    // 開始倒數計時
    countdown();
</script>
</body>

</html>