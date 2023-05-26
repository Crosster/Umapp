<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="${root}/js/index.js"></script>
<meta charset="UTF-8">
<title>${webName}</title>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
</head>
<body>
<<<<<<< HEAD
	<jsp:include page="/WEB-INF/layout/navbar.jsp" />
	
	<div class="container">
	
		<div>
			<label>Member 1</label>
			<input type="text" id = "message1">
			<button id="submitbtn1">Submit</button>
		</div>
		<div>
			<label>Member 2</label>
			<input type="text" id = "message2">
			<button id="submitbtn1">Submit</button>
		</div>
		
		<table class="mytable" id="list_table_json">
		 <thead>
		   <tr>
		     <th>留言內容</th>
		     <th>留言時間</th>
		     <th>聊天室ID</th>
		     <th>成員ID</th>
		   </tr>
		 </thead>
		 
		 <tbody>
		 </tbody>

		</table>

	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('#submitbtn1').click(function(e){
				var inputText = document.getElementById('message1').value
				var inputObject = {
					"text":inputText,
					"fMemberId":1,
    				"messengerId":1
				}

				var inputObjectString = JSON.stringify(inputObject);

				$.ajax({
					url:'${root}/messenger/addmessage',
					method:'post',
					contentType:'application/json',
					dataType:'json',
					data:inputObjectString,
					success:function(result){
						console.log(result);

						let trText = '<tr>';
						trText +='<td>' + result.message + '</td>';
						trText += '<td>'+ result.addtime + '</td>';
						trText += '<td>'+ result.messengerId + '</td>';
						trText += '<td>'+ result.fkmemberId + '</td>';
						trText +='</tr>';

						
						msg_data = '<tbody>'
						msg_data +='<tr>';
						msg_data +='<td>' + result.message + '</td>';
						msg_data += '<td>'+ result.addtime + '</td>';
						msg_data += '<td>'+ result.messengerId + '</td>';
						msg_data += '<td>'+ result.FKMemberId + '</td>';
						msg_data +='</tr>';
						
						/*
						$.each(result,function(index,value){
							msg_data +='<tr>';
							msg_data +='<td>' + value.message + '</td>';
							msg_data += '<td>'+ value.addtime + '</td>';
							msg_data += '<td>'+ value.messengerId + '</td>';
							msg_data += '<td>'+ value.FKMemberId + '</td>';
							msg_data +='</tr>';
						})
						*/
						msg_data += '</tbody>'
						
						var myTable = document.getElementById('list_table_json');
						
						let trNode = $.parseHTML(trText);
						
						$("table.mytable > tbody").append(trNode);

						
						
					},
					error:function(error){
						console.log(error);
					}
				})



			})
		})
	
	</script>
	
=======
<jsp:include page="/WEB-INF/layout/navbar.jsp" />
<h1>成功</h1>
>>>>>>> dennis
</body>
</html>