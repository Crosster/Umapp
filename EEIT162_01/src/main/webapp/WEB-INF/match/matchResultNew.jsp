<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
    <jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
<meta charset="UTF-8">
<title>約密</title>
<script>
    $(document).ready(function () {
        if('${member.level}'=="2"){
            console.log(document.getElementById('ad'));
            document.getElementById('ad').style.display = 'none';
        }
    });
</script>
</head>
<body>
    <!-- ======= Navbar START ======= -->
    <jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
    <!-- ======= Navbar END ======= -->

    <main id="main" data-aos="fade" data-aos-delay="1500">

        <!-- ======= Gallery Section ======= -->
        <section id="gallery" class="gallery">

            <div class="container-fluid">

                <div class="row gy-4 justify-content-center">

                    <jsp:include page="/WEB-INF/match/matchResult.jsp" />
                    
                </div>
        
            </div>
        </section><!-- End Gallery Section -->
        
    </main><!-- End #main -->
    


</body>
</html>