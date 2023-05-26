<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
