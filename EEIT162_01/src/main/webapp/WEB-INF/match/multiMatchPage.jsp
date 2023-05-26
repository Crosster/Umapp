<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>${webName }</title>

        <jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
        <jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
        <!-- 快速配對卡片css -->
        <style>
            .card {
                color: #000000;
                margin: auto;
            }

            #quick-match-img {
                object-fit: cover;
                max-width: 100%;
                height: 300px;
                width: 300px;
            }

            .status-circle-online {
                margin: 2px;
                width: 13px;
                height: 13px;
                border-radius: 50%;
                background-color: rgb(0, 255, 0);
                border: 1px solid white;
            }

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

    <body src="">
        <jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
        <div class="page-header d-flex align-items-center" style="padding-top: 100px;">
            <div class="container position-relative">
                <div class="row d-flex justify-content-center">
                    <div class="col-lg-6 text-center">
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <h2>條件篩選</h2>
            <div class="row">
                <div class="col-3 border border-light row" style="background-color: rgb(246, 159, 159);border-radius: 10px;padding: 10px;">
                    <div class="container">
                        <!-- <h2>多條件搜尋</h2> -->
                        <div class="form-group" style="display: flex;">
                            <div class="form-check" style="margin: 5px;">
                                <input type="checkbox" class="form-check-input" id="maleCheckbox">
                                <label class="form-check-label" for="maleCheckbox">男</label>
                            </div>
                            <div class="form-check" style="margin: 5px;">
                                <input type="checkbox" class="form-check-input" id="femaleCheckbox">
                                <label class="form-check-label" for="femaleCheckbox">女</label>
                            </div>
                        </div>
                        <div class="form-group" style="display: flex;">
                            <div class="form-check" style="margin: 5px;">
                                <input type="checkbox" class="form-check-input" id="smokerCheckbox">
                                <label class="form-check-label" for="smokerCheckbox">抽菸</label>
                            </div>
                            <div class="form-check" style="margin: 5px;">
                                <input type="checkbox" class="form-check-input" id="unsmokerCheckbox">
                                <label class="form-check-label" for="unsmokerCheckbox">不抽菸</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="heightRange">身高區間:</label>
                            <div class="input-group">
                                <input type="number" min="0" class="form-control" id="minHeight" placeholder="最低">
                                <input type="number" min="0" class="form-control" id="maxHeight" placeholder="最高">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="weightRange">體重區間:</label>
                            <div class="input-group">
                                <input type="number" min="0" class="form-control" id="minWeight" placeholder="最低">
                                <input type="number" min="0" class="form-control" id="maxWeight" placeholder="最高">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="ageRange">年齡區間:</label>
                            <div class="input-group">
                                <input type="number" min="18" class="form-control" id="minAge" placeholder="最低">
                                <input type="number" min="18" class="form-control" id="maxAge" placeholder="最高">
                            </div>
                        </div>
                        <button id="msres" onclick="msresClick(0)" class="btn btn-outline-primary">搜尋</button>
                        <div>
                            <button type="button" class="btn btn-warning" id="fillInButton1">一鍵填入</button>
                            <button type="button" class="btn btn-secondary" id="fillInButton2">一鍵填入</button>
                            <button type="button" class="btn btn-danger" id="fillInButton3">一鍵填入</button>
                        </div>
                    </div>
                </div>
                <div class="col-9">
                    <div class="row" style="display: flex;">
                        <div id="pageBtnBar" class="col-10"></div>
                        <div class="col-2">
                            每頁顯示：<input type="number" id="per" class="input" min="1" max="99" value="10">
                        </div>
                    </div>
                    <div id="searchResults" class="row">

                    </div>
                    <div id="pageBtnBar2" class="col-10"></div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/layout/footer.jsp" />

        <script>

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
                quickMatchUrl = "${root}/goldenUserResult";
                $("#match-level-msg").text("高級會員您好");
                getInviteFriendList();
            } else if (logInMemberLevel == 4) {
                //管理員用4
                $("#match-level-msg").text("管理員您好");
                quickMatchUrl = "${root}/normalUserResult";
                getInviteFriendList();
            } else {
                console.log("錯誤的身分組格式");
                console.log("Your member.level is" + logInMemberLevel);
            }


            function msresClick(userRedeemPage) {
                const maleCheckbox = $("#maleCheckbox").prop("checked");
                const femaleCheckbox = $("#femaleCheckbox").prop("checked");
                const smokerCheckbox = $("#smokerCheckbox").prop("checked");
                const unsmokerCheckbox = $("#unsmokerCheckbox").prop("checked");
                const minHeight = $("#minHeight").val();
                const maxHeight = $("#maxHeight").val();
                const minWeight = $("#minWeight").val();
                const maxWeight = $("#maxWeight").val();


                function ageToBirthdate(age) {
                    if(age == ""){
                        return "";
                    }
                    const currentDate = new Date(); // 取得當前日期
                    const currentYear = currentDate.getFullYear(); // 取得當前年份
                    const birthYear = currentYear - age; // 計算出生年份
                    const bddd = birthYear + "-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate()
                    // console.log(bddd);
                    return bddd;               
                }
                const minAge = $("#minAge").val();
                const maxAge = $("#maxAge").val();

                const pageNum = userRedeemPage;
                const pCount = $("#per").val();

                const data = {
                    male: maleCheckbox,
                    female: femaleCheckbox,
                    smoker: smokerCheckbox,
                    unsmoker: unsmokerCheckbox,
                    maxHeight: maxHeight,
                    minHeight: minHeight,
                    maxWeight: maxWeight,
                    minWeight: minWeight,
                    minAge: ageToBirthdate(minAge),
                    maxAge: ageToBirthdate(maxAge),

                    page: pageNum,
                    count: pCount,
                };


                // 看json資料
                // console.log(JSON.stringify(data));
                const myMultiMatchResult = document.getElementById("searchResults");
                const pageBtnBar = document.getElementById("pageBtnBar");

                $.ajax({
                    url: "${root}/api/multiSearchPage",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: function (myMultiSearchPage) {
                        // pageListHolder
                        // firstPage boolean 是否為第一頁
                        // lastPage boolean 是否為最後一頁
                        // page+1 當前頁數
                        // pageCount 總頁數
                        // pageList[] 當前頁面array資料
                        // source[] 總頁面array資料
                        // pageSize 設定的一頁的資料上限數量

                        // console.log(myMultiSearchPage);
                        currentPage = myMultiSearchPage.page + 1;
                        if (myMultiSearchPage.pageList.length == 0) {
                            alert("找不到對象");
                        }

                        //渲染分頁按鈕
                        renderPageBtn(myMultiSearchPage, pageBtnBar);

                        //渲染卡片開始
                        renderCard(myMultiSearchPage, myMultiMatchResult);



                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.error("Error: " + textStatus + ", " + errorThrown);
                    }
                });
            }
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

            function renderPageBtn(myMultiSearchPage, pageBtnBar) {

                let output = "";

                output += `<ul class="pagination justify-content-center">`;
                let disabled = myMultiSearchPage.firstPage ? "disabled" : "";
                output += `<li class="page-item ${disabled}">`;
                output += `<button class="page-link" onclick="msresClick(` + myMultiSearchPage.page + `)">上一頁</button>`;

                output += `</li>`;
                for (let i = 1; i <= myMultiSearchPage.pageCount; i++) {
                    let active = i == (myMultiSearchPage.page + 1) ? "active" : "";
                    output += `<li class="page-item ` + active + `"><button class="page-link" onclick="msresClick(` + i + `)">` + i + `</button></li>`;
                }

                disabled = myMultiSearchPage.page == (myMultiSearchPage.pageCount - 1) ? "disabled" : "";
                output += `<li class="page-item ${disabled}">`;

                output += `<button class="page-link" onclick="msresClick(` + (myMultiSearchPage.page + 2) + `)">下一頁</button>`;

                output += `</li>`;
                output += `</ul>`;
                pageBtnBar.innerHTML = output;
                document.getElementById("pageBtnBar2").innerHTML = document.getElementById("pageBtnBar").innerHTML;
            }



            function renderCard(myMultiSearchPage, myMultiMatchResult) {
                let output = "";

                for (let i = 0; i < myMultiSearchPage.pageList.length; i++) {
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
                    var logInMember = "${member.level}";
                    if (logInMember === "" || logInMember === null || typeof logInMember === 'undefined') {
                        var multiPL = myMultiSearchPage.pageList[i];
                        var inviteBtn = "";
                    } else {
                        var multiPL = myMultiSearchPage.pageList[i];
                        var inviteBtnText = "邀請"
                        var inviteBtnAddClass = ""

                        for (j = 0; j < InviteFriendList.length; j++) {
                            if (InviteFriendList[j].member2.id == multiPL.id) {
                                inviteBtnAddClass = "disabled"
                                inviteBtnText = "已邀請"
                                break;
                            }
                        }
                        var inviteBtn = `<button class="btn btn-primary ` + inviteBtnAddClass + `" onclick="friendInviteAct(this,` + '${member.id}' + ',' + multiPL.id + `)" style="position: absolute;bottom: 5px;right: 5px;">` + inviteBtnText + `</button>`;
                    }
                    // console.log(multiPL);
                    var bday = multiPL.birthday;
                    var genderRes = multiPL.gender;
                    if (genderRes == "M") {
                        genderRes = "男";
                    } else if (genderRes == "F") {
                        genderRes = "女";
                    } else {
                        genderRes = "";
                    }
                    var smokingShow = "";
                    if (!multiPL.memberDetail == "" || !multiPL.memberDetail == null || !typeof multiPL.memberDetail == 'undefined') {
                        if (!multiPL.memberDetail.smoking == "" || !multiPL.memberDetail.smoking == null || !typeof multiPL.memberDetail.smoking == 'undefined') {
                            var smokingShow = `<p class="card-text">是否抽菸：` + multiPL.memberDetail.smoking + `</p>`;
                        }
                    }

                    var card = `<div class="col-xl-4 col-lg-6">
                                    <div class="card" style="width: 18rem;margin: 1em;">
                                    <div style="position: relative;">
                                    <img id="multi-match-img"
                                        src="${root}/match/dphoto/` + multiPL.id + `"
                                        style="border-radius: 2px;" class="card-img-top">
                                    </div>
                                    <div class="card-body">
                                    <div style="align-items: center;">
                                        <p class="card-text">性別：`+ genderRes + `</p>
                                        `+ smokingShow + `
                                        <p class="card-text">身高：`+ multiPL.height + ` 公分</p>
                                        <p class="card-text">體重：`+ multiPL.weight + ` 公斤</p>
                                        <p class="card-text">年齡：`+ calculateAge(bday) + `</p>
                                    </div>`+ inviteBtn + `
                                    </div>
                                    </div>
                                    </div>`;

                    output += card;
                }
                myMultiMatchResult.innerHTML = output;
            }

            // 一鍵填入按鈕的點擊事件處理函式
            document.getElementById("fillInButton1").addEventListener("click", function () {
                // 填入身高區間
                document.getElementById("minHeight").value = 150;
                document.getElementById("maxHeight").value = 180;

                // 填入體重區間
                document.getElementById("minWeight").value = 50;
                document.getElementById("maxWeight").value = 70;

                // 填入年齡區間
                document.getElementById("minAge").value = 20;
                document.getElementById("maxAge").value = 30;
            });
            document.getElementById("fillInButton2").addEventListener("click", function () {
                // 填入身高區間
                document.getElementById("minHeight").value = 100;
                document.getElementById("maxHeight").value = 200;

                // 填入體重區間
                document.getElementById("minWeight").value = 40;
                document.getElementById("maxWeight").value = 80;

                // 填入年齡區間
                document.getElementById("minAge").value = 18;
                document.getElementById("maxAge").value = 25;
            });
            document.getElementById("fillInButton3").addEventListener("click", function () {
                // 填入身高區間
                document.getElementById("minHeight").value = 100;
                document.getElementById("maxHeight").value = 180;

                // 填入體重區間
                document.getElementById("minWeight").value = 20;
                document.getElementById("maxWeight").value = 100;

                // 填入年齡區間
                document.getElementById("minAge").value = 25;
                document.getElementById("maxAge").value = 65;
            });

        </script>
    </body>

    </html>