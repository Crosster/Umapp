<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台查詢所以有會員資料</title>

<style type="text/css">
body {
        background-image: url('${root}/img/gallery-12.jpg');
        background-size: cover ; /* 调整背景图片的尺寸以覆盖整个元素 */
        background-position: center top; /* 将背景图片定位于元素的中心 */
        background-repeat: no-repeat;
        
    }
  
 .headpic{width:5%; height: 5%;} 
 .box{border: 1px solid rgba(255, 160, 122, 0); display: flex; flex-direction: row; justify-content: flex-start;flex-wrap: wrap; background-color:rgba(235, 118, 71, 0.247)  ;
     height:100%; width: 100%;  margin: auto;}
 </style>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_3.jsp" />

</head>
<body>
<jsp:include page="/WEB-INF/layout/managenavbar.jsp" />
<div>
<main id="main">
  <section id="resume" class="resume">
</section><!-- End Resume Section -->
 <section id="portfolio" class="portfolio  ">
      <div class="container">
       <div class="section-title">
          <h2>All Members</h2>
         
          <div class=" ">
           <div class="card-body box">
           <c:forEach items="${page.content}" var="members">
          <div class="col-lg-4 col-md-6 portfolio-item filter-app  ">
           <div class="card" style="width: 18rem;">
            <div class="portfolio-wrap ">
   <div>${members.username}</div>
   <div class="card-text"> 
   <img  class="card-img-top" alt="photoContent" src="${root}/showphoto/${members.id}">
    </div>
   <div >${members.email}</div>
   <div >${members.created_at}</div>
   <div>${members.birthday}</div>
   <div>${members.gender}</div>
   <div>${members.address}</div>
   <div>${members.phone}</div>
  	
  	
   	<div class="  portfolio-links " >
     
                  <form  action="${root}/members/admupmemberdate/${page.number+1}">
                    <input type="hidden" name="id" value="${members.id}" />
                    <input  type="submit" class="btn btn-primary btn-sm me-2" value="編輯" />
                  </form>
                  <form action="${root}/members/stopmemberdate/${page.number+1}" >
                    <input type="hidden" name="id" value="${members.id}" />                    
                    <input   type="submit" class="btn btn-danger btn-sm" value="停權" />
                  </form>
                
   </div>
   </div>
    </div>
        </div>
   </c:forEach>
   </div>
  </div>      
        
     
 <div class="pagination justify-content-center">

    <ul class="pagination">

        <c:forEach var="memberspage" begin="1" end="${page.totalPages}">
            <c:choose>
                
                
                <c:when test="${page.number !=  memberspage-1}">
                    
                    <a href="${contextRoot}allmembers?show=${memberspage}" class="page-link">${memberspage}</a>
                </c:when>
                
                <c:otherwise>
                    <span class="page-link">${memberspage}</span>
                </c:otherwise>
                
                
            </c:choose>
        </c:forEach>
    </ul>
</div>
</div>
</div>
    </section><!-- End About Section -->
 </main>
 </div>
</body>
</html>