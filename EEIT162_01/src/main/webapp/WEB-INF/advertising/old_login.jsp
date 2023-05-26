<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
</head>
<body>
    <h2>Login</h2>
    
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    
    <form:form modelAttribute="advertiser" action="${root}/advertisers/login" method="post">
        <table>
            <tr>
                <td>Email:</td>
                <td><form:input path="email"/></td>
            </tr>
            <tr>
                <td>Password:</td>                
                <td><form:password path="password"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Login"/></td>
            </tr>
        </table>
    </form:form>

 	<p>Don't have an account? <a href="${root}/advertisers/register">Register</a></p>
 	<p>Forget your password? <a href="${root}/advertisers/request_password_reset">Reset</a></p>
 
    <script> 
//     function loginCheck(){
//         $.ajax({
//                    url: "${root}/advertisers/login",
//                    method: "post",
//                    contentType: "application/json",
//                    dataType:'json',
//                    data: JSON.stringify({
//                        "email": $("#email").val(),
//                        "password": $("#password").val(),
//                    }),
//                    success: function(data){                    
//                        //window.location.href = "advertiser";

//                        $('#loginInfo').hide();
//                        console.log(data);
//                    },
//                    error: function(textStatus, errorThrown){
//                        console.log("An error occurred: " + textStatus + ", " + errorThrown);
//                    }
//                });
//     }
    
//            $("#loginForm").submit(function(event){
//                event.preventDefault();
//                $.ajax({
//                    url: "${root}/advertisers/login",
//                    method: "POST",
//                    contentType: "application/json",
//                    data: JSON.stringify({
//                        "email": $("#email").val(),
//                        "password": $("#password").val(),
//                    }),
//                    success: function(data){
//                 	   $("#loginForm").hide();
//                        window.location.href = "${root}/advertisers/advertiser";
//                        console.log(data);
//                    },
//                    error: function(textStatus, errorThrown){
//                        alert("An error occurred: " + textStatus + ", " + errorThrown);
//                    }
//                });
//            });    
    </script>
</body>
</html>