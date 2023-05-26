<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 用於設定 SpringFramework 表單，並將 表單 包成一個物件 傳送給 Controller -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html>
<head>
<script src="${root}/js/index.js"></script>
<title>${webName}</title>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta content="text/html; charset=iso-8859-2" http-equiv="Content-Type">
<script type="text/javascript" src="${root}/js/activity.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<style>
<!-- -->
.mySlides {
	display: none;
}

.xxx {
	height: 5%;
	width: 5%;
}
</style>

<!-- 活動系統 商品卡片 -->
<style>
.container {
	max-width: 1250px;
	margin: auto;
	/* overflow: hidden; */
	position: relative;
		top: 50px;
		right: -50px;
		bottom:100px;
	}

.News {
	list-style: none;
	list-style-image: none;
	margin: -25px; 
    padding: 0; 
    border: 0; 
    font-size: 100%; 
    font: inherit; 
    vertical-align: baseline;
    position: relative;
		top: 0px;
		right: 0px;
		bottom: 0px;
	}

.list {
	width: 30%;
	height: 450px;
	margin-left: 1%;
	margin-right: 2%;
	top: -20px;
	float: left;
	display: block;
	padding: 10px;
	border: 1px solid #999;
	box-shadow: 5px 5px 5px gray;
	}

a {
	text-decoration: none;
	color: #333;
}

.picture {
	width: 100%;
	height: 200px;
}

#sidespace {
	position: relative;
	top: -50px;
}

.title {
	top: 10px;
	font-weight: bold;
	text-align: center;
	color: white;
	width: 250px;
	border: 1px solid darkblue;
	border-radius: 15px;
	text-shadow: 0.2em 0.2em 0.1em grey;
	background-color: darkblue;
}

.date {
	font-weight: bold;
	text-align: left;
	width: 200px;
	font-size: 20px;
}

.content {
	font-size: 14px;
	color:rgb(255,200,200);
	position:relative;
	}
.content:hover {
	color:black;
	background-color: yellow;
	} 

.pageNum{
	/* position: absolute; */
	margin-top: 50px;
	padding-bottom: 50px;
    bottom: -225px;
    left: 905px;
    font-size: 30px;
    color: white;
	}
</style>


<!-- 搜索欄位 -->
<style type="text/css">
	.container1 {
		position: fixed;
			top:20px;
			left:10px;
		margin: 0;
		padding: 0;
		height: 70px;
		width: 800px;
		margin: 100px auto;
		font-size: 16px;
		}
	.parent {
		width: 100%;
		height: 42px;
		top: 4px;
		position: relative;
		}
	.parent>input:first-of-type {
		/*输入框高度设置为40px, border占据2px，总高度为42px*/
		width: 220px;
		height: 40px;
		border: 1px solid #ccc;
		font-size: 12px;
		outline: none;
		}
	.parent>input:first-of-type:focus {
		border: 1px solid #317ef3;
		padding-left: 16px;
		}
	.parent>input:last-of-type {
		/*button按钮border并不占据外围大小，设置高度42px*/
		width: 70px;
		height: 40px;
		position: absolute;
		background: #317ef3;
		border: 1px solid #317ef3;
		color: #fff;
		font-size: 16px;
		outline: none;
		}
	.presentError{
		position: relative;
		top:15px;
		font-size: 16px;
		color: bule;
	}
	.textarea1{
		width: 180px;
		height: 180px;
		resize: none;
	}
</style>


</head>
<body>
	<jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
	
	<div class="" style="position: fixed;top:120px;left:0px;">
		<form action="${root}/activity/eventpage" class="parent">
			<input type="text" name="keyword" style="width: 100px;">
			<input type="submit" value="快搜">
		</form>
		<br/>
			<textarea class="textarea1">輸入【關鍵字】建議採用：電影院、國家公園、國家藝術中心、主題樂園、餐廳、電影院、百貨中心、世貿館、運動休閒、KTV、舞廳夜店...等。</textarea>
	<div class="presentError">
		<c:if test="${emtpyResults}">
			<p>您搜尋的活動不存在，請重新輸入！</p>
		</c:if>
	</div>
	</div>
	
	
	<div class="container" style="padding-top: 100px" >
		<ul class="News">
			<c:forEach begin="0" end="${pageObject.content.size() -1}" step="1" var="i">
				<li class="list">
					<img class="picture" src="${root}/activity/showImg/${pageObject.content[i].activityId}" alt="">
					<h4 class="title">${pageObject.content[i].activityName}</h4>
					<p class="date" style="font-size: 20px">
						<fmt:formatDate value="${pageObject.content[i].activityStartUpTime}" pattern="yyyy-MM-dd" />
						~
						<fmt:formatDate value="${pageObject.content[i].activityEndTime}" pattern="yyyy-MM-dd" />
					</p> <a href="${pageObject.content[i].activityLink}" target="_blank" >
					<p class="content">${pageObject.content[i].activityContent}</p>
					</a>
				</li>
			</c:forEach>
		</ul>
		<div class="pageNum">
			頁次：<c:forEach begin="1" end="${pageObject.totalPages}" step="1" var="i">
					<c:choose>
						<c:when test="${pageObject.number+1==i}">
							<font style="color: aquamarine;">${i}</font>							
						</c:when>
						<c:otherwise>
							<a href="${root}/activity/eventpage?page=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
		</div>
	</div>

</body>
</html>