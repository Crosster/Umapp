<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stop Members</title>
<style type="text/css">
body {
        background-image: url('${root}/img/gallery-12.jpg');
        background-size: cover ; /* 调整背景图片的尺寸以覆盖整个元素 */
        background-position: center top; /* 将背景图片定位于元素的中心 */
        background-repeat: no-repeat;
       
    }
  

 .box{border: 2px solid rgba(255, 160, 122, 0); display: flex; flex-direction: row; justify-content: flex-start;flex-wrap: wrap; background-color:rgba(235, 118, 71, 0.247)  ;
     height:100%; width: 100%;  margin: auto;}
 </style>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_3.jsp" />
</head>
<body>
<jsp:include page="/WEB-INF/layout/managenavbar.jsp" />
<main id="main">
<div class="justify-content-center box">
<h1></h1>
<form:form   modelAttribute="members" method="post" action="" enctype="multipart/form-data" >
 <label class="form-label" for="level">會員狀態:</label>    
    <input type="hidden" name="id" value="${members.id}">
    <form:select path="level">
        <form:option value="">請選擇</form:option>       
        <form:option value="1" >開通</form:option>
        <form:option value="0" >停權</form:option>
    </form:select><br/>
 <input type="Submit" class="btn btn-danger" value="停權"onclick="confirmUpdate()">
 <input name="nowPage" value="${nowPage}" style="display:none;"/>
<button onclick="goBack()"  class="btn btn-secondary">返回</button> <!-- 添加取消按鈕 -->
</form:form>
</div>
</main>
<script>
 function goBack() {
        window.history.back(); // 返回上一頁
        
    }
 function confirmUpdate() {
     if (confirm('確認要進行停權嗎？')) { // 使用confirm()函数弹出确认窗口
         document.forms[0].submit(); // 确认后提交表单
     }
 }
 </script>
</body>
</html>