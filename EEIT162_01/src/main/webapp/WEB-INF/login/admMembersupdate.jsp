<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台編輯個人資料</title>
<style type="text/css">
body {
        background-image: url('${root}/img/gallery-12.jpg');
        background-size: cover ; /* 调整背景图片的尺寸以覆盖整个元素 */
        background-position: center top; /* 将背景图片定位于元素的中心 */
        background-repeat: no-repeat;
       
    }
  

 .box{border: 1px solid rgba(255, 160, 122, 0); display: flex; flex-direction: row; justify-content: flex-start;flex-wrap: wrap; background-color:rgba(235, 118, 71, 0.247)  ;
     height:100%; width: 100%;  margin: auto;}
 </style>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_3.jsp" />
</head>
<body>
<jsp:include page="/WEB-INF/layout/managenavbar.jsp" />
<main id="main">

<div class="justify-content-center box">
<form:form   modelAttribute="members" method="post" action="${root}/members/admupmemberdate/${nowPage}" enctype="multipart/form-data" >
<input type="hidden" name="id" value="${members.id}">

<div>
    <form:input type="file" path="photoContent" id="photoContent" name="photoContent"/>
  </div>
<div>
    <img alt="圖片預覽" id="preview" src="${root}/showphoto/${members.id}" width="200" height="200">
 </div>

		<form:input type="hidden" path="email"/><br/>  
<label class="bi bi-chevron-right" for="username">用戶名稱:</label>
		<form:input type="text" path="username"/><br/>
<label class="bi bi-chevron-right" for="phone">電話:</label>
    <form:input type="tel" path="phone"/><br/>
    
    <label class="bi bi-chevron-right" for="gender">性別:</label>
    <form:select path="gender">
        <form:option value="">請選擇</form:option>
        <form:option value="M" >male</form:option>
        <form:option value="F" >female</form:option>
        <form:option value="O" >other</form:option>
    </form:select><br/>
    
    <label class="bi bi-chevron-right" for="birthday">生日:</label>
    <form:input type="date" path="birthday"/><br/>    
    
    
    <label class="bi bi-chevron-right" for="job">職業:</label>
    <form:input type="text" path="job"/><br/>
    
    <label class="bi bi-chevron-right" for="height" >身高大約:</label>
    <form:input type="number" path="height" min="1"  max="250" step="1"/><br/>
    
    <label class="bi bi-chevron-right" for="weight">體重大約:</label>
    <form:input type="number" path="weight" min="1"  max="250" step="1"/><br/>
    
    <label class="bi bi-chevron-right" for="address">地址:</label>
    <form:textarea type="text" path="address"/><br/>
    
    <input class="btn btn-secondary" type="Submit" value="更改" onclick="confirmUpdate()">
    <input name="nowPage" value="${nowPage}" style="display:none;"/>
    <button class="btn btn-secondary" onclick="goBack()">取消</button> <!-- 添加取消按鈕 -->
</form:form>
</div>
<script>
    function goBack() {
        window.history.back(); // 返回上一頁
    }
    function confirmUpdate() {
        if (confirm('確認要進行更改嗎？')) { // 使用confirm()函数弹出确认窗口
            document.forms[0].submit(); // 确认后提交表单
        }
    }
    
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
</main>
</body>
</html>