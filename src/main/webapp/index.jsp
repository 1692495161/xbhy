<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<script type="text/javascript" src="${path}/static/js/jquery-3.4.1.js"></script>
<script src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

<link rel="stylesheet" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap.css">
<html>
<head>
    <title>登录界面</title>
</head>
<style>
    #top {
        width: 100%;
        height: 15%;
        position: absolute;
        top:20px;
    }
    #main{
        height: 60%;
        width: 60%;
        border: 1px solid #8c8c8c;
        position: relative;
        top: 20%;
        left: 30%;

    }
    #login{
        border: 1px solid;
        height: auto;
        width: 90%;
        position: absolute;
    }
</style>
<body>
<div id="top">
    <div class="page-header">
        <h1>小标会议
            <small>XiaoBiao Meetting</small>
        </h1>
    </div>
</div>
<div id="main">
<form action="/login/login" method="post" class="form-horizontal"id="login">
    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">用户名:</label>
        <div class="col-sm-3">
            <input type="text" name="username" id="username" class="form-control" placeholder="username">
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">密码：</label>
        <div class="col-sm-2">
            <input type="password" name="password" id="password" class="form-control" placeholder="password">
        </div>
    </div>
    <div class="form-group">
        <label for="code" class="col-sm-2 control-label">验证码：</label>
        <div class="col-sm-2" >
            <input type="text" name="code" id="code" class="form-control">
            <img src="/img/getCode" id="btn" onclick="changeImg()" style="float: right;margin-right: -30%">
        </div>
    </div>
    <input type="submit" value="登录">
    <input type="checkbox" name="remember" id="remember" value="1">记住密码 <br>
    <a href="/jsp/forget/forget.jsp">忘记密码</a>
    <a href="/otherLogin/weChat"><img src="/doc/image/1.png"></a>
</form>
</div>
</body>
<script>
    // $("#btn").click(function () {
    //     $("#btn").attr("src","/img/getCode?nocache="+new Date().getTime());
    // });
    function changeImg() {
        // $("#btn").attr("src","/img/getCode?nocache="+new Date().getTime());
        document.querySelector("#btn").setAttribute("src", "/img/getCode?nocache=" + new Date().getTime())
    }

</script>
</html>
