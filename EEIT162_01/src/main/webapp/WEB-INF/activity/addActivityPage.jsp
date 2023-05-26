<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

<jsp:include page="../layout/navbar.jsp"></jsp:include>

<div class="container">
	<h1>*~活動訊息~*</h1>

	<div class="card">
		<h5 class="card-header">Featured</h5>
		<div class="card-body">
			<form:form modelAttribute="ActivityBean">
				<form:textarea path="text"/>
				<button type="submit" class="btn btn-primary">送出</button>
			</form:form>
		</div>
		</div>





	</div>



</body>

<script type="text/javascript">
	const root = "${root}";
</script>

</html>