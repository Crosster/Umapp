<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${webName}</title>
<style type="text/css"> .xxx{height: 5%; width: 5%;}</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="${root}/">Navbar${members.username}</a>
			<c:if test="${member.id>0}">
				<img class="xxx" alt="" src="${root}/showphoto/${member.id}">
			</c:if>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="${root}/">Home</a></li>
					
					<li class="nav-item"><a class="nav-link"
						href="${root}/members/date">個人資料</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/members/photowall">新增貼文</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/chat">Message</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/">Advertisement</a></li>
					<li class="nav-item"><a class="nav-link"
<<<<<<< HEAD
						href="${root}/activity/eventpage">交誼活動</a></li>
=======
					href="${root}/activity/eventpage">Event(活動頁面)</a></li>
>>>>>>> 826cbb7e9b684cf5b49c16d2782d34d15f7911c2
					<li class="nav-item"><a class="nav-link" 

						href="${root}/members/photoshow">自己所有的圖片</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/members/allphotoshow">大家的發文</a></li>
<<<<<<< HEAD
					<li class="nav-item"><a class="nav-link"
						href="${root}/activity/eventpage">Event(活動頁面)</a></li>
					<li class="nav-item"><a class="nav-link"
=======
						<li class="nav-item"><a class="nav-link"
						href="${root}/activity/addActPage">Event(活動頁面)</a></li>
						<li class="nav-item"><a class="nav-link"

>>>>>>> 826cbb7e9b684cf5b49c16d2782d34d15f7911c2
						href="${root}/members/photoshow">ALLPhoto</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/match/mr">Match</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root}/member/level/pay">購買會員</a></li>
						
             
<li class="nav-item" >
    <c:choose>
        <c:when test="${logingetId}">      
            <a class="nav-link" href="${root}/members/logout"><button>登出</button></a>           
        </c:when>
        
        <c:otherwise>
            <a class="nav-link " href="${root}/members/login"><button >登入</button></a>
        </c:otherwise> 
         
    </c:choose>
</li>		
<li class="nav-item">
    <c:choose>
        <c:when test="${logingetId}">     
              <a class="nav-link" href="${root}/"><button>註銷</button></a> 
        </c:when>
        <c:otherwise>
            <a class="nav-link" href="${root}/members/addmember"><button>註冊新帳號</button></a>
        </c:otherwise> 
         
    </c:choose>
</li>

		
				</ul>
			</div>
		</div>
	</nav>
</body>


</html>