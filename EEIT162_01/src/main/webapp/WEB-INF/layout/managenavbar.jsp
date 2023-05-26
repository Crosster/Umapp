<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_3.jsp" />
</head>
<body>
<!-- ======= Header ======= -->
  <header id="header">
    <div class="d-flex flex-column">

      <div class="profile">
        
        <h1 class="text-light">Management:${member.username}</h1>
        
      </div>

      <nav id="navbar" class="nav-menu navbar">
        <ul>
          <li><a href="${root}/members/adm" class="nav-link scrollto active"><i class="bx bx-home"></i> <span>Home</span></a></li>
          <li><a href="${root}/members/allmembers" class="nav-link scrollto"><i class="bx bx-user"></i> <span>所有會員</span></a></li>
          <li><a href="${root}/members/showmembersphotoshow" class="nav-link scrollto"><i class="bx bx-file-blank"></i> <span>所有貼文</span></a></li>
           <a class="nav-link" href="${root}/members/logout"><button class="btn btn-secondary">登出</button></a>
        </ul>
      </nav><!-- .nav-menu -->
    </div>
    
  </header><!-- End Header -->
</body>
</html>