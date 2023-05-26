<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta charset="UTF-8">
<title>logonBackend</title>

<style>
	
	.xxx {
	height: 5%;
	width: 5%;
	}
	
	.signup{
		max-width: 1250px;
		margin: auto;
		/* overflow: hidden; */
		position: relative;
		top: 175px;
		right: -350px;
		bottom:100px;
		font-size: 35px;
	}
	
	.button{
		max-width: 1250px;
		margin: auto;
		/* overflow: hidden; */
		position: relative;
		top: 25px;
		right: -90px;
		bottom:100px;
		font-size: 35px;
	}
	
	#account {
		max-width: 1250px;
		margin: auto;
		/* overflow: hidden; */
		position: relative;
		top: -45px;
		right: 0px;
		bottom:100px;
		font-size: 35px;
	}
</style>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="${root}/">Navbar${members.username}</a>
			<img class="xxx" alt="" src="${root}/showphoto/${member.id}">
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${root}/">首頁</a></li>

					
					<li class="nav-item"><a class="nav-link"
						href="${root}/activity/eventpage">近期活動</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/activity/loginBackendPage">後臺管理</a></li>

				</ul>
			</div>
		</div>
	</nav>
	<form class="signup" action="${root}/activity/checkLoginBackend" method="post">
	<table>
		<tbody>
			<tr id="account">
				<td>帳號:</td>
				<td><input type="text" name="account"></td>
			</tr>
			<tr>
				<td>密碼:</td>
				<td><input type="text" name="password"></td>
			</tr>
			<c:if test="${isAdmin == false}"><tr><td>帳號或密碼錯誤</td></tr></c:if>
		</tbody>
	</table>
	<button class="button">送出</button>
	</form>
	
	
</body>
</html>