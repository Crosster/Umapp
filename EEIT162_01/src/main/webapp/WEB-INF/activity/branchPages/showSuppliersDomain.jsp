<!-- 05/03 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 用於設定 SpringFramework 表單，並將 表單 包成一個物件 傳送給 Controller -->
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- 5/04 -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Testing Only </title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    
    <style>
        #location{
        display: ;
        padding: 120px;
        width: 760px;
        height: 960px;
        margin-right: 480px;
        float: right;
        border-radius: 50%;
        color:black;
        background-color:;
        font-size: xx-large;
        text-align: left;
        }
    </style>
</head> 

<body>
<div id="location">
    <div  class="w3-container">
        <div class="contaoner">
            <div class="row justify-content-center">
                <div class="col-10 col-md-6">
            
                    <form enctype="multipart/form-data" class="position" action="/action_page.php" method="post">
                    
                        <label for="input">供應商名稱：</label> <br/> 
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>
                        <label for="input">供應商所屬產業別：</label> <br/> 
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>
                        <label for="input">供應商營業型態：</label> <br/>  
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>
                        <label for="input">供應商資本額：</label> <br/> 
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>
                        <label for="input">供應商業地址：</label> <br/> 
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>
                        <label for="input">供應商營業地址：</label> <br/> 
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>
                        <label for="input">供應商電話號碼：</label> <br/> 
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>
                        <label for="input">供應商合作起始日期：</label> <br/> 
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>						
                        <label for="input">供應商合作結束日期：</label> <br/> 
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>	
                        <label for="input">合約類型：</label> <br/> 
                        <input type="text" id="input" name="input"> <br/> 
                            <br/>	
                        <span>
                            <label for="photo" value="限 .JPG.PEG 格式">供應商 Logo：</label> <br/> 
                            <input type="file" id="photo" name="photo" accept="image/png, image/jpeg"> <br/> 
                        </span>
                        <br/>					
                        <botun id="gap1" type="submit" onclick="alert('資料已傳送完成~!!!')" class="w3-btn w3-blue">填妥送出</botun>
                        <dic></dic>
                        <!-- <input type="text" name="input" /> -->
                        <input type="reset" value="重新填寫" onclick="alert('按下『確定』後，請重新填寫~!!!')" class="w3-btn w3-blue"/>
                        <br/> 
                        <!-- <botun id="gap2" type="submit" onclick="alert('請重新填寫~!!!')" class="w3-btn w3-blue">重新填寫</botun> -->
                        
                        <!-- 
                            <input type="button" value="click here to clear" onclick="document.getElementById('inputText').value = '' "/>
                            <input type="text" value="Tutorix" id="inputText" /> 
                        -->
                    </form>

    <!-- 05/05 輸入頁面 -->
        
    
                </div>
            </div>
        </div>
    </div>
</div>



    <script>


    </script>
</body>
</html>