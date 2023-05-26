function addChatFriend(chatFriend,friendPhoto){
    console.log(chatFriend);
    let aClassName = ['bg-dark','list-group-item','list-group-item-action','list-group-item-light','rounded-0'];
    let dClassName = ['d-flex', 'align-items-center', 'justify-content-between','mb-3'];
    let pClassName = ['font-italic','text-muted', 'mb-0', 'text-small'];

    var divOnlineUser = document.getElementById('onlineUser');
    
    //好友方塊 a連結 start
    var aOnlineUser = document.createElement('a');
    for(let i =0;i<aClassName.length;i++){
        aOnlineUser.classList.add(aClassName[i]);
    }
    aOnlineUser.href='#';
    aOnlineUser.id = 'connectedUser';
    //好友方塊 a連結 end

    //好友方塊 a連結-div(media) start
    var divMedia = document.createElement('div');
    divMedia.classList.add('media');
    //好友方塊 a連結-div(media) end

    //好友方塊 a連結-div(media)-img start
    var userImg = document.createElement('img');
    userImg.src = friendPhoto;
    userImg.alt = 'user';
    userImg.width = '50';
    userImg.classList.add('rounded-circle');
    divMedia.appendChild(userImg);
    //好友方塊 a連結-div(media)-img end

    //好友方塊 a連結-div(media)-div(media-body) start
    var divMediabody = document.createElement('div'); 
    divMediabody.classList.add('media-body');
    divMediabody.classList.add('ml-4');
    //好友方塊 a連結-div(media)-div(media-body) end


    var divdflex = document.createElement('div'); 
    for(let i =0;i<dClassName.length;i++){
        divdflex.classList.add(dClassName[i]);
    }


    var h6User = document.createElement('h6');
    h6User.classList.add('mb-0');
    h6User.innerText = chatFriend.username;

    var h6Id = document.createElement('h6');
    h6Id.id = 'targetId';
    h6Id.hidden = true;
    h6Id.innerText = chatFriend.id;

    var smallTime = document.createElement('small');
    smallTime.classList.add('small');
    smallTime.classList.add('font-weight-bold');

    // if(chatFriend.messageTime!= null){
    //     if(connectUser.readTime==""){
    //         smallTime.innerText = connectUser.messageTime+" new";
    //     }else{
    //         smallTime.innerText = connectUser.messageTime;
    //     }
    // }else{
    //     smallTime.innerText = "";
    // }
    smallTime.innerText = chatFriend.lastLogindAt;

    smallTime.id = 'reciveTime_'+chatFriend.id;
    
    var pUser = document.createElement('p');
    // pUser.id = 'reciver_';
    pUser.id = 'reciver_'+chatFriend.id;

    for(let i =0;i<pClassName.length;i++){
        pUser.classList.add(pClassName[i]);
    }

    // if(connectUser.text!=null){
    //     if(connectUser.text.includes("data:image")){
    //         pUser.innerText = "貼圖";
    //     }else{
    //         pUser.innerText = connectUser.text;
    //     }
    // }
    // else{
    //     pUser.innerText = "快開始聊天~";
    // }

    pUser.innerText = "預覽文字";
    
    divdflex.appendChild(h6User);
    divdflex.appendChild(smallTime);
    divMediabody.appendChild(divdflex);
    divMediabody.appendChild(pUser);
    divMedia.appendChild(divMediabody);
    aOnlineUser.appendChild(divMedia);
    aOnlineUser.appendChild(h6Id);
    divOnlineUser.appendChild(aOnlineUser);



}


function addChatFriendWithPreview(chatFriend,friendPhoto){
    console.log(chatFriend);
    let aClassName = ['bg-dark','list-group-item','list-group-item-action','list-group-item-light','rounded-0'];
    let dClassName = ['d-flex', 'align-items-center', 'justify-content-between','mb-3'];
    let pClassName = ['font-italic','text-muted', 'mb-0', 'text-small'];

    var divOnlineUser = document.getElementById('onlineUser');
    
    //好友方塊 a連結 start
    var aOnlineUser = document.createElement('a');
    for(let i =0;i<aClassName.length;i++){
        aOnlineUser.classList.add(aClassName[i]);
    }
    aOnlineUser.href='#';
    aOnlineUser.id = 'connectedUser';
    //好友方塊 a連結 end

    //好友方塊 a連結-div(media) start
    var divMedia = document.createElement('div');
    divMedia.classList.add('media');
    //好友方塊 a連結-div(media) end

    //好友方塊 a連結-div(media)-img start
    var userImg = document.createElement('img');
    userImg.src = friendPhoto;
    userImg.alt = 'user';
    userImg.width = '50';
    userImg.classList.add('rounded-circle');
    divMedia.appendChild(userImg);
    //好友方塊 a連結-div(media)-img end

    //好友方塊 a連結-div(media)-div(media-body) start
    var divMediabody = document.createElement('div'); 
    divMediabody.classList.add('media-body');
    divMediabody.classList.add('ml-4');
    //好友方塊 a連結-div(media)-div(media-body) end


    var divdflex = document.createElement('div'); 
    for(let i =0;i<dClassName.length;i++){
        divdflex.classList.add(dClassName[i]);
    }


    var h6User = document.createElement('h6');
    h6User.classList.add('mb-0');
    h6User.innerText = chatFriend.friendName;

    var h6Id = document.createElement('h6');
    h6Id.id = 'targetId';
    h6Id.hidden = true;
    h6Id.innerText = chatFriend.friendId;

    var smallTime = document.createElement('small');
    smallTime.classList.add('small');
    smallTime.classList.add('font-weight-bold');

    // if(chatFriend.messageTime!= null){
    //     if(connectUser.readTime==""){
    //         smallTime.innerText = connectUser.messageTime+" new";
    //     }else{
    //         smallTime.innerText = connectUser.messageTime;
    //     }
    // }else{
    //     smallTime.innerText = "";
    // }
    smallTime.innerText = chatFriend.message.messageTime;

    smallTime.id = 'reciveTime_'+chatFriend.friendId;
    
    var pUser = document.createElement('p');
    // pUser.id = 'reciver_';
    pUser.id = 'reciver_'+chatFriend.friendId;

    for(let i =0;i<pClassName.length;i++){
        pUser.classList.add(pClassName[i]);
    }

    if(chatFriend.message.text!=null){
        if(chatFriend.message.text.includes("data:image")){
            pUser.innerText = "貼圖";
        }else{
            pUser.innerText = chatFriend.message.text;
        }
    }
    else{
        pUser.innerText = "快開始聊天~";
    }

    // pUser.innerText = chatFriend.message.text;
    
    divdflex.appendChild(h6User);
    divdflex.appendChild(smallTime);
    divMediabody.appendChild(divdflex);
    divMediabody.appendChild(pUser);
    divMedia.appendChild(divMediabody);
    aOnlineUser.appendChild(divMedia);
    aOnlineUser.appendChild(h6Id);
    divOnlineUser.appendChild(aOnlineUser);



}



















function addOnlineUser(connectUser,Url,userPhoto){
    var divOnlineUser = document.getElementById('onlineUser');
    var aOnlineUser = document.createElement('a');
    let aClassName = ['list-group-item','list-group-item-action','list-group-item-light','rounded-0'];
    let dClassName = ['d-flex', 'align-items-center', 'justify-content-between','mb-3'];
    let pClassName = ['font-italic','text-muted', 'mb-0', 'text-small'];
    for(let i =0;i<aClassName.length;i++){
        aOnlineUser.classList.add(aClassName[i]);
    }
    aOnlineUser.href='#';
    aOnlineUser.id = 'connectedUser';
    var divMedia = document.createElement('div');
    divMedia.classList.add('media');
    var userImg = document.createElement('img');
    userImg.src=userPhoto;
    userImg.alt = 'user';
    userImg.width = '50';
    userImg.classList.add('rounded-circle');
    divMedia.appendChild(userImg);
    var divMediabody = document.createElement('div'); 
    divMediabody.classList.add('media-body');
    divMediabody.classList.add('ml-4');
    var divdflex = document.createElement('div'); 
    for(let i =0;i<dClassName.length;i++){
        divdflex.classList.add(dClassName[i]);
    }
    var h6User = document.createElement('h6');
    h6User.classList.add('mb-0');
    h6User.innerText = connectUser[0];

    var h6Id = document.createElement('h6');
    h6Id.id = 'targetId';
    h6Id.hidden = true;
    h6Id.innerText = connectUser[1];

    var smallTime = document.createElement('small');
    smallTime.classList.add('small');
    smallTime.classList.add('font-weight-bold');
    smallTime.innerText = 'latestDate';
    var pUser = document.createElement('p');
    for(let i =0;i<pClassName.length;i++){
        pUser.classList.add(pClassName[i]);
    }
    // pUser.innerText = latestText;
    pUser.innerText = 'latestText';
    divdflex.appendChild(h6User);
    divdflex.appendChild(smallTime);
    divMediabody.appendChild(divdflex);
    divMediabody.appendChild(pUser);
    divMedia.appendChild(divMediabody);
    aOnlineUser.appendChild(divMedia);
    aOnlineUser.appendChild(h6Id);
    divOnlineUser.appendChild(aOnlineUser);

}

function addFriendUser(connectUser,userPhoto){
    var divOnlineUser = document.getElementById('onlineUser');
    var aOnlineUser = document.createElement('a');
    let aClassName = ['bg-dark','list-group-item','list-group-item-action','list-group-item-light','rounded-0'];
    let dClassName = ['d-flex', 'align-items-center', 'justify-content-between','mb-3'];
    let pClassName = ['font-italic','text-muted', 'mb-0', 'text-small'];
    for(let i =0;i<aClassName.length;i++){
        aOnlineUser.classList.add(aClassName[i]);
    }
    aOnlineUser.href='#';
    aOnlineUser.id = 'connectedUser';
    var divMedia = document.createElement('div');
    divMedia.classList.add('media');
    var userImg = document.createElement('img');
    userImg.src=userPhoto;
    userImg.alt = 'user';
    userImg.width = '50';
    userImg.classList.add('rounded-circle');
    divMedia.appendChild(userImg);
    var divMediabody = document.createElement('div'); 
    divMediabody.classList.add('media-body');
    divMediabody.classList.add('ml-4');
    var divdflex = document.createElement('div'); 
    for(let i =0;i<dClassName.length;i++){
        divdflex.classList.add(dClassName[i]);
    }
    var h6User = document.createElement('h6');
    h6User.classList.add('mb-0');

    h6User.innerText = connectUser.reciverName;

    var h6Id = document.createElement('h6');
    h6Id.id = 'targetId';
    h6Id.hidden = true;

    h6Id.innerText = connectUser.reciverId;

    var smallTime = document.createElement('small');
    smallTime.classList.add('small');
    smallTime.classList.add('font-weight-bold');

    if(connectUser.messageTime!= null){
        if(connectUser.readTime==""){
            smallTime.innerText = connectUser.messageTime+" new";
            console.log('unread');
        }else{
            smallTime.innerText = connectUser.messageTime;
        }

    }else{
        smallTime.innerText = "";
    }

    smallTime.id = 'reciveTime_'+connectUser.reciverId;
    
    var pUser = document.createElement('p');
    pUser.id = 'reciver_'+connectUser.reciverId;
    for(let i =0;i<pClassName.length;i++){
        pUser.classList.add(pClassName[i]);
    }

    if(connectUser.text!=null){
        if(connectUser.text.includes("data:image")){
            pUser.innerText = "貼圖";
        }else{
            pUser.innerText = connectUser.text;
        }
    }
    else{
        pUser.innerText = "快開始聊天~";
    }
    
    divdflex.appendChild(h6User);
    divdflex.appendChild(smallTime);
    divMediabody.appendChild(divdflex);
    divMediabody.appendChild(pUser);
    divMedia.appendChild(divMediabody);
    aOnlineUser.appendChild(divMedia);
    aOnlineUser.appendChild(h6Id);
    divOnlineUser.appendChild(aOnlineUser);

}

function addFriendUser2(connectUser,userPhoto){
    var divOnlineUser = document.getElementById('onlineUser');
    var aOnlineUser = document.createElement('a');
    // let aClassName = ['list-group-item','list-group-item-action','list-group-item-light','rounded-0'];
    let aClassName = ['bg-dark','list-group-item','list-group-item-action','list-group-item-light','rounded-0'];
    let dClassName = ['d-flex', 'align-items-center', 'justify-content-between','mb-3'];
    let pClassName = ['font-italic','text-muted', 'mb-0', 'text-small'];
    for(let i =0;i<aClassName.length;i++){
        aOnlineUser.classList.add(aClassName[i]);
    }
    aOnlineUser.href='#';
    aOnlineUser.id = 'connectedUser';
    var divMedia = document.createElement('div');
    divMedia.classList.add('media');
    var userImg = document.createElement('img');
    userImg.src=userPhoto;
    userImg.alt = 'user';
    userImg.width = '50';
    userImg.classList.add('rounded-circle');
    divMedia.appendChild(userImg);
    var divMediabody = document.createElement('div'); 
    divMediabody.classList.add('media-body');
    divMediabody.classList.add('ml-4');
    var divdflex = document.createElement('div'); 
    for(let i =0;i<dClassName.length;i++){
        divdflex.classList.add(dClassName[i]);
    }
    var h6User = document.createElement('h6');
    h6User.classList.add('mb-0');

    h6User.innerText = connectUser.username;

    var h6Id = document.createElement('h6');
    h6Id.id = 'targetId';
    h6Id.hidden = true;

    h6Id.innerText = connectUser.id;
    console.log(connectUser);

    var smallTime = document.createElement('small');
    smallTime.classList.add('small');
    smallTime.classList.add('font-weight-bold');

    if(connectUser.messageTime!= null){
        if(connectUser.readTime==""){
            smallTime.innerText = connectUser.messageTime+" new";
            console.log('unread');
        }else{
            smallTime.innerText = connectUser.messageTime;
        }

    }else{
        smallTime.innerText = "";
    }

    smallTime.id = 'reciveTime_'+connectUser.reciverId;
    
    var pUser = document.createElement('p');
    pUser.id = 'reciver_'+connectUser.id;
    for(let i =0;i<pClassName.length;i++){
        pUser.classList.add(pClassName[i]);
    }

    if(connectUser.text!=null){
        if(connectUser.text.includes("data:image")){
            pUser.innerText = "貼圖";
        }else{
            pUser.innerText = connectUser.text;
        }
    }
    else{
        pUser.innerText = "快開始聊天~";
    }
    
    divdflex.appendChild(h6User);
    divdflex.appendChild(smallTime);
    divMediabody.appendChild(divdflex);
    divMediabody.appendChild(pUser);
    divMedia.appendChild(divMediabody);
    aOnlineUser.appendChild(divMedia);
    aOnlineUser.appendChild(h6Id);
    divOnlineUser.appendChild(aOnlineUser);

}

function showMessageHistory(response,userId,reciverPhoto){

    for(let i = 0;i<response.length;i++){
        var divResponseBox = document.getElementById('responseBox');
        var divMedia = document.createElement('div');
        divMedia.classList.add('media');
        divMedia.classList.add('w-50');
        divMedia.classList.add('mb-3');
        divMedia.classList.add('bg-dark');
        if(response[i].messengerId == userId){
            divMedia.classList.add('ml-auto');
            divMedia.classList.add('ms-auto');
        }

        var imgSrc = document.createElement('img');
        imgSrc.src = reciverPhoto;
        imgSrc.alt = 'user';
        imgSrc.width = '50';
        imgSrc.classList.add('rounded-circle');

        var divMediabody = document.createElement('div');
        divMediabody.classList.add('media-body');
        divMediabody.classList.add('ml-3');
        divMediabody.classList.add('bg-dark');

        var divP = document.createElement('div');
        if(response[i].messengerId == userId){
            divP.classList.add('bg-primary');
        }else{
            divP.classList.add('bg-light');
        }
        divP.classList.add('rounded');
        divP.classList.add('py-2');
        divP.classList.add('px-3');
        divP.classList.add('mb-2');

        var pText = document.createElement('p');
        pText.classList.add('text-small');
        pText.classList.add('mb-0');

        if(response[i].messengerId == userId){
            pText.classList.add('text-white');
        }else{
            pText.classList.add('text-muted');
        }

        if(response[i].message.includes('data:image')){
            var imgMessage = document.createElement('img');
            imgMessage.src = response[i].message;
            imgMessage.width = '100';
            pText.appendChild(imgMessage);
        }
        else{
            pText.innerText = response[i].message;
        }


        
        var pTime = document.createElement('p');
        pTime.classList.add('small');
        pTime.classList.add('text-muted');
        pTime.innerText = response[i].addtime;


        divP.appendChild(pText);
        
        divMediabody.appendChild(divP);
        divMediabody.appendChild(pTime);

        if(response[i].messengerId != userId){
            divMedia.appendChild(imgSrc);
        }
        
        divMedia.appendChild(divMediabody);

        divResponseBox.appendChild(divMedia);

    }
}

function showLatestMessage(controllerurl){
    $.ajax({
        url:controllerurl,
        method:'post',
        contentType:'application/json',
        dataType:'json',
        data:{'text':'text'},
        success:function(response){
            console.log(response.message);
        },
        error:function(error){
        }
    })
}

function toDataURL(url,callback){
    var XHR = new XMLHttpRequest();
    XHR.onload = function(){
        var reader = new FileReader();
        reader.onloadend = function(){
            callback(reader.result);
        }
        reader.readAsDataURL(XHR.response);
    };
    XHR.open('GET',url);
    XHR.responseType = 'blob';
    XHR.send();
}