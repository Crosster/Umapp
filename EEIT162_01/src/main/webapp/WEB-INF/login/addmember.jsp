<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<meta charset="UTF-8">
<title>註冊帳號</title>

<style>
.ww {
	width: 200px;
	height: 200px;
}

.password-match-error {
	border-color: red;
}

.hhh {
	height: 80%;
	background-color: rgba(255, 255, 255, 0.884);
}

.bbx {
	box-shadow: 3px 3px 10px;
}

body {
	background-image: url('${root}/img/a24hr-lgwlv.jpg');
	background-size: cover; /* 调整背景图片的尺寸以覆盖整个元素 */
	background-position: center top; /* 将背景图片定位于元素的中心 */
	background-repeat: no-repeat;
}
</style>
</head>

<script type="text/javascript">
	const root = "${root}";
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/croppie/2.6.5/croppie.js"
	integrity="sha512-vUJTqeDCu0MKkOhuI83/MEX5HSNPW+Lw46BA775bAWIp1Zwgz3qggia/t2EnSGB9GoS2Ln6npDmbJTdNhHy1Yw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<body>
	<div class="container">
		<div class="m-auto w-75  text-center hhh ">
			<div class="bbx">


				<h1>註冊帳號</h1>



				<form method="post" action="" enctype="multipart/form-data"
					onsubmit="return validateForm()">
					<div class="form-group">
						<label for="username">信箱:</label> <input type="hidden"
							id="confirmEmail" name="confirmEmail" value=""> <input
							type="email" class="form-control m-auto w-25" id="email"
							name="email" required>
					</div>
					<br>
					<div class="form-group">
						<label for="password">密碼:</label> <input type="password"
							class="form-control m-auto w-25" id="password" name="password"
							required>
					</div>
					<br>
					<div class="form-group">
						<label for="confirmPassword">確認密碼:</label> <input type="password"
							class="form-control m-auto w-25" id="confirmPassword"
							name="confirmPassword" required onblur="checkPasswordMatch()"
							oninput="handleInput()">
					</div>
					<br>
					<div class="form-group">
						<label for="email">用戶名稱:</label> <input type="text"
							class="form-control m-auto w-25" id="username" name="username"
							required>
					</div>
					<br>
					<div class="form-group">
						<label for="photoContent">頭像圖片選擇:</label> <input type="file"
							class="form-control m-auto w-25 " id="photoContent"
							name="photoContent" onchange="previewImage(this);">
					</div>
					<div class="img" id="previewImgHome">
						<img src="${root}/getphoto" id="preview" class="ww">
					</div>




					<button type="submit" class="btn btn-outline-warning">註冊</button>
					<a href="${root}/"><button class="btn btn-outline-danger"
							type="button">取消</button></a>
					<c:if test="${not empty errorMessage}">
						<div class="error-message">${errorMessage}</div>
					</c:if>

				</form>

				<div>
					<button type="button" class="btn btn-primary" onclick="fillForm()">一鍵填入</button>
				</div>


			</div>
		</div>
	</div>
</body>




<script>
	const photo = document.getElementById("photoContent");
	const preview = document.getElementById("preview");

	photo.addEventListener("change", function() {
		let photoContent = this.files[0];
		if (photoContent != undefined) {
			let temPath = URL.createObjectURL(photoContent);
			preview.src = temPath
		}
		if (photoContent == undefined) {
			preview.src = ""
		}
	})
	preview.addEventListener("click", function() {
		photo.click()
	})

	function fillForm() {
		document.getElementById("email").value = "qq@example.com";
		document.getElementById("password").value = "1234";
		document.getElementById("username").value = "dd";
	}
	function checkPasswordMatch() {
		var password = document.getElementById("password").value;
		var confirmPassword = document.getElementById("confirmPassword").value;
		var passwordInput = document.getElementById("password");
		var confirmPasswordInput = document.getElementById("confirmPassword");

		if (password !== confirmPassword) {
			alert("確認密碼不匹配");
			passwordInput.classList.add("password-match-error");
			confirmPasswordInput.classList.add("password-match-error");
		} else {
			passwordInput.classList.remove("password-match-error");
			confirmPasswordInput.classList.remove("password-match-error");
		}
	}

	function handleInput() {
		var passwordInput = document.getElementById("password");
		var confirmPasswordInput = document.getElementById("confirmPassword");

		passwordInput.classList.remove("password-match-error");
		confirmPasswordInput.classList.remove("password-match-error");
	}
	<c:if test="${not empty errorMessage}">
	alert("${errorMessage}");

	</c:if>
</script>
</html>