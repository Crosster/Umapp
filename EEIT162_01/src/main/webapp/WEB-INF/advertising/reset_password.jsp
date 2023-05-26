<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

<!DOCTYPE html>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>重設密碼</title>
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
                <h3 class="card-title text-left mb-3">重設密碼</h3>
                
                <c:if test="${not empty errorMessage}">
                  <div class="alert alert-danger">${errorMessage}</div>
                </c:if>
                
                <form action="" method="post">                                  
                  <div class="form-group">
                    <label>新密碼</label>
                    <input type="password" name="newPassword" class="form-control p_input" required />                                      
                  </div>
                  <div class="form-group">
                    <label>確認新密碼</label>
                    <input type="password" name="confirmNewPassword" class="form-control p_input" required />               
                  </div>                        
                  <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-block enter-btn">送出</button>
                  </div>        
                </form>
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