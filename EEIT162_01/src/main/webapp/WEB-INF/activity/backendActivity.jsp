<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp"/>
<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp"/>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<meta charset="UTF-8">
<title>活動後台管理頁面</title>
<style>
	.activityBackendImg{
		width:400px;
		height:200px;
	}
	.activityBackendTable{
		border-collapse:collapse;
	}
	.activityBackendTable td{
		border:solid grey 1px;
	}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
	
	<div style="padding-top: 150px;">
		<table class="activityBackendTable">
			<tbody>
				<tr>
					<td>id</td>
					<td>起始時間</td>
					<td>結束時間</td>
					<td>活動名稱</td>
					<td>活動內容</td>
					<td>費用</td>
					<td>logo</td>
					<!-- <td>連結</td> -->
					<td>類型</td>
					<td>修改</td>
					<td>刪除</td>
				</tr>
				<c:forEach items="${activityList}" var="ActivityBean">
					<tr>
						<td>${ActivityBean.activityId}</td>
						<td>${ActivityBean.activityStartUpTime}</td>
						<td>${ActivityBean.activityEndTime}</td>
						<td>${ActivityBean.activityName}</td>
						<td>${ActivityBean.activityContent}</td>
						<td>${ActivityBean.activityFee}</td>
						<td><img class="activityBackendImg" src="${pageContext.request.contextPath}/activity/showImg/${ActivityBean.activityId}"></td>
						<!-- <td>${ActivityBean.activityLink}</td> -->
						<td>${ActivityBean.activityType}</td>
						<td><form action="${root}/activity/updateActivityPage/${ActivityBean.activityId}"><button>修改</button></form></td>
						<td><form action="${root}/activity/delete/${ActivityBean.activityId}"><button>刪除</button></form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	

	
</body>
</html>