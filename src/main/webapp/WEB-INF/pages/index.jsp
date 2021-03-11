<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath;
    String staticPath = basePath + "/static";
    request.setAttribute("staticPath", staticPath);
    request.setAttribute("basePath", basePath);
%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <title>Login</title>
    <!-- Meta tag Keywords -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8" />
    <meta name="keywords" content="登录"
    />
    <link rel="stylesheet icon" href="${staticPath}/img/logo.jpg" type="image/x-icon" media="screen" />
    <!-- Meta tag Keywords -->
    <!-- css files -->
    <link rel="stylesheet" href="${staticPath}/css/style.css" />
    <!-- Style-CSS -->
    <link rel="stylesheet" href="${staticPath}/css/fontawesome-all.css">
    <!-- Font-Awesome-Icons-CSS -->
    <!-- //css files -->
    <!-- web-fonts -->
    <!-- //web-fonts -->

</head>

<body>
<!-- bg effect -->
<div id="bg">
    <canvas></canvas>
    <canvas></canvas>
    <canvas></canvas>
</div>
<!-- //bg effect -->
<!-- title -->
<h1><spring:message code="login.title"></spring:message></h1>
<!-- //title -->
<!-- content -->
<div class="sub-main-w3">
    <form  id="form_login">
        <h2><spring:message code="login.loginnow"></spring:message>
            <i class="fas fa-level-down-alt"></i>
        </h2>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-user"></i>
                <spring:message code="login.username"></spring:message>
            </label>
            <input placeholder="Username" name="name" type="text" required="">
        </div>
        <div class="form-style-agile">
            <label>
                <i class="fas fa-unlock-alt"></i>
                <spring:message code="login.password"></spring:message>
            </label>
            <input placeholder="Password" name="psw" type="password" required="">
        </div>
        <!-- checkbox -->
       <%-- <div class="wthree-text">
            <ul>
                <li>
                    <label class="anim">
                        <input type="checkbox" class="checkbox" required="">
                        <span>Stay Signed In</span>
                    </label>
                </li>
                <!-- <li> -->
                <!-- <a href="#">Forgot Password?</a> -->
                <!-- </li> -->
            </ul>
        </div>--%>
        <!-- //checkbox -->
        <input type="submit" value="<spring:message code="login.login"></spring:message>">
        <!-- <input type="botton" value="Log In"> -->
    </form>
</div>
<!-- //content -->

<!-- copyright -->
<div class="footer">
    <p>©2021 Sinic-Tek intelligent Technology Co.,Ltd 版权所有</p>
</div>
<!-- //copyright -->

<!-- Jquery -->
<script src="${staticPath}/jquery/jQuery-2.2.0.min.js"></script>
<!-- //Jquery -->
<!-- effect js -->
<script src="${staticPath}/js/canva_moving_effect.js"></script>
<script >
    $(document).ready(function(){
        $('#form_login').submit(function(e) {
            e.preventDefault();   //越过submit 执行以下代码.
            login();

        });
    });
    function login(){
        // alert($('#login_submit').val());
        $.ajax({
            url:"${basePath}/spc/login",
            data:$('#form_login').serialize(),
            dataType:"json",
            type:"POST",
            beforeSend:function(){
            },
            success:function(data) {
                //alert(data.data);
                //登录成功并保存token
                if(!window.localStorage){
                    alert("浏览器支持localstorage");
                    return false;
                }else{
                    var storage=window.localStorage;
                    storage.setItem("token",data.data.token);
                    storage.setItem("username",data.data.username);
                    console.log(storage.token);
                    //跳转主页
                    if(data.message=='OK'){
                        /*$.ajax({
                            url:"${basePath}/Home/pcbHome",
                            headers:{'token':storage.token},
                            type:"GET",
                            success:function(data) {*/
                                //alert(data);
                                window.location.href='${basePath}/Home/pcbHome';
                           /* }
                            ,error:function (data) {
                                //alert(data);
                            }

                        });*/
                    }else{
                        //登录失败
                        alert('登录失败!'+data.data.data);
                    }
                }

            },
            error:function (data) {
                alert(data);
            }
        });

    }
    function enterLoginSearch(){
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13)
        {

        }
    }
</script>
<script>
    /*addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    }*/
</script>

</body>

</html>