<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <!-- 快速配對卡片css -->
            <style>
                .card {
                    color: #000;
                    margin: auto;
                }

                #quick-match-img {
                    object-fit: cover;
                    max-width: 100%;
                    height: 300px;
                    width: 300px;
                }

                /* .status-circle-online {
                    margin: 2px;
                    width: 13px;
                    height: 13px;
                    border-radius: 50%;
                    background-color: rgb(0, 255, 0);
                    border: 1px solid white;
                }

                .status-circle {
                    margin: 2px;
                    width: 13px;
                    height: 13px;
                    border-radius: 50%;
                    background-color: rgb(255, 0, 0);
                    border: 1px solid white;
                } */

                .match-rate {
                    width: 50px;
                    height: 20px;
                    border-radius: 3px;
                    background-color: rgb(255, 255, 255, 0.7);
                    text-align: center;
                    font-size: small;
                    font-weight: bolder;
                    position: absolute;
                    bottom: 5px;
                    right: 5px;
                }
            </style>

        </head>

        <body>
            <main>
                <!-- 快速配對結果 -->


                <div class="">
                    <h2 id="match-level-msg"></h2>
                    <c:if test="${logingetId}">
                        <a href="${root}/match/multiSearchIfBuy" class="btn btn-warning">多條件搜尋(黃金會員獨享)</a>
                    </c:if>
                    <div id="goldenBtnshow"></div>
                </div>


                <div class="container-fluid">
                    <h3 id="match-fail-msg"></h3>
                    <div id="myquick-match-result" class="row gy-4 justify-content-center">

                    </div>

                </div>
            </main>


            <script>

                //判斷會員，改變url
                //randomResult(不應該顯示配對率)
                //normalUserResult
                //goldenUserResult
                var quickMatchUrl = "";
                var logInMemberLevel = "${member.level}";
                var memberId = "${member.id}";
                //找已邀請的好友
                var InviteFriendList;
                function getInviteFriendList() {
                    $.ajax({
                        url: '${root}/friend/get/' + memberId + '/invite',
                        type: 'GET',
                        dataType: 'json',
                        async: false,
                        success: function (friendList) {
                            console.log(friendList);
                            InviteFriendList = friendList;

                        },
                        error: function () {
                            alert('Error loading friend list.');
                        }
                    });
                }

                if (logInMemberLevel === "" || logInMemberLevel === null || typeof logInMemberLevel === 'undefined') {
                    quickMatchUrl = "${root}/randomResult";
                    $("#match-level-msg").text("遊客您好，登入可享有配對功能");
                } else if (logInMemberLevel == 1) {
                    quickMatchUrl = "${root}/normalUserResult";
                    $("#match-level-msg").text("一般會員您好");
                    getInviteFriendList();
                } else if (logInMemberLevel == 2) {
                    quickMatchUrl = "${root}/goldenUserResult/70";
                    $("#match-level-msg").text("高級會員您好");
                    getInviteFriendList();
                    $("#goldenBtnshow").html(`<button class="btn btn-light" onclick="goldenUserResult(70)">70%以上</button>
                                              <button class="btn btn-light" onclick="goldenUserResult(50)">50%以上</button>
                                              <button class="btn btn-light" onclick="goldenUserResult(0)">一般配對</button>`)
                } else if (logInMemberLevel == 4) {
                    //管理員用4
                    $("#match-level-msg").text("管理員您好");
                    quickMatchUrl = "${root}/normalUserResult";
                    getInviteFriendList();
                } else {
                    console.log("錯誤的身分組格式");
                    console.log("Your member.level is" + logInMemberLevel);
                }

                const myquickMatchResult = document.getElementById("myquick-match-result");
                $.ajax({
                    url: quickMatchUrl,
                    type: "GET",
                    success: function (quickpage) {
                        console.log(quickpage);

                        // console.log(response);
                        // console.log(response.pageList);
                        // console.log(response.pageList[0].id);
                        // console.log("size" + response.pageList.length);
                        // console.log(response.pageList[0].member);

                        currentPage = quickpage.page + 1;
                        // $("#currentPage").text(currentPage);



                        let output = "";

                        if (quickpage.pageList.length == 0) {
                            $("#match-fail-msg").text("找不到適合的對象，更改個人偏好條件能幫您找到適合對象")
                            $("#match-fail-msg").addClass("text-warning")
                        }
                        for (let i = 0; i < quickpage.pageList.length; i++) {

                            //得到年齡
                            function calculateAge(birthday) {
                                var birthDate = new Date(birthday);
                                var today = new Date();

                                var age = today.getFullYear() - birthDate.getFullYear();
                                var m = today.getMonth() - birthDate.getMonth();
                                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                                    age--;
                                }
                                return age;
                            }
                            //沒登入用pageHolder<member>，登入是用DTO< pageHolder<member> , matchRate >
                            //因為上面的區分所以下面要改
                            var logInMember = "${member.level}";
                            if (logInMember === "" || logInMember === null || typeof logInMember === 'undefined') {
                                var quickPL = quickpage.pageList[i];
                                var mrrrate = "";
                                var inviteBtn = "";
                            } else {
                                var quickPL = quickpage.pageList[i].member;
                                //登入才顯示配對率
                                var mrrrate = `<div class="match-rate"><i class="bi bi-heart"></i>` + quickpage.pageList[i].matchRate + `%</div></div>`;
                                var inviteBtnText = "邀請"
                                var inviteBtnAddClass = ""
                                for (j = 0; j < InviteFriendList.length; j++) {
                                    if (InviteFriendList[j].member2.id == quickPL.id) {
                                        inviteBtnAddClass = "disabled"
                                        inviteBtnText = "已邀請"
                                        break;
                                    }
                                }
                                var inviteBtn = `<button class="btn btn-primary ` + inviteBtnAddClass + `" onclick="friendInviteAct(this,` + '${member.id}' + ',' + quickPL.id + `)" style="position: absolute;bottom: 5px;right: 5px;">` + inviteBtnText + `</button>`;

                            }


                            // 定義字串陣列
                            var strings = [
                                "心情很好，正在尋找新朋友",
                                "想去旅行，尋找旅伴一同探索世界",
                                "期待新的開始，迎接人生的挑戰",
                                "正在學習新技能，追求個人成長",
                                "感到幸福快樂，希望與他人分享",
                                "尋找愛情，渴望與對方共度一生",
                                "享受當下，珍惜每個美好的瞬間",
                                "渴望冒險，追求刺激和新奇的事物",
                                "追求夢想中，努力實現自己的目標",
                                "渴望友誼，希望結交新的朋友"
                            ];
                            // 產生隨機索引（0 到 4）
                            var randomIndex = Math.floor(Math.random() * strings.length);
                            // 選擇隨機字串
                            var randomString = strings[randomIndex];



                            var bday = quickPL.birthday

                            var cardbeforeHtml = `<div class="col-xl-3 col-lg-4 col-md-6">`;
                            var card = `<div class="card" style="width: 18rem;">
									<div style="position: relative;">
									<img id="quick-match-img"
									src="${root}/match/dphoto/` + quickPL.id + `"
									style="border-radius: 2px;" class="card-img-top">
									`+ mrrrate + `
									<div class="card-body">
									<h5 class="card-title">`+ randomString + `</h5>
									<div style="display: flex;">
									<div style="display: flex;align-items: center;">
									<div class='status-circle-online'></div>
									<p class="card-text">`+ quickPL.address + ` | ` + calculateAge(bday) + `歲</p>
									</div>
									</div>
									`+ inviteBtn + `	
									</div>
									</div>
									</div>`;
                            var cardafterHtml = `</div>`;
                            output += cardbeforeHtml;
                            output += card;
                            output += cardafterHtml;
                        }

                        myquickMatchResult.innerHTML = output;
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.error("Error: " + textStatus + ", " + errorThrown);
                    }
                });
                function friendInviteAct(btn, mid, cardfriendId) {
                    $.ajax({
                        type: 'GET',
                        url: `${root}/friend/add/` + mid + `/` + cardfriendId,
                        success: function (data) {
                            // $(btn).prop('disabled', true);
                            $(btn).addClass('disabled');
                            $(btn).text("已邀請");
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.error(textStatus, errorThrown);
                        }
                    });

                }
                function goldenUserResult(goldscore) {
                    quickMatchUrl = "${root}/goldenUserResult/" + goldscore;

                    $.ajax({
                        url: quickMatchUrl,
                        type: "GET",
                        success: function (quickpage) {
                            console.log(quickpage);

                            // console.log(response);
                            // console.log(response.pageList);
                            // console.log(response.pageList[0].id);
                            // console.log("size" + response.pageList.length);
                            // console.log(response.pageList[0].member);

                            currentPage = quickpage.page + 1;
                            // $("#currentPage").text(currentPage);



                            let output = "";

                            if (quickpage.pageList.length == 0) {
                                $("#match-fail-msg").text("找不到適合的對象，更改個人偏好條件能幫您找到適合對象")
                                $("#match-fail-msg").addClass("text-warning")
                            }else{
                                $("#match-fail-msg").text("")
                            }


                            for (let i = 0; i < quickpage.pageList.length; i++) {

                                //得到年齡
                                function calculateAge(birthday) {
                                    var birthDate = new Date(birthday);
                                    var today = new Date();

                                    var age = today.getFullYear() - birthDate.getFullYear();
                                    var m = today.getMonth() - birthDate.getMonth();
                                    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                                        age--;
                                    }
                                    return age;
                                }
                                //沒登入用pageHolder<member>，登入是用DTO< pageHolder<member> , matchRate >
                                //因為上面的區分所以下面要改
                                var logInMember = "${member.level}";
                                if (logInMember === "" || logInMember === null || typeof logInMember === 'undefined') {
                                    var quickPL = quickpage.pageList[i];
                                    var mrrrate = "";
                                    var inviteBtn = "";
                                } else {
                                    var quickPL = quickpage.pageList[i].member;
                                    //登入才顯示配對率
                                    var mrrrate = `<div class="match-rate"><i class="bi bi-heart"></i>` + quickpage.pageList[i].matchRate + `%</div></div>`;
                                    var inviteBtnText = "邀請"
                                    var inviteBtnAddClass = ""
                                    for (j = 0; j < InviteFriendList.length; j++) {
                                        if (InviteFriendList[j].member2.id == quickPL.id) {
                                            inviteBtnAddClass = "disabled"
                                            inviteBtnText = "已邀請"
                                            break;
                                        }
                                    }
                                    var inviteBtn = `<button class="btn btn-primary ` + inviteBtnAddClass + `" onclick="friendInviteAct(this,` + '${member.id}' + ',' + quickPL.id + `)" style="position: absolute;bottom: 5px;right: 5px;">` + inviteBtnText + `</button>`;

                                }


                                // 定義字串陣列
                                var strings = [
                                    "心情很好，正在尋找新朋友",
                                    "想去旅行，尋找旅伴一同探索世界",
                                    "期待新的開始，迎接人生的挑戰",
                                    "正在學習新技能，追求個人成長",
                                    "感到幸福快樂，希望與他人分享",
                                    "尋找愛情，渴望與對方共度一生",
                                    "享受當下，珍惜每個美好的瞬間",
                                    "渴望冒險，追求刺激和新奇的事物",
                                    "追求夢想中，努力實現自己的目標",
                                    "渴望友誼，希望結交新的朋友"
                                ];
                                // 產生隨機索引（0 到 4）
                                var randomIndex = Math.floor(Math.random() * strings.length);
                                // 選擇隨機字串
                                var randomString = strings[randomIndex];



                                var bday = quickPL.birthday

                                var cardbeforeHtml = `<div class="col-xl-3 col-lg-4 col-md-6">`;
                                var card = `<div class="card" style="width: 18rem;">
									<div style="position: relative;">
									<img id="quick-match-img"
									src="${root}/match/dphoto/` + quickPL.id + `"
									style="border-radius: 2px;" class="card-img-top">
									`+ mrrrate + `
									<div class="card-body">
									<h5 class="card-title">`+ randomString + `</h5>
									<div style="display: flex;">
									<div style="display: flex;align-items: center;">
									<div class='status-circle-online'></div>
									<p class="card-text">`+ quickPL.address + ` | ` + calculateAge(bday) + `歲</p>
									</div>
									</div>
									`+ inviteBtn + `	
									</div>
									</div>
									</div>`;
                                var cardafterHtml = `</div>`;
                                output += cardbeforeHtml;
                                output += card;
                                output += cardafterHtml;
                            }

                            myquickMatchResult.innerHTML = output;
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.error("Error: " + textStatus + ", " + errorThrown);
                        }
                    });
                }
            </script>

        </body>

        </html>