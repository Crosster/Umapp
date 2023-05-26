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
            <h2>個人資料</h2>
            
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
                
                <li><i class="bi bi-chevron-right"></i><strong>Headportrait:</strong><input  class="" type="file" id="photoContent" name="photoContent" ><li>                
				<li><i class="bi bi-chevron-right"></i><img  id="preview" class="w-26 " src="${root}/showphoto/${members.id}"><li>              
                 <li><i class="bi bi-chevron-right"></i> <strong>Name:</strong><input type="text" id="username" name="username" value="${members.username }" required>
                  <li><i class="bi bi-chevron-right"></i><strong>Birthday:</strong> <input  type="date" id="birthday" name="birthday" value="${members.birthday}" required></li>
                  <li><i class="bi bi-chevron-right"></i> <strong>Phone:</strong> <input  type="tel" id="phone" name="phone" value="${members.phone}"placeholder="請輸入手機號碼" ></li>
                  <li><i class="bi bi-chevron-right"></i> <strong>Email:<input type="email" id="email" name="email" value="${members.email }" required></li>
                  
                </ul>
              </div>
              <div class="col-lg-9">
                <ul>
                <li><i class="bi bi-chevron-right"></i> <strong>Job:</strong>
			             <select    aria-label="size 3 select example" id="Job" name="Job">
						<option value="">請選擇職業</option>
						    <option value="工程師" ${members.job == '工程師' ? 'selected' : ''}>工程師</option>
						    <option value="教師" ${members.job == '教師' ? 'selected' : ''}>教師</option>
						    <option value="學生" ${members.job == '學生' ? 'selected' : ''}>學生</option>
						    <option value="醫生" ${members.job == '醫生' ? 'selected' : ''}>醫生</option>
						    <option value="上班族" ${members.job == '上班族' ? 'selected' : ''}>上班族</option>
						</select>
				</li>
                <li><i class="bi bi-chevron-right"></i> <strong>Gender</strong> 
			        <select   aria-label="size 3 select example" id="gender" name="gender">
						<option value="M" ${members.gender == 'M' ? 'selected' : ''}>male</option>
						<option value="F" ${members.gender == 'F' ? 'selected' : ''}>female</option>
						<option value="O" ${members.gender == 'O' ? 'selected' : ''}>other</option>
					</select>
				</li>                  
                  <li><i class="bi bi-chevron-right"></i> <strong>Height:</strong><input type="number" id="height" name="height" value="${members.height }"min="1"  max="250" step="1" >公分</li>
                  <li><i class="bi bi-chevron-right"></i> <strong>Weight: </strong><input type="number" id="weight" name="weight" value="${members.weight }" min="1"  max="200"  step="1">公斤</li>
                   <li><i class="bi bi-chevron-right"></i> <strong>CityAddress:</strong> <textarea  id="address" name="address">${members.address}</textarea></li>
                </ul>
              </div>
           <button  class="btn btn-outline-secondary" type="submit" onclick="return confirm('是否要更新?')">更新</button>
           <a href="${root}/index/newpage"> <input type="button"  class="btn btn-outline-secondary" value="取消"></a>
            </div>          
      </form:form>
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





</script>
</html>