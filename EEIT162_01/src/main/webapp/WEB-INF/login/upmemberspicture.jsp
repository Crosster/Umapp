<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台編輯貼文</title>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_3.jsp" />
<style> 

.box{ margin: auto;}
.boxbg{background-color: rgba(214, 214, 214, 0.603);}
body {
        background-image: url('${root}/img/gallery-15.jpg');
        background-size: cover ; /* 调整背景图片的尺寸以覆盖整个元素 */
        background-position: center top; /* 将背景图片定位于元素的中心 */
        background-repeat: no-repeat;
        
    }
</style>
</head>

<body>
<jsp:include page="/WEB-INF/layout/managenavbar.jsp" />
<div class="container">
<div class="row justify-content-center ">
<div class="col-9 col-md-6">
 <div class="shadow-lg p-3 mb-5  rounded boxbg  ">
<h1>編輯訊息</h1>

<table>
<tr>
<td></td>
</tr>


</table>


<form:form modelAttribute="Photos" method="post" action="" enctype="multipart/form-data">

 <form:input type="hidden" path="pid"/>標題:
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
</body>
<script>
  const photo = document.getElementById('photoContent');
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