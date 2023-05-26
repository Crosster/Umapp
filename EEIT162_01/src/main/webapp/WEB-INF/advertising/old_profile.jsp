<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Advertiser Info</title>
</head>
<body>
    <h2>Advertiser Info</h2>        
        <p>Email: ${advertiser.email}</p>
        <p>Password: ${advertiser.password}</p>
        <p>Brand: ${advertiser.brand}</p>
        <p>Logo: <img src="data:image/png;base64,${logo}" alt="Advertiser Logo" style="width:100px"></p>
        <p>Company Name: ${advertiser.companyName}</p>
        <p>Unified Business No: ${advertiser.unifiedBusinessNo}</p>
        <p>Contact: ${advertiser.contact}</p>
        <p>Phone: ${advertiser.phone}</p>
        <p>Address: ${advertiser.address}</p>
        <p>Enabled: ${advertiser.enabled}</p>        
        <p>Created Time: <fmt:formatDate value="${advertiser.createdTime}" pattern="yyyy-MM-dd HH:mm"/></p>
        <input type="button" onclick="location.href='${root}/advertisers/logout'" value="logout"/>
        <input type="button" onclick="location.href='${root}/advertisers/change_password'" value="Change Password"/>
        <input type="button" onclick="location.href='${root}/advertisers/update'" value="Update"/>
        <input type="button" onclick="location.href='${root}/advertisements'" value="Show Advertisements"/>
        <input type="button" onclick="confirmDelete()" value="Delete Account"/>
        
        <script>
		    function confirmDelete() {
		        var r = confirm("確定刪除帳號嗎?");
		        if (r == true) {
		            // 使用者點擊了"OK"
		            location.href = '${root}/advertisers/delete';
		        } else {
		            // 使用者點擊了"Cancel"
		        }
		    }
		</script>
</body>
</html>