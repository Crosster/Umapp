<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp"/>
<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp"/>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	.formalization{
		max-width: 1250px;
		margin: auto;
		/* overflow: hidden; */
		position: relative;
		top: 25px;
		right: -450px;
		bottom:100px;
		font-size: 25px;
	}
	
	#logoPreview{
		width:200px;
		height:100px;
	}
</style>


</head>
<body class="bg-secondary">

	<jsp:include page="/WEB-INF/layout/newnavbar.jsp" />

	<div style="padding-top: 150px;">
		<form class="formalization" action="${root}/activity/insertActivity" enctype="multipart/form-data" method="post">
			起始時間:<input type="date" name="activityStartUpTime"><br/><br/>
			結束時間:<input type="date" name="activityEndTime"><br/><br/>
			活動名稱:<input type="text" name="activityName"><br/><br/>
			活動內容:<textarea style="word-wrap: break-word;" rows="max" cols="23" name="activityContent"></textarea><br /> <br />
			費用:<input type="text" name="activityFee"><br/><br/>
			連結:<input type="text" name="activityLink"><br/><br/>
			logo:<input id="logoFile" type="file" name="activityAd" /><br/><br/>
			預覽:<img id="logoPreview" src="${root}/image/no_image.png" alt=""><br/><br/>
			廠商類型:<select name="activityType">
				  <optgroup label="選擇廠商所屬商業類別：">
				  <option>國家公園</option>
				  <option>國家藝術中心</option>
				  <option>主題樂園</option>
				  <option>電影院</option>
				  <option>KTV</option>
				  <option>百貨中心</option>
				  <option>餐廳</option>
				  <option>世貿館</option>
				  <option>運動休閒</option>
				  <option>舞廳夜店</option>
				  </optgroup>
				</select><br/><br/>
			<button>送出</button>
		</form>
	</div>

	<script>
		let theFile = document.getElementById('logoFile');
		let preview = document.getElementById('logoPreview');
		theFile.addEventListener("change",function(){
			let f = this.files[0];
			let tempsrc = URL.createObjectURL(f);
			preview.src = tempsrc;
		})
	</script>
	
</body>
</html>