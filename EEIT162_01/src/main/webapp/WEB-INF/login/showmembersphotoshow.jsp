<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.5.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>.w-26{ width:20%; height: 20%;}

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
    .box{border: 2px solid rgba(255, 255, 255, 0)}

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
main{background-color: rgba(255, 255, 255, 0.685);}
body{  
        background-image: url('${root}/img/gallery-15.jpg');
        background-size: cover ; /* 调整背景图片的尺寸以覆盖整个元素 */
        background-position: center top; /* 将背景图片定位于元素的中心 */
        background-repeat: no-repeat;
        
    }

</style>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
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
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_3.jsp" />
</head>
<title>後台查詢貼文資料</title>
<body>
  <jsp:include page="/WEB-INF/layout/managenavbar.jsp" />
  
  
  <div class="container mt-4">
    <div class=".body1">
      
    </div>
    <main>
  
  <section id="portfolio" class="portfolio  ">
      <div class="container">
       <div class="section-title">

<div class="container mt-4">
    <div class="row row-cols-1 row-cols-md-2 g-4">
      <c:forEach items="${page.content}" var="picture">
        <div class="col">
          <div class="card-body box w-50 p-1 h-75 " >
            <div  onclick="showModal('${root}/showpictur/${picture.pid}')" >
            <div class="portfolio-wrap ">
                <img src="${root}/showpictur/${picture.pid}" class="card-img-top  " alt="Picture"  >

            
            <div class="card-body">
              <h5 class="card-title">${picture.title}</h5>
              <p class="card-text">${picture.text}</p>
              <p class="card-text">${picture.created_at}</p>
              <div class="d-flex justify-content-between">
                <div class="  portfolio-links ">
             
                  <form action="${root}/members/upmemberspicture/${page.number+1}">
                    <input type="hidden" name="pid" value="${picture.pid}" />
                    <input type="submit" class="btn btn-primary btn-sm me-2" value="編輯" />
                  </form>
                  <form action="${root}/members/admindeleteMembers/${page.number+1}" method="post">
                    <input type="hidden" name="pid" value="${picture.pid}" />
                    <input type="hidden" name="_method" value="delete" />
                    <input name="nowphPage" value="${nowphPage}" style="display:none;"/>
                    <input type="submit" class="btn btn-danger btn-sm" value="刪除" />
                  </form>
                
                </div>
               </div>
              </div>
            </div>
          </div>
            </div>
        </div>
      </c:forEach>
    </div>
  </div>


  
  
  <div class="pagination justify-content-center">

    <ul class="pagination">

        <c:forEach var="photopage" begin="1" end="${page.totalPages}">
            <c:choose>
                
                
                <c:when test="${page.number !=  photopage-1}">
                    
                    <a href="${contextRoot}showmembersphotoshow?show=${photopage}" class="page-link">${photopage}</a>
                </c:when>
                
                <c:otherwise>
                    <span class="page-link">${photopage}</span>
                </c:otherwise>
                
                
            </c:choose>
        </c:forEach>
    </ul>
</div>
</div>
</div>
    </section><!-- End About Section -->

</main>
</div> 
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.5.0/js/bootstrap.bundle.min.js"></script>


</html>