<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Update Advertiser</title>
</head>
<body>
    <h2>Update Advertiser</h2>
    <form action="${root}/advertisers/update" method="post" enctype="multipart/form-data">
        <input type="hidden" name="advertiserId" value="${advertiser.advertiserId}">
        <input type="hidden" name="password" value="${advertiser.password}"/>
        <input type="hidden" name="resetToken" value="${advertiser.resetToken}"/>
        <input type="hidden" name="loginAttempt" value="${advertiser.loginAttempt}"/>
        <input type="hidden" name="enabled" value="${advertiser.enabled}"/>
        <input type="hidden" name="createdTime" value="${advertiser.createdTime}"/>
        
        <table>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email" value="${advertiser.email}" readonly></td>
            </tr>
            <tr>
                <td>Brand:</td>
                <td><input type="text" name="brand" value="${advertiser.brand}" required></td>
            </tr>
            <tr>
                <td>Logo:</td>
                <td><input type="file" name="logoFile" id="file-input"><br><img id="preview" style= "width: 100px"/></td>                
            </tr>
            <tr>
                <td>Company Name:</td>
                <td><input type="text" name="companyName" value="${advertiser.companyName}" required></td>
            </tr>
            <tr>
                <td>Unified Business No:</td>
                <td><input type="text" name="unifiedBusinessNo" value="${advertiser.unifiedBusinessNo}" required></td>
            </tr>
            <tr>
                <td>Contact:</td>
                <td><input type="text" name="contact" value="${advertiser.contact}" required></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><input type="tel" name="phone" value="${advertiser.phone}" required></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address" value="${advertiser.address}" required></td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="Update">
                </td>
            </tr>
        </table>
    </form>
    
    <script>
	    document.getElementById('file-input').addEventListener('change', function(e) {
	        var reader = new FileReader();
	        reader.onload = function(e) {
	            document.getElementById('preview').src = e.target.result;
	        }
	        reader.readAsDataURL(e.target.files[0]);
	    });
    </script>
    
</body>
</html>