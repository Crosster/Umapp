<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>編輯貼文</title>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />

<style type="text/css">
.haedp1{
        width: 70%;
        height: 70%;
        
    }
</style>
</head>

<body>
<jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
<h1>編輯訊息</h1>

 <!-- ======= End Page Header ======= -->
    <div class="page-header d-flex align-items-center">
      <div class="container position-relative">
        <div class="row d-flex justify-content-center">
          <div class="col-lg-6 text-center">
            <h2>編輯</h2>
            

          </div>
        </div>
      </div>
    </div><!-- End Page Header -->

      <div class="container">

        <div class="row gy-4 justify-content-center">
          <div class="col-lg-4">
            <img src="" class="img-fluid" alt="">
          </div>
          <div class="col-lg-9 content">
            
            
          
            <div class="py-5">
             <div class="container">
<div class="row justify-content-center">
<div class="col-10 col-md-9">


<form:form modelAttribute="Photos" method="post" action="" enctype="multipart/form-data">
 <form:input type="hidden" path="pid"/>
 <form:label path="">標題:</form:label>
  <form:input type="text" path="title"/><br/>
  <form:label path="">內容:</form:label>
<form:textarea class="form-control" path="text"/>
<br/>
<div>圖片：</div>
  <div>
    <form:input type="file" path="pictureContent" id="pictureContent" name="pictureContent"/>
  </div>
  <div>
    <img alt="圖片預覽" id="preview" src="${root}/showpictur/${Photos.pid}" width="200" height="200">
  </div>  
  <br/>
<button type="submit" class="btn btn-outline-primary">送出</button>

 <input name="nowphPage" value="${nowphPage}" style="display:none;"/>
</form:form>
</div>
</div>
</div>
         </div>
            
          </div>
        </div>
</div>
 



<jsp:include page="../layout/footer.jsp" />
</body>
<script>
  const photo = document.getElementById('pictureContent');
  const preview = document.getElementById('preview');

  photo.addEventListener('change', function() {
    const photoContent = this.files[0];
    if (photoContent != undefined) {
      const temPath = URL.createObjectURL(photoContent);
      preview.src = temPath;
    } else {
      preview.src = '';
    }
  });

  preview.addEventListener('click', function() {
    photo.click();
  });
</script>

</html>