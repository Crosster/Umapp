<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

<!DOCTYPE html>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>廣告商註冊</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="../assets_ad/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../assets_ad/vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="../assets_ad/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="../assets_ad/images/favicon.png" />
  </head>
  <body>
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="row w-100 m-0">
          <div class="content-wrapper full-page-wrapper d-flex align-items-center auth login-bg">
            <div class="card col-lg-4 mx-auto">
              <div class="card-body px-5 py-5">
                <h3 class="card-title text-left mb-3">註冊</h3>
                
                <c:if test="${not empty errorMessage}">
                  <div class="alert alert-danger">${errorMessage}</div>
                </c:if>
                
                <form:form modelAttribute="advertiser" action="${root}/advertisers/register" method="post">
                  <div class="form-group">
                    <label>信箱</label>
                    <form:input path="email" class="form-control p_input"/>                    
                  </div>                  
                  <div class="form-group">
                    <label>密碼</label>
                    <form:password path="password" class="form-control p_input"/>                    
                  </div>                  
                  <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-block enter-btn">註冊</button>
                  </div>                  
                  <p class="sign-up text-center">已經有帳號了嗎? <a href="${root}/advertisers/login">登入</a></p>                  
                </form:form>
              </div>
            </div>
          </div>
          <!-- content-wrapper ends -->
        </div>
        <!-- row ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="../assets_ad/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="../assets_ad/js/off-canvas.js"></script>
    <script src="../assets_ad/js/hoverable-collapse.js"></script>
    <script src="../assets_ad/js/misc.js"></script>
    <script src="../assets_ad/js/settings.js"></script>
    <script src="../assets_ad/js/todolist.js"></script>
    <!-- endinject -->
  </body>
</html>