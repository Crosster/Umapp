<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8" />
<head>
<title>Home</title>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<script src="${root}/js/sockjs-0.3.4.js"></script>
<script src="${root}/js/stomp.js"></script>
</head>
<body>
	<form action="@{/loginchat}" method="post">
		<div>
			<label> Account : <input type="text" name="username" />
			</label>
		</div>
		<div>
			<label> Password: <input type="password" name="password" />
			</label>
		</div>
		<div>
			<input type="submit" value="Submit" />
		</div>
	</form>
</body>
</html>