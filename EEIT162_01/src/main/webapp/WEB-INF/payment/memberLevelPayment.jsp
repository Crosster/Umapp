<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
        <jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
        <meta charset="UTF-8">
        <title>約密</title>
        <script>
            function goPay() {
                // console.log('pay');
            }
        </script>
    </head>

    <body>
        <jsp:include page="/WEB-INF/layout/newnavbar.jsp" />

        <section id="hero" class="hero d-flex flex-column justify-content-center align-items-center" data-aos="fade"
            data-aos-delay="1500">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-6 text-center">
                        <div class="row" style="color: black;">
                            <div class="card col" style="margin: 20px;">
                                <div class="card-header" style="background-color:aliceblue">
                                    <h4 class="my-0 font-weight-normal">一般會員</h4>
                                </div>
                                <div class="card-body">
                                    <h1 class="card-title pricing-card-title">$0 <small class="text-muted">/
                                            月</small>
                                    </h1>
                                    <ul class="list-unstyled mt-3 mb-4">
                                        <li>免費配對</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card col"
                                style="margin: 20px;background-color: rgb(252, 221, 108);">
                                <div class="card-header">
                                    <h4 class="my-0 font-weight-normal">黃金會員</h4>
                                </div>
                                <div class="card-body">
                                    <h1 class="card-title pricing-card-title">$150 <small class="text-muted">/
                                            月</small>
                                    </h1>
                                    <ul class="list-unstyled mt-3 mb-4">
                                        <li>+無廣告體驗</li>
                                        <li>+更好的配對體驗</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <h2>黃金會員購買</h2>
                        <div style="background-color: rgba(2, 6, 30, 0.205);width: 300px;">
                            <div class="form-check" style="position: relative;left: 50%;">
                                <input class="form-check-input" type="radio" name="flexRadioDefault" id="150" checked>
                                <label class="form-check-label" for="flexRadioDefault1" style="color: brown;">
                                    <h2>150/1月</h2>
                                </label>
                            </div>
                            <div class="form-check" style="position: relative;left: 50%;">
                                <input class="form-check-input" type="radio" name="flexRadioDefault" id="240">
                                <label class="form-check-label" for="flexRadioDefault2">
                                    <h2>360/3月(20%off)</h2>
                                </label>
                            </div>
                            <div class="form-check" style="position: relative;left: 50%;">
                                <input class="form-check-input" type="radio" name="flexRadioDefault" id="600">
                                <label class="form-check-label" for="flexRadioDefault2">
                                    <h2>600/6月(33%off)</h2>
                                </label>
                            </div>
                        </div>
                        <br>
                        <a href="javascript: void(0)" class="btn-get-started" id="payBtn">前往付款</a>
                    </div>
                </div>
            </div>
        </section><!-- End Hero Section -->

        <script>
            let payBtn = document.getElementById('payBtn');
            payBtn.addEventListener('click', function () {
                var value = $("input[type=radio][name=flexRadioDefault]:checked");
                $(location).prop("href", "${root}/member/level/pay/${member.id}/" + value.attr('id'));
            });
        </script>

    </body>

    </html>