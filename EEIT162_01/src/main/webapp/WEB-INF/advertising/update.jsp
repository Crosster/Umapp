<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

<!DOCTYPE html>
<html>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>約密-廣告商專區</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="../assets_ad/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../assets_ad/vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End Plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="../assets_ad/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="../assets_ad/images/favicon.png" />
  </head>
  <body>
    <div class="container-scroller">
      <!-- partial:../../partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
          <a class="sidebar-brand brand-logo" href="#"><img src="data:image/png;base64,${logo}" alt="Advertiser Logo" style="width:100px"></a>
        </div>
        <ul class="nav">
          <li class="nav-item profile">
            <div class="profile-desc">
              <div class="profile-pic">                
                <div class="profile-name">
                  <h5 class="mb-0 font-weight-normal">${advertiser.brand} - ${advertiser.contact}</h5><br>                                             
                </div>         
              </div>
              <a href="#" id="profile-dropdown" data-toggle="dropdown"><i class="mdi mdi-dots-vertical"></i></a>
              <div class="dropdown-menu dropdown-menu-right sidebar-dropdown preview-list" aria-labelledby="profile-dropdown">
                <a href="${root}/advertisers/profile" class="dropdown-item preview-item">
                  <div class="preview-thumbnail">
                    <div class="preview-icon bg-dark rounded-circle">
                      <i class="mdi mdi-settings text-primary"></i>
                    </div>
                  </div>
                  <div class="preview-item-content">
                    <p class="preview-subject ellipsis mb-1 text-small">廣告商資訊</p>
                  </div>
                </a>
                <div class="dropdown-divider"></div>
                <a href="${root}/advertisers/change_password" class="dropdown-item preview-item">
                  <div class="preview-thumbnail">
                    <div class="preview-icon bg-dark rounded-circle">
                      <i class="mdi mdi-onepassword  text-info"></i>
                    </div>
                  </div>
                  <div class="preview-item-content">
                    <p class="preview-subject ellipsis mb-1 text-small">更改密碼</p>
                  </div>
                </a>
                <div class="dropdown-divider"></div>
                  <a href="${root}/advertisers/logout" class="dropdown-item preview-item">
                    <div class="preview-thumbnail">
                      <div class="preview-icon bg-dark rounded-circle">
                        <i class="mdi mdi-logout text-danger"></i>
                      </div>
                    </div>
                    <div class="preview-item-content">
                      <p class="preview-subject mb-1">登出</p>
                    </div>
                  </a>                
            </div>
          </li>        
          
          <li class="nav-item menu-items">
            <a class="nav-link" href="${root}/advertisements/create">
              <span class="menu-icon">
                <i class="mdi mdi-playlist-play"></i>
              </span>
              <span class="menu-title">新增廣告</span>
            </a>
          </li>
          <li class="nav-item menu-items">
            <a class="nav-link" href="${root}/advertisements">
              <span class="menu-icon">
                <i class="mdi mdi-table-large"></i>
              </span>
              <span class="menu-title">廣告清單</span>
            </a>
          </li>              
              
        </ul>
      </nav>
      <!-- partial -->    
      
      
        
          <div class="card-body">
            <h4 class="card-title">廣告商資訊</h4>
            <br>            
            <form class="forms-sample" action="${root}/advertisers/update" method="post" enctype="multipart/form-data">
              
              <input type="hidden" name="advertiserId" value="${advertiser.advertiserId}">
              <input type="hidden" name="password" value="${advertiser.password}"/>
              <input type="hidden" name="resetToken" value="${advertiser.resetToken}"/>
              <input type="hidden" name="loginAttempt" value="${advertiser.loginAttempt}"/>
              <input type="hidden" name="enabled" value="${advertiser.enabled}"/>
              <input type="hidden" name="createdTime" value="${advertiser.createdTime}"/>

              <div class="form-group">
                <label for="exampleInputEmail3">信箱</label>
                <input type="email" class="form-control" id="exampleInputEmail3" name="email" value="${advertiser.email}" readonly>
              </div>              
              <div class="form-group">
                <label for="exampleInputName1">品牌</label>
                <input type="text" class="form-control" id="exampleInputName1" name="brand" value="${advertiser.brand}" required>
              </div>
              <div class="form-group">
                <label for="exampleInputName1">商標</label>
                <input type="file" class="form-control" name="logoFile" id="file-input"><br><img id="preview" style= "width: 200px"/>
              </div>
              <div class="form-group">
                <label for="exampleInputName1">公司名稱</label>
                <input type="text" class="form-control" id="exampleInputName1" name="companyName" value="${advertiser.companyName}" required>
              </div>
              <div class="form-group">
                <label for="exampleInputName1">統一編號</label>
                <input type="text" class="form-control" id="exampleInputName1" name="unifiedBusinessNo" value="${advertiser.unifiedBusinessNo}" required>
              </div>
              <div class="form-group">
                <label for="exampleInputName1">聯絡人</label>
                <input type="text" class="form-control" id="exampleInputName1" name="contact" value="${advertiser.contact}" required>
              </div>
              <div class="form-group">
                <label for="exampleInputName1">電話</label>
                <input type="tel" class="form-control" id="exampleInputName1" name="phone" value="${advertiser.phone}" required>
              </div>
              <div class="form-group">
                <label for="exampleInputName1">公司地址</label>
                <input type="text" class="form-control" id="exampleInputName1" name="address" value="${advertiser.address}" required>
              </div>

         <!--      <div class="form-group">
                <label for="exampleSelectGender">Gender</label>
                <select class="form-control" id="exampleSelectGender">
                  <option>Male</option>
                  <option>Female</option>
                </select>
              </div>   -->  
              <!-- <input type="submit" value="更新"> -->
              <button type="submit" class="btn btn-primary mr-2">儲存</button>              
            </form>
          </div>
        
        <script>
            document.getElementById('file-input').addEventListener('change', function(e) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    document.getElementById('preview').src = e.target.result;
                }
                reader.readAsDataURL(e.target.files[0]);
            });
        </script>      

        </div>
        <!-- main-panel ends -->
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
    <!-- Custom js for this page -->
    <!-- End custom js for this page -->
  </body>
</html>