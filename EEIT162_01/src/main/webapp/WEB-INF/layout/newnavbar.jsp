<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>約密</title>
    <meta content="" name="description">
    <meta content="" name="keywords">
    <script>
        $(document).ready(function(){
            if('${member.level}' !="8"){
                    document.getElementById('eventManger').style.display = 'none';
                }
            $.ajax({
                    url:'${root}/advertisements/place',
                    method:'GET',
                    success:function(response){
                        document.getElementById('adUrl').href = response.url;
                        document.getElementById('adImg').src = "data:image/jpeg;base64," + response.img;
                        if('${member.level}'=='2'){
                            document.getElementById('ad').style.display = 'none';
                            let adSection = document.getElementById('ad');
                            adSection.remove();
                        }
                    },
                    error:function(error){
                    }
            });
        });
    </script>
</head>
<body>
    <!-- ======= Header ======= -->
    <header id="header" class="header d-flex align-items-center fixed-top">
        
        <div class="container-fluid d-flex align-items-center justify-content-between">
        <a href="${root}/" class="logo d-flex align-items-center  me-auto me-lg-0">
            <img src="${root}/image/Relationship.png" alt="">
            <!-- Uncomment the line below if you also wish to use an image logo -->
            <!-- <i class="bi bi-camera"></i> -->
            <h1>與${member.username}的約密</h1>
        </a>
        <!-- <img src="${root}/showphoto/${member.id}" style="width: 60px;;border-radius:50%" alt=""> -->
        <nav id="navbar" class="navbar">

            <ul>
                <li><a href="${root}/" class="active">首頁</a></li>
                <li class="dropdown"><a href="#"><span>會員功能</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
                    <ul>
                        <li><a href="${root}/members/photowall">新增貼文</a></li>                
                        <li><a href="${root}/members/date">個人資料</a></li>
                        <li><a href="${root}/members/photoshow">相簿</a></li>
                        <li class="dropdown"><a href="#"><span>開通黃金會員</span><i class="bi bi-chevron-down dropdown-indicator"></i></a>
                            <ul>
                                <li><a href="${root}/member/level/pay">購買會員</a></li>
                                <li><a href="${root}/member/level/orderlist">購買紀錄</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li><a href="${root}/members/allphotoshow">心情</a></li>
                <li><a href="${root}/chat">聊聊</a></li>
                <li class="dropdown"><a href="#"><span>配對</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
                    <ul>
                        <li><a href="${root}/match/mdp">填寫個人偏好</a></li>
                        <li><a href="${root}/match/newmr">快速配對</a></li>
                        <li><a href="${root}/match/fl">好友名單</a></li>
                        <c:if test="${member.level==2}">
                            <li><a href="${root}/match/multiMatch">條件篩選</a></li>
                        </c:if>
                    </ul>
                </li>
                <li class="dropdown"><a href="#"><span>交誼活動</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
                    <ul>
                        <li class="dropdown"><a href="#" id="eventManger"><span>後台管理</span><i class="bi bi-chevron-down dropdown-indicator"></i></a>
                            <ul>
                                <li><a href="${root}/activity/backendManage">活動管理</a></li>
                                <li><a href="${root}/activity/insertPage">新增活動</a></li>
                            </ul>
                        </li>
                        <li><a href="${root}/activity/eventpage">近期活動</a></li>
                    </ul>
                </li>
                <li><a href="${root}/advertisers/login" target="_blank">廣告商專區</a></li>
                <li class="nav-item" >
                    <c:choose>
                        <c:when test="${logingetId}">
                            <a class="nav-link" href="${root}/members/logout"><button class="btn btn-secondary">登出</button></a>
                        </c:when>
                        
                        <c:otherwise>
                            <a class="nav-link " href="${root}/members/login"><button class="btn btn-secondary">登入</button></a>
                        </c:otherwise>
                        
                    </c:choose>
                </li>
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${logingetId}">    
                              
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" href="${root}/members/addmember"><button class="btn btn-secondary">註冊新帳號</button></a>
                        </c:otherwise> 
                         
                    </c:choose>
                </li>
            </ul>
        </nav><!-- .navbar -->

        <div class="header-social-links">
        </div>
        <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
        <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

        </div>
    </header><!-- End Header -->
	<!-- 廣告區 -->
    <section name="adSection" id="hero" class="hero d-flex flex-column justify-content-center align-items-center" data-aos="fade" data-aos-delay="1500">
        <div class="container" id="ad">
	        <div class="row justify-content-center" >
	            <div class="col-10">
	                <a href="" target="_blank" id="adUrl">
	                    <img id="adImg" src="" style="display:block; margin:auto; width: 100%;">
	                </a>
	            </div>
	        </div>
        </div>        
    </section>
    <!-- 廣告區 -->
    
</body>
</html>