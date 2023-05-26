<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">

            <jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
            <jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
            <title>${webName }</title>
        </head>

        <body>
            <jsp:include page="/WEB-INF/layout/newnavbar.jsp" />

            <div class="container h-100" style="margin-top: 100px;">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-xl-9">
                        <h1 class="text-white mb-4">填寫理想對象的條件</h1>
                        <form:form id="mdForm" modelAttribute="memberAtt" method="post" action="">


                            <div>
                                <form:input type="hidden" path="id" value="${member.id}" />
                                <!-- 性向 sexualOrientation -->
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h6 class="mb-0">我的性向</h6>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <form:select path="memberDetail.sexualOrientation"
                                            class="form-control form-control-lg">
                                            <form:option value="" label="請選擇" />
                                            <form:option value="喜歡男性" label="喜歡男性" />
                                            <form:option value="喜歡女性" label="喜歡女性" />
                                        </form:select>
                                    </div>
                                </div>

                                <!-- 體型偏好 bodyType -->
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h6 class="mb-0">體型偏好</h6>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <form:select path="memberDetail.bodyType" class="form-control form-control-lg">
                                            <form:option value="" label="請選擇" />
                                            <form:option value="偏瘦" label="偏瘦" />
                                            <form:option value="普通" label="普通" />
                                            <form:option value="微肉感" label="微肉感" />
                                        </form:select>
                                    </div>
                                </div>

                                <!-- 是否抽菸 smoking -->
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h6 class="mb-0">是否抽菸</h6>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <form:select path="memberDetail.smoking" class="form-control form-control-lg">
                                            <form:option value="" label="請選擇" />
                                            <form:option value="不抽" label="不抽" />
                                            <form:option value="抽" label="抽" />
                                        </form:select>
                                    </div>
                                </div>

                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h6 class="mb-0">是否接受對方抽菸</h6>
                                    </div>
                                    <div class="col-md-9 pe-5">
                                        <form:select path="memberDetail.smokingAccept"
                                            class="form-control form-control-lg">
                                            <form:option value="" label="請選擇" />
                                            <form:option value="可接受" label="可接受" />
                                            <form:option value="不可接受" label="不可接受" />
                                        </form:select>
                                    </div>
                                </div>




                                <!-- 理想對象年齡範圍 min_age_preference 和 max_age_preference -->
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h6 class="mb-0">理想對象的最小年齡</h6>
                                    </div>
                                    <div class="col-md-4 pe-5">
                                        <form:input path="memberDetail.minAgePreference" type="number"
                                            class="form-control form-control-lg" placeholder="最小年齡" />
                                    </div>
                                </div>
                                <div class="row align-items-center pt-4 pb-3">
                                    <div class="col-md-3 ps-5">
                                        <h6 class="mb-0">理想對象的最大年齡</h6>
                                    </div>
                                    <div class="col-md-4 pe-5">
                                        <form:input path="memberDetail.maxAgePreference" type="number"
                                            class="form-control form-control-lg" placeholder="最大年齡" />
                                    </div>
                                </div>




                                <hr class="mx-n3">
                                <!-- 送出註冊 -->
                                <div class="px-5 py-4">
                                    <button type="submit" class="btn btn-primary btn-lg">送出</button>
                                </div>
                                <p> ${message} </p>
                            </div>
                        </form:form>
                        <button id="fill-default1" class="btn btn-secondary">一鍵填入</button>
                        <button id="fill-default2" class="btn btn-warning">一鍵填入</button>
                        <button id="fill-default3" class="btn btn-info">一鍵填入</button>
                    </div>
                </div>
            </div>


            <jsp:include page="/WEB-INF/layout/footer.jsp" />



            <script>


                $(document).ready(function () {
                    var memberOld = "${member}";

                    console.log("memberOld");
                    console.log(memberOld);
                    var memberDetailOld = "${member.memberDetail}";
                    console.log("memberDetailOld");
                    console.log(memberDetailOld);
                    if (memberDetailOld != null && memberDetailOld != "") {
                        console.log("memberDT");
                        console.log("${memberDT}");
                        var varSexualOrientation = "${member.memberDetail.sexualOrientation}"
                        var bodyType = "${member.memberDetail.bodyType}";
                        var smoking = "${member.memberDetail.smoking}";
                        var smokingAccept = "${member.memberDetail.smokingAccept}";
                        var minAgePreference = "${member.memberDetail.minAgePreference}";
                        var maxAgePreference = "${member.memberDetail.maxAgePreference}";
                        if ("${memberDT}" != null && "${memberDT}" != "") {
                            console.log("HI");
                            if ("{ memberDT.sexualOrientation }" != null && "${ memberDT.sexualOrientation } " != "") {
                                varSexualOrientation = "${memberDT.sexualOrientation}";
                                if (varSexualOrientation == "M" || varSexualOrientation == "男" || varSexualOrientation == "喜歡男性") {
                                    var sexualOrientation = "喜歡男性";
                                }
                                if (varSexualOrientation == "F" || varSexualOrientation == "女" || varSexualOrientation == "喜歡女性") {
                                    var sexualOrientation = "喜歡女性";
                                }
                            }
                            if ("{ memberDT.bodyType }" != null && "${ memberDT.bodyType } " != "") {
                                bodyType = "${memberDT.bodyType}";
                            }
                            if ("{ memberDT.smoking }" != null && "${ memberDT.smoking } " != "") {
                                smoking = "${memberDT.smoking}";
                            }
                            if ("{ memberDT.smokingAccept }" != null && "${ memberDT.smokingAccept } " != "") {
                                smokingAccept = "${memberDT.smokingAccept}";

                            }
                            if ("{ memberDT.minAgePreference }" != null && "${ memberDT.minAgePreference } " != "") {
                                minAgePreference = "${memberDT.minAgePreference}";
                            }
                            if ("{ memberDT.maxAgePreference }" != null && "${ memberDT.maxAgePreference } " != "") {
                                maxAgePreference = "${memberDT.maxAgePreference}";
                            }

                        }
                        $('select[name="memberDetail.sexualOrientation"]').val(sexualOrientation);
                        $('select[name="memberDetail.bodyType"]').val(bodyType);
                        $('select[name="memberDetail.smoking"]').val(smoking);
                        $('select[name="memberDetail.smokingAccept"]').val(smokingAccept);
                        $('input[name="memberDetail.minAgePreference"]').val(minAgePreference);
                        $('input[name="memberDetail.maxAgePreference"]').val(maxAgePreference);
                        var changeForm = document.getElementById("mdForm");
                        changeForm.method = "get";
                        changeForm.action = "${root}/match/MDupdate";
                    }



                    //一鍵填入
                    $("#fill-default1").on("click", function () {
                        // 在這裡填入你的預設資料
                        $('select[name="memberDetail.sexualOrientation"]').val('喜歡男性');
                        $('select[name="memberDetail.bodyType"]').val('普通');
                        $('select[name="memberDetail.smoking"]').val('不抽');
                        $('select[name="memberDetail.smokingAccept"]').val('可接受');
                        $('input[name="memberDetail.minAgePreference"]').val(30);
                        $('input[name="memberDetail.maxAgePreference"]').val(35);
                    });
                    $("#fill-default2").on("click", function () {
                        // 在這裡填入你的預設資料
                        $('select[name="memberDetail.sexualOrientation"]').val('喜歡女性');
                        $('select[name="memberDetail.bodyType"]').val('普通');
                        $('select[name="memberDetail.smoking"]').val('不抽');
                        $('select[name="memberDetail.smokingAccept"]').val('可接受');
                        $('input[name="memberDetail.minAgePreference"]').val(24);
                        $('input[name="memberDetail.maxAgePreference"]').val(30);
                    });
                    $("#fill-default3").on("click", function () {
                        // 在這裡填入你的預設資料
                        $('select[name="memberDetail.sexualOrientation"]').val('喜歡女性');
                        $('select[name="memberDetail.bodyType"]').val('微肉感');
                        $('select[name="memberDetail.smoking"]').val('不抽');
                        $('select[name="memberDetail.smokingAccept"]').val('可接受');
                        $('input[name="memberDetail.minAgePreference"]').val(25);
                        $('input[name="memberDetail.maxAgePreference"]').val(35);
                    });






                    $("#memberDetail-form").on("submit", function (event) {
                        event.preventDefault();

                        // 獲取表單數據
                        var formData = $(this).serializeArray();
                        // console.log("${member.id}")
                        // 將 memberId 添加到表單數據中
                        // formData.push({ name: "FK_mID", value: "${member.id}" });

                        // 發送非同步請求
                        $.ajax({
                            url: "${root}/memberdetail/create",
                            type: "POST",
                            data: formData,

                            success: function (response) {
                                console.log("註冊成功:", response);
                                // 這裡您可以處理成功的回應，例如顯示一個提示消息
                                alert("註冊成功！");
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                console.error("註冊失敗:", error);
                                // 這裡您可以處理錯誤回應，例如顯示一個錯誤消息
                                alert("註冊失敗，請重試！");
                            },

                        });
                    });
                });
            </script>







        </body>

        </html>