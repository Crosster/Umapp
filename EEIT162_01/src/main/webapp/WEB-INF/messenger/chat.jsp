<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chat WebSocket</title>
    <jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
    <jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
    <link rel="stylesheet" href="${root}/css/chat.css">
    <script type="text/javascript">
            var stompClient = null;
            var userName = '${member.username}';
            var sendId = '${member.id}';
            var reciveId = null;
            var connectedList = null;
            function connectWithLoginSession(){
                if(stompClient == null) {

                    // var socket = new SockJS('/UM-app/chatroom');
                    stompClient = Stomp.over(new SockJS('/UM-app/chatroom'));
                    stompClient.connect({user:userName,userId:'${member.id}'}, function(frame) {
                        console.log('Connected: ' + frame);
                        //廣播連線成員
                        stompClient.subscribe('/topic/messages', function(connectionUser) {
                            showConnectionUser(JSON.parse(connectionUser.body));
                        });

                        // 私人
                        stompClient.subscribe('/user/${member.id}/subscribe', function(messageOutput) {
                            //ChatMessage(JSON.parse(messageOutput.body));
                            showHistoryMessage();
                        });
                    });
                    showChatFriendWithPreview();
                }

                
            }

            function addChatMessage(message){
                
            }


            function showHistoryMessage(){
                document.getElementById('responseBox').innerHTML='';
                let previewTime = document.getElementById('reciveTime_'+reciveId);
                let previewText = document.getElementById('reciver_'+reciveId);
                var sendJSON = JSON.stringify({    'id':'',
                                                   'message':'',
                                                   'addtime':'',
                                                   'readtime':'',
                                                   'messengerId':'${member.id}',
                                                   'fkMemberId':reciveId
                                                });
                $.ajax({
                    url:'${root}/chat/history',
                    method:'post',
                    contentType:'application/json',
                    dataType:'json',
                    data:sendJSON,
                    success:function(response){
                        console.log(response);
                        document.getElementById('responseBox').innerHTML;
                        let reciverPhoto = '${root}/showphoto/'+reciveId;
                        showMessageHistory(response,'${member.id}',reciverPhoto);
                        var elem = document.getElementById('responseBox');
                        elem.scrollTop = elem.scrollHeight;

                        if(response.length>0){
                            previewTime.innerText =response[response.length-1].addtime.split(' ')[0].replaceAll('-','/')
                            +' ' + response[response.length-1].addtime.split(' ')[1];
                            if(response[response.length-1].message.includes("data:image")){
                                previewText = '貼圖'
                            }else{
                                previewText.innerHTML = response[response.length-1].message;
                            }
                        }else{
                            previewTime.innerText = '';
                        }


                       
                    },
                    error:function(error){
                        console.log(error);
                    }
                });
            }



            function showChatFriendWithPreview(){
                $.ajax({
					url: '${root}/chat/chatlist/' + '${member.id}',
					type: 'GET',
					dataType: 'json',
					success: function (chatList) {
                        console.log(chatList);
                        chatList.forEach((item,index,chatList)=>{
                            let photoUrl = '${root}/showphoto/'+item.friendId;
                            addChatFriendWithPreview(item,photoUrl);
                        })						
					},
					error: function () {
						alert('Error loading friend list.');
					}
				});
            }

            function showChatFriend(){
                $.ajax({
					url: '${root}/friend/get/' + '${member.id}' + '/checked',
					type: 'GET',
					dataType: 'json',
					success: function (friendList) {
                        
                        friendList.forEach((item,index,friendList)=>{
                            let connectId = null;
                            let connetUserPhoto = '${root}/showphoto/';
                            let friendMember=null;
                            if(item.member1.id == '${member.id}'){
                                connectId = item.member2.id
                                friendMember = item.member2;
                            }else{
                                connectId = item.member1.id
                                friendMember = item.member1;
                            }
                            addChatFriend(friendMember,connetUserPhoto+connectId);

                        })						
					},
					error: function () {
						alert('Error loading friend list.');
					}
				});
            }

            function disconnect() {
                if(stompClient != null) {
                    stompClient.disconnect();
                }
                console.log("Disconnected");
            }

            function sendMessage() {
                var text = document.getElementById('text').value;                
                if(text != ''){
                    var sendJSON = JSON.stringify({'id':'',
                                                   'message':text,
                                                   'addtime':'',
                                                   'readtime':'',
                                                   'messengerId':'${member.id}',
                                                   'fkMemberId':reciveId
                                                });
                    stompClient.send("/app/singlechat2", {}, sendJSON);
                    document.getElementById('text').value = '';
                }
            }

            function sendImage(dataUrl){
                var sendJSON = JSON.stringify({    'id':'',
                                                   'message':dataUrl,
                                                   'addtime':'',
                                                   'readtime':'',
                                                   'messengerId':'${member.id}',
                                                   'fkMemberId':reciveId
                                                });
                $.ajax({
                        url:'${root}/chat/getimage',
                        method:'post',
                        contentType:'application/json',
                        dataType:'json',
                        data:sendJSON,
                        success:function(response){
                        },
                        error:function(error){
                        }
                });
                document.getElementById('responseBox').innerHTML = '';
                showHistoryMessage();
            }

            function showConnectionUser(connectionUser){

            }

            

            function showMessageOutput(messageOutput) {
                showHistoryMessage();
            }

            function broadcastConnectionUsers(){
                    $.ajax({
                    url:'${root}/chat/broadcastusers',
                    method:'post',
                    contentType:'application/json',
                    dataType:'json',
                    data:{'text':'text'},
                    success:function(response){
                    },
                    error:function(error){
                    }
                });
                showFriendList();                
            }

            function showFriendList(){
                $.ajax({
					url: '${root}/friend/get/' + '${member.id}' + '/checked',
					type: 'GET',
					dataType: 'json',
					success: function (friendList) {
                        
                        friendList.forEach((item,index,friendList)=>{
                            let connectId = null;
                            let connetUserPhoto = '${root}/showphoto/';
                            let friendMember=null;
                            if(item.member1.id == '${member.id}'){
                                connectId = item.member2.id
                                friendMember = item.member2;
                            }else{
                                connectId = item.member1.id
                                friendMember = item.member1;
                            }
                            addFriendUser2(friendMember,connetUserPhoto+connectId);
                        })						
					},
					error: function () {
						alert('Error loading friend list.');
					}
				});
            }

            function showFriendLatestChat(){
                $.ajax({
					url: '${root}/chat/history/latest/' + '${member.id}',
					type: 'GET',
					dataType: 'json',
					success: function (messageList) {
                        
                        messageList.forEach((item,index,messageList)=>{
                            console.log(item);
                            let connetUserPhoto = '${root}/showphoto/' + item.reciverId;
                            addFriendUser(item,connetUserPhoto);
                        })						
					},
					error: function () {
						alert('Error loading friend list.');
					}
				});
            }


            

            function showConnectedList(){
                showLatestMessage(connectedList);
            }

    </script>
</head>
<body onload="connectWithLoginSession()">
<!-- <jsp:include page="/WEB-INF/layout/navbar.jsp" /> -->
<jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
<div class="container py-5 px-4">
    <!-- <p>
        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#horizontalCollapse" aria-expanded="true" aria-controls="collapseExample">
            ChatRoom
        </button>
    </p>

    <div class="collapse width" id="horizontalCollapse">
        <div class="card card-body">
            Some placeholder content for the collapse component. This panel is hidden by default but revealed when the user activates the relevant trigger.
          </div>
    </div> -->
    <br>
    <div class="card card-body bg-dark">
        <div class="row rounded-lg overflow-hidden shadow bg-dark">
            <!-- Users box-->
            <div class="col-5 px-0 bg-dark">
              <div class="bg-dark">
                <div class="bg-dark px-4 py-2 bg-light">
                  <p class="h5 mb-0 py-1" style="color: rgb(127, 180, 255);">Online UMer</p>
                </div>
                <div class="messages-box">
                  <div class=" bg-dark list-group rounded-0" id="onlineUser">
                  </div>
                </div>
              </div>
            </div>
            <!-- Users box end-->

            <!-- <img src="${root}/showphoto/${member.id}" alt=""> -->
            
      
            <!-- Chat Box start-->
            <div class="col-7 px-0">
              <div class="px-4 py-5 chat-box bg-dark" id="responseBox"></div>
        
              <!-- Typing area -->
              <form action="#" class="bg-light" id="typingArea">
                <div class="input-group bg-dark">
                  <img style="width: 100px;" id="preview" src="" alt="">
                  <input id="text" type="text" placeholder="Type a message" aria-describedby="button-addon2" class="form-control py-4 text-white" style="border-radius: 10px;background-color: darkslategray;">
                  <div class="input-group-append bg-dark">
                      <!-- <input class="btn" type="file"> -->
                      <label class="btn btn-info" style="margin-left: 10px;">
                          <input  id="theFile" style="display: none;" type="file">
                          <i class="fa fa-photo"></i>&#x1F4E4;
                      </label>
                      <button id="button-addon2" type="submit" class="btn btn-link" onclick="sendMessage();"> <i class="fa fa-paper-plane"></i></button>
                  </div>
                </div>
              </form>
              <!-- Typing area end -->
        
            </div>
            <!-- Chat box end -->
          </div>
    </div>

    
    

   
</div>

<!-- jQuery start -->
<script>
    var activeClassName = ['active','text-white'];
    var nonActiveClassName = 'list-group-item-light';
    var pActiveClassName = ['font-italic','mb-0','text-small'];
    var pNonActiveClassName = ['font-italic', 'text-muted', 'mb-0', 'text-small'];
    $(document).on('click','a#connectedUser',function(e) {
        $(this).addClass(activeClassName).removeClass(nonActiveClassName).removeAttr('href');
        $(this)[0].children[0].children[1].children[1].classList = pActiveClassName;
        $(this).siblings().addClass(nonActiveClassName).removeClass(activeClassName).attr('href','#');
        if($(this).siblings().length>0){
        $(this).siblings()[0].children[0].children[1].children[1].classList = pNonActiveClassName;
        }
        reciveId = $(this)[0].children[1].innerHTML;
        showHistoryMessage();
    });
    let theFile = document.getElementById('theFile');
    let preview = document.getElementById('preview');
    let submitBtn = document.getElementById('button-addon2');
    theFile.addEventListener("change",function(){
        let f = this.files[0];
        let tempsrc = URL.createObjectURL(f);
        preview.src = tempsrc;
    })

    submitBtn.addEventListener("click",function(){
        let image = theFile.files[0];
        var base64string='';
        if(image != null){
            toDataURL(URL.createObjectURL(image),function(dataUrl){
                sendImage(dataUrl);
            });
        }
        $('#theFile').val('');
        preview.src = '';





    });


</script>
<!-- jQuery end -->
</body>
</html>