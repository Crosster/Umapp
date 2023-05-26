<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
<meta charset="UTF-8">
<title>約密</title>
<script>
    function goPay(){
        // console.log('pay');
    }
</script>
</head>
<body>
    <jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
    <div>
        ${payForm};
    </div>
</body>
</html>