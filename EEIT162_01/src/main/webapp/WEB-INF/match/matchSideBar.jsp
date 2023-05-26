<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>

		<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">



		<style>
			.icon-container {
				width: 50px;
				height: 50px;
				position: relative;
			}

			img {
				height: 100%;
				width: 100%;
				border-radius: 50%;
			}

			.status-circle {
				width: 10px;
				height: 10px;
				border-radius: 50%;
				background-color: rgb(0, 255, 0);
				border: 1px solid white;
				bottom: 0;
				right: 0;
				position: absolute;
			}
		</style>

		<!-- 快速配對卡片css -->
        <style>
            .card {
                color: #000;
                margin: auto;
            }

            #quick-match-img {
                object-fit: cover;
                max-width: 100%;
                height: 300px;
                width: 300px;
            }

            .status-circle-online {
                margin: 2px;
                width: 13px;
                height: 13px;
                border-radius: 50%;
                background-color: rgb(0, 255, 0);
                border: 1px solid white;
            }

            .status-circle {
                margin: 2px;
                width: 13px;
                height: 13px;
                border-radius: 50%;
                background-color: rgb(255, 0, 0);
                border: 1px solid white;
            }

            .match-rate {
                width: 50px;
                height: 20px;
                border-radius: 3px;
                background-color: rgb(255, 255, 255, 0.7);
                text-align: center;
                font-size: small;
                font-weight: bolder;
                position: absolute;
                bottom: 5px;
                right: 5px;
            }
        </style>
	</head>

	<body>

		<!-- 快速配對卡片 -->
		卡片範例：
		<div class="card" style="width: 18rem;">
			<div style="position: relative;">
				<img id="quick-match-img"
					src="https://image.civitai.com/xG1nkqKTMzGDvpLrqFT7WA/2c9470b0-8368-4b25-86e3-52e9016155ca/width=1024/00142-417701170.jpeg"
					style="border-radius: 2px;" class="card-img-top">

				<div class="match-rate">50%</div>
			</div>
			<div class="card-body">
				<h5 class="card-title">想認識新朋友，喜歡旅遊</h5>
				<div style="display: flex;">
					<div style="display: flex;align-items: center;">
						<div class='status-circle-online'></div>
						<p class="card-text">年齡 | 居住地</p>
					</div>
				</div>
				<a href="#" class="btn btn-primary" style="position: absolute;bottom: 5px;right: 5px;">讚</a>
			</div>
		</div>





		實作右邊的bar 及時觀看後台數據(根據使用者喜好，ex:年輕)

		<div class='icon-container'>
			<img
				src="https://cdn2.iconfinder.com/data/icons/flatfaces-everyday-people-square/128/beard_male_man_face_avatar-512.png" />
			<div class='status-circle'>
			</div>
		</div>

		<div class="w3-sidebar w3-bar-block w3-card w3-animate-left" style="display: none" id="leftMenu">
			<button onclick="closeLeftMenu()" class="w3-bar-item w3-button w3-large">Close &times;</button>
			<a href="#" class="w3-bar-item w3-button">Link 1</a> <a href="#" class="w3-bar-item w3-button">Link 2</a> <a
				href="#" class="w3-bar-item w3-button">Link 3</a>
		</div>
		<div class="w3-teal">
			<button class="w3-button w3-teal w3-xlarge w3-left" onclick="openLeftMenu()">&#9776;</button>
			<button class="w3-button w3-teal w3-xlarge w3-right" onclick="openRightMenu()">&#9776;</button>
			<div class="w3-container">
				<h1>My Page</h1>
			</div>
		</div>



		<script>
			function openRightMenu() {
				document.getElementById("rightMenu").style.display = "block";
			}

			function closeRightMenu() {
				document.getElementById("rightMenu").style.display = "none";
			}


			// 构建请求数据
			const data = {
				heightGreaterThan: 4,
				heightLessThan: 2,
				page: 1
			};
			console.log(data);
			console.log(JSON.stringify(data));

		</script>












		<section class="vh-100" style="background-color: #2779e2;">
			<div class="container h-100">
				<div class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-xl-9">
						<h1 class="text-white mb-4">立即註冊</h1>

						<form:form modelAttribute="member">

							<div class="card" style="border-radius: 15px;">
								<div class="card-body">

									<form:input path="id" id="memberId" />

									<!-- 體型偏好 bodyType -->
									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">
											<h6 class="mb-0">體型偏好</h6>
										</div>
										<div class="col-md-9 pe-5">
											<form:select path="memberDetail.bodyType"
												class="form-control form-control-lg">
												<form:option value="偏瘦" label="偏瘦" />
												<form:option value="普通" label="普通" />
												<form:option value="微肉感" label="微肉感" />
											</form:select>
										</div>
									</div>

									<!-- 是否抽菸 smoking -->
									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">
											<h6 class="mb-0">是否抽菸</h6>
										</div>
										<div class="col-md-9 pe-5">
											<form:select path="memberDetail.smoking"
												class="form-control form-control-lg">
												<form:option value="不抽" label="不抽" />
												<form:option value="抽" label="抽" />
											</form:select>
										</div>
									</div>

									<!-- 是否喝酒 drinking -->
									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">
											<h6 class="mb-0">是否喝酒</h6>
										</div>
										<div class="col-md-9 pe-5">
											<form:select path="memberDetail.drinking"
												class="form-control form-control-lg">
												<form:option value="不喝" label="不喝" />
												<form:option value="喝" label="喝" />
											</form:select>
										</div>
									</div>

									<!-- 月薪 monthlyIncome -->
									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">
											<h6 class="mb-0">月薪</h6>
										</div>
										<div class="col-md-9 pe-5">
											<form:select path="memberDetail.monthlyIncome"
												class="form-control form-control-lg">
												<form:option value="未滿2萬" label="未滿2萬" />
												<form:option value="2~4萬" label="2~4萬" />
												<form:option value="4~6萬" label="4~6萬" />
												<!-- ... -->
												<form:option value="15以上未滿20萬" label="15以上未滿20萬" />
												<form:option value="20以上" label="20以上" />
											</form:select>
										</div>
									</div>

									<!-- 理想對象年齡範圍 min_age_preference 和 max_age_preference -->
									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">
											<h6 class="mb-0">理想對象年齡範圍</h6>
										</div>
										<div class="col-md-4 pe-5">
											<form:input path="memberDetail.min_age_preference" type="number"
												class="form-control form-control-lg" placeholder="最小年齡" />
										</div>
										<div class="col-md-4 pe-5">
											<form:input path="memberDetail.max_age_preference" type="number"
												class="form-control form-control-lg" placeholder="最大年齡" />
										</div>
									</div>

									<!-- 希望理想對象住在哪裡 ideal_partner_location -->
									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">
											<h6 class="mb-0">希望理想對象住在哪裡</h6>
										</div>
										<div class="col-md-9 pe-5">
											<form:input path="memberDetail.ideal_partner_location" type="text"
												class="form-control form-control-lg" placeholder="理想對象住址" />
										</div>
									</div>

									<!-- 興趣愛好 hobby -->
									<div class="row align-items-center pt-4 pb-3">
										<div class="col-md-3 ps-5">
											<h6 class="mb-0">興趣愛好</h6>
										</div>
										<div class="col-md-9 pe-5">
											<form:input path="memberDetail.hobby" type="text"
												class="form-control form-control-lg" placeholder="興趣愛好" />
										</div>
									</div>


									<hr class="mx-n3">
									<!-- 送出註冊 -->
									<div class="px-5 py-4">
										<button type="submit" class="btn btn-primary btn-lg">註冊</button>
									</div>

								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</section>












	</body>

	</html>