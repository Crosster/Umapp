<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Advertisements</title>
    <style>
	table, th, td {
	  border: 1px solid black;
	  border-collapse: collapse;
	}
	</style>
</head>
<body>
    <h2>Advertisements</h2>
    <c:if test="${not empty advertisements}">        
			<table>
			    <tr>
			        <th>Title</th>
			        <th>Picture</th>
			        <th>Link</th>
<!-- 			        <th>Category</th> -->
<!-- 			        <th>Target Audience Age</th> -->
<!-- 			        <th>Target Audience Sex</th> -->
			        <th>Status</th>
			        <th>Start Date</th>
			        <th>End Date</th>
			        <th>Created Time</th>
			    </tr>
			    
			    <c:forEach var="advertisement" items="${advertisements}">				
				        <tr>
				            <td>${advertisement.title}</td>				            			            
				            <td>          	
				                <img src="data:image/png;base64,${pictures[advertisement.advertisementId]}" alt="${advertisement.title}" style="width:200px">
				            </td>      
				            <td>
				                <a href="${advertisement.url}" target="_blank">Link</a>
				            </td>
<%-- 				            <td>${advertisement.category}</td> --%>
<%-- 				            <td>${advertisement.targetAudienceAge}</td> --%>
<%-- 				            <td>${advertisement.targetAudienceSex}</td> --%>
				            <td>${advertisement.status}</td>
				            <td><fmt:formatDate value="${advertisement.startDate}" pattern="yyyy-MM-dd" /></td>
				            <td><fmt:formatDate value="${advertisement.endDate}" pattern="yyyy-MM-dd" /></td>
				            <td><fmt:formatDate value="${advertisement.createdTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				        </tr>
				</c:forEach>
			</table>        
    </c:if>
    <c:if test="${empty advertisements}">
        <p>No advertisements found.</p>
    </c:if>
    <input type="button" onclick="location.href='${root}/advertisements/create'" value="Add Advertisement"/>
    <input type="button" onclick="location.href='${root}/advertisers/profile'" value="profile"/>
</body>
</html>