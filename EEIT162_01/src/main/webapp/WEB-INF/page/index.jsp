<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- <script src="${root}/js/index.js"></script> -->
<meta charset="UTF-8">
<title>${webName}</title>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
</head>
<body>
	<jsp:include page="/WEB-INF/layout/navbar.jsp" />
	<h1>hello ${member.username}:會員等級:${member.level}</h1>
</body>
</html>