<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<style>.w-26{ width:100%; height: 100%;}
.box{ border: 2px solid rgba(255, 160, 122, 0); display: flex; flex-direction: row; justify-content: space-around;flex-wrap: wrap; background-color:rgba(235, 118, 71, 0.247)  ;
     height:50%; width: 50%;  margin: auto; } 
.item{border: 1px solid rgba(255, 255, 255, 0); width: 50%; height: 100%; margin: auto; text-align: center; }
.rr{border: 1px solid rgba(124, 120, 120, 0.466); width: 50%; height: 100%; margin: auto; text-align: center;}
.item2{border: 1px solid rgba(0, 0, 0, 0);  width: 50%; margin: auto}
.back{background-color: rgba(128, 128, 128, 0.5);}
.modal-container {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      background-color: rgba(0, 0, 0, 0.8);
      z-index: 9999;
    }
    .modal-content {
      max-width: 80%;
      max-height: 80%;
      overflow: auto;
    }
    .modal-image {
      width: 100%;
      height: 100%;
    }
</style>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
</head>
<script>
    
    function showModal(imageUrl) {
      // 創建 modal-container 元素
      var modalContainer = document.createElement('div');
      modalContainer.classList.add('modal-container');
  
      // 創建 modal-content 元素
      var modalContent = document.createElement('div');
      modalContent.classList.add('modal-content');
  
      // 創建 modal-image 元素
      var modalImage = document.createElement('img');
      modalImage.classList.add('modal-image');
      modalImage.src = imageUrl;
  
      // 添加 modal-image 到 modal-content
      modalContent.appendChild(modalImage);
  
      // 添加 modal-content 到 modal-container
      modalContainer.appendChild(modalContent);
  
      // 添加 modal-container 到 body
      document.body.appendChild(modalContainer);
  
      // 監聽 modal-container 點擊事件，點擊時關閉 modal
      modalContainer.addEventListener('click', function () {
        document.body.removeChild(modalContainer);
      });
    }

    
  </script>


<body>
<jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
<h1 style="padding-top: 150px">${members.username}個人的發文和照片</h1>
 <section id="gallery" class="gallery">
      <div class="container-fluid">
	<div class="row gy-4 justify-content-center">
            <c:forEach items="${page.content}" var="picture">
			<div class="col-xl-3 col-lg-4 col-md-6">
            <div class="gallery-item h-100">
            <div onclick="showModal('${root}/showpictur/${picture.pid}')">
              <img src="${root}/showpictur/${picture.pid}" class="img-fluid" alt="picture">
              </div>
              <div class="gallery-links d-flex align-items-center justify-content-center">
              <div class="card-body">
								<h5 class="card-title">標題:${picture.title}</h5>
								<p class="card-text">內容:${picture.text}</p>
								<p class="card-text">日期:${picture.created_at}</p>
								<div class="d-flex justify-content-between">
									<div>
										<c:if test="${members.username == picture.photousername}">
											<form action="${root}/members/uppicturemyself/${page.number+1}">
												<input type="hidden" name="pid" value="${picture.pid}" /> <input
													type="submit" class="btn btn-primary btn-sm me-2"
													value="編輯" />
											</form>
											<form action="${root}/members/myselfdelete/${page.number+1}"
												method="post">
												<input type="hidden" name="pid" value="${picture.pid}" /> <input
													type="hidden" name="_method" value="delete" /> <input
													name="nowphPagemyself" value="${nowphPagemyself}"
													style="display: none;" /> <input type="submit"
													class="btn btn-danger btn-sm" value="刪除" />
											</form>
										</c:if>
									</div>

									
								</div>
							</div>
              
                <div onclick="showModal('${root}/showpictur/${picture.pid}')" title="" class=" preview-link"><i class="bi bi-arrows-angle-expand"></i></div>
               
              </div>
            </div>
            
            
          	</div><!-- End Gallery Item -->
            </c:forEach>
          </div>
      </div>
    </section><!-- End Gallery Section -->


  
  <div class="pagination justify-content-center">

    <ul class="pagination">

        <c:forEach var="photopage" begin="1" end="${page.totalPages}">
            <c:choose>
                
                <c:when test="${page.number !=  photopage-1}">
                    
                    <a href="${contextRoot}photoshow?show=${photopage}"  class="page-link">${photopage}</a>
                </c:when>
                
                <c:otherwise>
                    <span class="page-link">${photopage}</span>
                </c:otherwise>                
            </c:choose>
        </c:forEach>
        
    </ul>
    </div>
    <jsp:include page="../layout/footer.jsp" />
</body>
<script>
    function likePhoto(button) {
        var pid = button.getAttribute("pid");
        fetch("${root}/likePhotos?pid=" + pid)
            .then(response => response.text())
            .then(data => {
                if (data === "liked") {
                    button.innerHTML = '<button class="btn btn-sm btn-outline-secondary" ><i class="fa fa-heart"></i></button>';
                } else if (data === "unLiked") {
                    button.innerHTML = '<button class="btn btn-sm btn-outline-secondary" ><i class="far fa-heart"></i></button>';
                }
            });
    }
</script>

</html>