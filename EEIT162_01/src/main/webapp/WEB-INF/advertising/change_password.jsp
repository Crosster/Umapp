<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Change Password</title>
</head>
<body>
	<h2>Change Password</h2>
    
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    
    <c:if test="${not empty successMessage}">
   		<div class="alert alert-success">${successMessage}</div>
	</c:if>
    
<%--     <div id="successMessage" style="display:none">${successMessage}</div> --%>
<%--     <div id="errorMessage" style="display:none">${errorMessage}</div> --%>
    
    <form:form modelAttribute="passwordForm" action="${root}/advertisers/change_password" method="post">
        <table>
            <tr>
                <td>Old Password:</td>
                <td><form:password path="oldPassword" /></td>                
            </tr>
            <tr>
                <td>New Password:</td>
                <td><form:password path="newPassword" /></td>
            </tr>
            <tr>
                <td>Confirm New Password:</td>
                <td><form:password path="confirmNewPassword" /></td>                
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Submit" />
                    <input type="button" onclick="location.href='${root}/advertisers/profile'" value="profile"/>
                </td>
            </tr>
        </table>
    </form:form>
    
    
    
	<script type="text/javascript">
// 	window.onload = function() {
// 	    var successMessage = document.getElementById('successMessage').innerText;
// 	    var errorMessage = document.getElementById('errorMessage').innerText;
	
// 	    if (successMessage) {
// 	        alert(successMessage);
// 	    }
	
// 	    if (errorMessage) {
// 	        alert(errorMessage);
// 	    }
// 	}
	</script>

</body>
</html>