<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Add advertisement</title>
</head>
<body>
	<h2>Add advertisement</h2>
	
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
	
	<form method="POST" action="${root}/advertisements/create" enctype="multipart/form-data">
	    <label>Title: </label>
	    <input type="text" name="title" required /><br/> 
	
	    <label>Picture: </label>	    
	    <input type="file" name="pictureFile" id="file-input"><br/><img id="preview" style= "width: 200px"/><br/>
	    
	    <label>URL: </label>
	    <input type="text" name="url" required /><br/>
	    
	    <label>Start Date: </label>
	    <input type="date" id="start" name="startDate" onchange="calculateCost()" required /><br/>
	    
	    <label>End Date: </label>
	    <input type="date" id="end" name="endDate" onchange="calculateCost()" required /><br/>    
	    
	    <label>Plan: </label>
		<select id="plan" name="planId" onchange="calculateCost()" required>
		    <c:forEach var="plan" items="${plans}">
		        <option value="${plan.planId}" data-price="${plan.price}">時段: ${plan.period} - 價格: ${plan.price}元/日</option>
		    </c:forEach>
		</select>
		<br/>
		
		<div id="cost"></div>
		<input type="hidden" name="cost" id="costValue" value="">	
	    
	    <input type="submit" value="Check Out" />
	    <input type="button" onclick="location.href='${root}/advertisers/profile'" value="profile"/>
	</form>
	
	<script>
		function calculateCost() {
		    const startInput = document.getElementById('start');
		    const endInput = document.getElementById('end');
		    const planSelect = document.getElementById('plan');
		
		    // 如果開始結束日期和方案沒有全部選完，不要顯示試算結果
		    if (!startInput.value || !endInput.value || !planSelect.value) {
		        return;
		    }
		    
		    const startDate = new Date(startInput.value);		    
		    const endDate = new Date(endInput.value);
		    
		    const diffTime = Math.abs(endDate - startDate);
		    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
		    
		    const pricePerDay = planSelect.options[planSelect.selectedIndex].dataset.price;    
		    const cost = diffDays * pricePerDay;
		    console.log(cost);
		    
		    document.getElementById('cost').textContent = "廣告總金額: " + cost + "元";	
		    document.getElementById('costValue').value = cost;
		}
	</script>
	
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