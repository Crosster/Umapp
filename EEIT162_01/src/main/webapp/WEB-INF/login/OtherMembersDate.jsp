<%@page import="com.eeit162.FWBweb.daka.login.Members"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員個人資料編輯</title>
<jsp:include page="../component/common_dependencies.jsp" />
<jsp:include page="../component/common_dependencies_2.jsp" />
<style> .w-26{ width:200px; height: 200px;}
        .container11{width: 800px; margin: auto; border: 1px solid rgb(0, 0, 0)};
</style>
</head>
<body>
<jsp:include page="../layout/newnavbar.jsp" />
<div class="page-header d-flex align-items-center">
      <div class="container position-relative">
        <div class="row d-flex justify-content-center">
          <div class="col-lg-6 text-center">
            
            
          </div>
        </div>
      </div>
    </div><!-- End Page Header -->

<section id="about" class="about">
      <div class="container">

        <div class="row gy-4 justify-content-center">
          <div class="col-lg-6">
            <img src="${root}/showphoto/${members.id}" class="img-fluid" alt="">
          </div>
          <div class="col-lg-5 content">
          
            <form:form   modelAttribute="members" method="post" action="" enctype="multipart/form-data" >
            <div class="row">
              <div class="col-lg-9">
            <input type="hidden" id="id" name="id" value="${members.id }" required><br><br>
                <ul>
                
                                
				
				
				
				 <li><i class="bi bi-chevron-right"></i> <strong>Name:</strong><span>${members.username}</span>           
                 
                  <li><i class="bi bi-chevron-right"></i><strong>Birthday:</strong> <span>${members.birthday}</span>

                  
                </ul>
              </div>
              <div class="col-lg-9">
                <ul>
                <li><i class="bi bi-chevron-right"></i> <strong>Job:</strong><span>${members.job }</span>
			            
				</li>
                <li><i class="bi bi-chevron-right"></i> <strong>Gender</strong><span>${members.gender }</span>
			        
				</li>                  
                  <li><i class="bi bi-chevron-right"></i> <strong>Height:</strong>大約<span>${members.height}</span>公分</li>
                  <li><i class="bi bi-chevron-right"></i> <strong>Weight: </strong>大約<span>${members.weight}</span>公斤</li>
                   <li><i class="bi bi-chevron-right"></i> <strong>CityAddress:</strong> <span>${members.address}</span></li>
                </ul>
              </div>
           
          
            </div>          
      </form:form>
           <button onclick="goBack()"  class="btn btn-secondary">返回</button>
          </div>
        </div>

      </div>
    </section><!-- End About Section -->


<jsp:include page="../layout/footer.jsp" />
</body>
<script>

const photo =document.getElementById("photoContent");
const preview= document.getElementById("preview");

photo.addEventListener("change",function(){
  let photoContent=this.files[0];
  if(photoContent!=undefined){
    let temPath=URL.createObjectURL(photoContent);
    preview.src=temPath
  }
  if(photoContent ==undefined){
     preview.src=""
  }
})
preview.addEventListener("click",function(){
  photo.click()
})


function goBack() {
        window.history.back(); // 返回上一頁
        
    }


</script>
</html>