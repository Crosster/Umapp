<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<meta charset="UTF-8">
<title>Login</title>
<style>*{ margin: 3px; padding: 3px;}
.box{box-shadow: 3px 3px  10px gray; background-color: rgba(255, 127, 80, 0.555);}
body {
        background-image: url('${root}/img/kids6.jpg');
        background-size: cover ; /* 调整背景图片的尺寸以覆盖整个元素 */
        background-position: center top; /* 将背景图片定位于元素的中心 */
        background-repeat: no-repeat;
        
    }
  
  

</style>

<script type="text/javascript">
function validateForm() {
    var account = document.forms["loginForm"]["email"].value;
    var password = document.forms["loginForm"]["password"].value;
    if (email == "" || password == "") {
        alert("帳號或密碼不得為空白");
        return false;
    }
}

</script>
</head>
<body>
<div class="container">
<div class="m-auto w-75 text-center  ">

    
    <h1>Login</h1>
    <div class="box m-auto w-50 " >
        <form name="loginForm" method="post" action="" onsubmit="return validateForm()">
            
            <div class="mb-3 ">
                <label for="email ">email:</label>
                <input  class="form-control shadow-lg " type="email" id="email" name="email" ><br>
            </div>
            <div class="mb-3">
                <label  class="form-label" for="password">Password:</label>
                <input class="form-control shadow-lg " type="password" id="password" name="password"><br>
            </div>
            <button class="btn btn-warning" type="submit">登入</button>
            
            <a href="${root}/members/addmember"><button class="btn btn-warning" type="button">註冊 </button></a>
            
            
            
            <c:if test="${error eq 'true'}">
                <p style="color: red">輸入的帳號或密碼有錯誤請重新輸入</p>
            </c:if>
        
        </form>
        
        <button type="button" onclick="fillCredentials()">使用者1</button>
        <button type="button" onclick="fillCredentials2()">使用者2</button>
      	<button type="button" onclick="fillCredentials3()">使用者3</button>
		<button type="button" onclick="fillCredentials4()">使用者4</button>
    </div>
</div>
</div>
    
    
</body>
<script>

function fillCredentials3() {
	  document.getElementById("email").value = "bbb@ccc.com";
	  document.getElementById("password").value = "passw0rd";
}
function fillCredentials4() {
	  document.getElementById("email").value = "qq@example.com";
	  document.getElementById("password").value = "1234";
}
</script>
</html>