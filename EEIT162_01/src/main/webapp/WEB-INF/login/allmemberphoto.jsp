<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
.w-26 {
	width: 20%;
	height: 20%;
}

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

.box {
	border: 2px solid rgba(255, 160, 122, 0)
}

.sidebar22 {
	position: fixed;
	top: 50%;
	right: 0;
	transform: translateY(-50%);
	width: 20%;
	background-color: #f1f1f1;
	padding: 10px;
	height: 70%;
}

.sidebar h3 {
	color: #333;
	font-size: 18px;
	margin-bottom: 10px;
}

.sidebar ul {
	list-style-type: none;
	padding: 0;
	margin: 0;
}

.sidebar ul li {
	margin-bottom: 5px;
}

.sidebar ul li a {
	text-decoration: none;
	color: #666;
}
</style>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
<script>
	function testFunc(pictureId){
		console.log(pictureId,members.id);
	}
    
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

</head>
<body>
	<jsp:include  page="/WEB-INF/layout/newnavbar.jsp" />

	<h1 style="padding-top: 150px">Welcome, ${members.username}</h1>


	<main>
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
								<h5 class="card-title">${picture.title}</h5>
								<p class="card-text">${picture.text}</p>
								<p class="card-text">${picture.created_at}</p>
								<div class="d-flex justify-content-between">
									<div>
										<c:if test="${members.username == picture.photousername}">
											<form action="${root}/members/uppicture/${page.number+1}">
												<input type="hidden" name="pid" value="${picture.pid}" /> <input
													type="submit" class="btn btn-primary btn-sm me-2"
													value="編輯" />
											</form>
											<form action="${root}/members/delete/${page.number+1}"
												method="post">
												<input type="hidden" name="pid" value="${picture.pid}" /> <input
													type="hidden" name="_method" value="delete" /> <input
													name="nowphPage" value="${nowphPage}"
													style="display: none;" /> <input type="submit"
													class="btn btn-danger btn-sm" value="刪除" />
											</form>
										</c:if>
									</div>

									<div class="like-button" pid="${picture.pid}" l_mid="${picture.members.id}" onclick="likePhoto(this)">
									    <button type="button" class="btn btn-sm btn-outline-secondary" value="${picture.pid}">
											<c:set var="endValue" value="0" scope="page"></c:set>
											<c:forEach items="${members.likes}" var="memberLike">
												<c:if test="${memberLike.photos.pid == picture.pid}">
													<c:set var="endValue" value="1" scope="page"></c:set>
												</c:if>
											</c:forEach>
											<c:choose>
													<c:when test="${endValue != 0}">
														<i class="fa fa-heart  fa-2xl"  style="color: #d11a1a;"></i>
													</c:when>
													<c:otherwise>
														<i class="far fa-heart  fa-2xl"  /></i>
													</c:otherwise>
												</c:choose>
									    </button>
									</div>
								</div>
							</div>
              
                <div onclick="showModal('${root}/showpictur/${picture.pid}')" title="" class=" preview-link"><i class="bi bi-arrows-angle-expand"></i></div>
                <a href="${root}/members/date/${picture.members.id}" class="details-link" ><i class="bi bi-link-45deg"></i></a>> 
              </div>
            </div>
            
            
          	</div><!-- End Gallery Item -->
            </c:forEach>
          </div>
      </div>
    </section><!-- End Gallery Section -->
<div class="pagination justify-content-center">
			<ul class="pagination ">
				<c:forEach var="photopage" begin="1" end="${page.totalPages}">
					<c:choose>
						<c:when test="${page.number !=  photopage-1}">
							 <a href="${contextRoot}allphotoshow?show=${photopage}"
								class="page-link "><button type="button" class="btn btn-secondary" >${photopage}</button></a>
						</c:when>
						<c:otherwise>
							<span class="page-link "><button type="button" class="btn btn-secondary" >${photopage}</button></span>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</div>

		

	
	</main>
	<jsp:include page="../layout/footer.jsp" />
</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.5.0/js/bootstrap.bundle.min.js"></script>
<script>
function likePhoto(button) {
	  var pid = button.getAttribute("pid");
	  var icon = button.querySelector('i');
	  var localStorageKey = 'likeStatus_' + pid;

	  if (localStorage.getItem(localStorageKey) === 'liked') {
	    // 如果按鈕的狀態是 'liked'
	    // 修改按鈕的內容為實心愛心
	    button.innerHTML = '<button class="btn btn-sm btn-outline-secondary"><i class="fa fa-heart   fa-2xl"size="lg"></i></button>';
	    // 更新 localStorage 中的狀態為 'liked'
	    localStorage.setItem(localStorageKey, 'liked');
	  } else {
	    // 如果按鈕的狀態是 'unLiked' 或未設置
	    // 修改按鈕的內容為空心愛心
	    button.innerHTML = '<button class="btn btn-sm btn-outline-secondary"><i class="far fa-heart  fa-2xl "size="lg"></i></button>';
	    // 更新 localStorage 中的狀態為 'unLiked'
	    localStorage.setItem(localStorageKey, 'unLiked');
	  }
	// 處理點讚後的相應邏輯
	  fetch("${root}/likePhotos?pid=" + pid)
	    .then(response => response.text())
	    .then(data => {
	      if (data === "liked") {
	        // 將 localStorage 中的狀態設置為 'liked'
	        localStorage.setItem(localStorageKey, 'liked');
	        // 修改按鈕的內容為實心愛心
	        button.innerHTML = '<button class="btn btn-sm btn-outline-secondary"><i class="fa fa-heart  fa-2xl" style="color: #d11a1a;"size="lg"></i></button>';
	      } else if (data === "unLiked") {
	        // 將 localStorage 中的狀態設置為 'unLiked'
	        localStorage.setItem(localStorageKey, 'unLiked');
	        // 修改按鈕的內容為空心愛心
	        button.innerHTML = '<button class="btn btn-sm btn-outline-secondary"><i class="far fa-heart  fa-2xl" ></i></button>';
	      }
	    });
	}
window.addEventListener('load', function() {
	  var likeButtons = document.querySelectorAll('.like-button');
	  likeButtons.forEach(function(likeButton) {
	    var pid = likeButton.getAttribute('pid');
	    var icon = likeButton.querySelector('i');
	    var localStorageKey = 'likeStatus_' + pid;

	    if (localStorage.getItem(localStorageKey) === 'liked') {
	      // 如果 localStorage 中的狀態是 'liked'
	      // 修改按鈕的內容為實心愛心
	      //icon.classList.remove('far');
	      //icon.classList.add('fa');
	    } else {
	      // 如果 localStorage 中的狀態是 'unLiked' 或未設置
	      // 修改按鈕的內容為空心愛心
	      //icon.classList.remove('fa');
	      //icon.classList.add('far');
	    }
	  });
	});
</script>





</html>