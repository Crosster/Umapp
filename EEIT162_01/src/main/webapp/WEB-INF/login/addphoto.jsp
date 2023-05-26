<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
<style> .w-25{ width:400px; height: 400px;  padding: 0; margin: 0; }  

.box{float: center;}
.boxbg{background-color: rgba(128, 128, 128, 0.438);}

</style>
</head>

<body>
  <jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
  <!-- ======= Hero Section ======= -->
  <section id="hero" class="hero d-flex flex-column justify-content-center align-items-center" data-aos="fade" data-aos-delay="1500">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-6 text-center">
          <h2>你可以把自己<span>當日的心情和圖片</span>分享出去 讓大家了解你的近況 </h2>
          
         
      </div>
    </div>
  </section><!-- End Hero Section -->
  
     

        <div class="">
          
          <div class="">
          
     <div class="container">
    <div class="m-auto w-75 text-center " >
  <div class="shadow-lg p-3 mb-5  rounded boxbg ">
    <h1>貼文</h1>
    <form method="post" action="" enctype="multipart/form-data" onsubmit="return validateForm()" >
    <div class="mb-3">
      <label>標題:</label>
      <input class="form-control shadow-sm p-3 mb-5 bg-body rounded  " type="text" id="title" name="title" value="" maxlength="20">
    </div>
    <div>
      <span>內容:</span>
      <textarea  class="form-control shadow-sm p-3 mb-5 bg-body rounded" aria-label="With textarea" type="text" id="text" name="text" value=""></textarea>
    </div>
    <div>	
      <input type="hidden" id="photousername" name="photousername" value="${members.username }">
    </div>
    <div>	
      <input type="hidden" id="created_at" name="created_at" value="{{ date | date('yyyy-MM-dd') }}">
    </div>
    
		
    <div>圖片：</div>
    
		<div class="mb-3 "><input class="form-control shadow-sm p-3 mb-5 bg-body rounded" type="file" id="pictureContent" name="pictureContent"></div>
    <div class=" box"><img style="float: center" alt="" id="preview" src="${root}/getPicture?pid=${p.pid}" class="img-thumbnail shadow-sm p-3 mb-5 bg-body rounded " ></div>
    
    <button type="submit" class="btn btn-outline-primary">送出</button>
    <a href="${root}/index/newpage"> <input type="button"  class="btn btn-outline-secondary" value="取消"></a>
	</form>
</div>
</div>
	
            
            
             
          </div>
        </div>

      </div>
     
<jsp:include page="../layout/footer.jsp" />
</body>
<script type="text/javascript">
const photo =document.getElementById("pictureContent");
const preview= document.getElementById("preview");

photo.addEventListener("change",function(){
  let photoContent=this.files[0];
  if(photoContent!=undefined){
    let temPath=URL.createObjectURL(photoContent);
    preview.src=temPath
  }
  if(photoContent ==undefined){
     preview.src=""
  }
})
preview.addEventListener("click",function(){
  photo.click()
})

var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();

    if(dd<10) {
        dd = '0'+dd
    } 

    if(mm<10) {
        mm = '0'+mm
    } 

    today = yyyy + '-' + mm + '-' + dd;
    document.getElementById("created_at").setAttribute("min", today);
    
    var now = new Date();
    var year = now.getFullYear();
    var month = ('0' + (now.getMonth() + 1)).slice(-2);
    var day = ('0' + now.getDate()).slice(-2);
    var today = year + '-' + month + '-' + day;
    document.getElementById('created_at').value = today;
    
    function validateForm() {
    	  var title = document.getElementById("title").value;
    	  var text = document.getElementById("text").value;
    	  var photo = document.getElementById("pictureContent").value;

    	  if (title.trim() === "" || text.trim() === "" || photo === "") {
    	    alert("標題、內容和圖片字段不能为空");
    	    return false; // 验证失败，阻止表单提交
    	  }

    	  return true; // 验证通过，允许表单提交
    	}

</script>

</html>