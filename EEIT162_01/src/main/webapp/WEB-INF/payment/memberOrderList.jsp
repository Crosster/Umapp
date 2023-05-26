<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
<jsp:include page="/WEB-INF/component/common_dependencies_2.jsp" />
<meta charset="UTF-8">
<title>約密</title>
<script>
    $(document).ready(function(){
        $.ajax({
					url: '${root}/member/level/findall/${member.id}',
					type: 'GET',
					success: function (response) {
                        
                        response.forEach((item,index,response)=>{
                            addOrderList(item);
                        });
					},
					error: function () {
					}
        });

    });

    function addOrderList(item){
        let orderTbody = document.getElementById('orderBody');

        let orderTr = document.createElement('tr');
        let orderTh = document.createElement('th');
        let orderTd1 = document.createElement('td');
        let orderTd2 = document.createElement('td');
        let orderTd3 = document.createElement('td');
        let orderTd4 = document.createElement('td');

        orderTh.scope = 'row';
        orderTh.innerHTML = item.id;

        orderTd1.innerHTML = dateFormat(item.orderDate);
        orderTd2.innerHTML = item.orderName;
        orderTd3.innerHTML = item.orderPrice;
        orderTd4.innerHTML = item.orderNumber;
    
        orderTr.appendChild(orderTh);
        orderTr.appendChild(orderTd1);
        orderTr.appendChild(orderTd2);
        orderTr.appendChild(orderTd3);
        orderTr.appendChild(orderTd4);
        orderTbody.appendChild(orderTr);
    }

    function dateFormat(date){
        const theDate = new Date(date);
        const theYear = theDate.getFullYear();
        const theMonth = theDate.getMonth()+1;
        const theDay = theDate.getDate();
        const theTime = theDate.getHours()+':'+theDate.getMinutes()+':'+theDate.getSeconds();

        let formatedDate = theYear+"-"+theMonth+"-"+theDay+" "+theTime;

        return formatedDate;
    }

</script>
</head>
<body>
<jsp:include page="/WEB-INF/layout/newnavbar.jsp" />
<!-- ======= Pricing Section ======= -->
<section id="pricing" class="pricing" style="padding-bottom: 200px;padding-top: 0px;">
        <div class="section-header">
            <p>購買紀錄</p>
        </div>
        <table class="table text-white">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">購買日期</th>
                <th scope="col">購買產品</th>
                <th scope="col">購買價格</th>
                <th scope="col">訂單編號</th>
              </tr>
            </thead>
            <tbody id="orderBody">
            </tbody>
          </table>
    </section><!-- End Pricing Section -->

</body>
</html>