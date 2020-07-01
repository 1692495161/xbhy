<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/28
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<form action="/forget/updateForget" method="post">
    用户名：<input type="text" name="username"><br><br>
    新密码：<input type="password" name="password"><br><br>
    邮箱：<input type="text" name="email" id="email" value="">
    <span id="span" hidden>发送成功</span><br><br>
    <input type="button" value="发送" class="btn btn-info" id="btn"><br><br>
    验证码：<input type="text" name="code" id="code"><br><br>
    <input type="submit" value="修改">
</form>
</body>
<script>
    $(function () {
        $("#btn").click(function () {
            $.ajax({
                url: "/email/email",
                type: "post",
                data: {"email": $("#email").val()},
                dataType: "text",
                success: function (data) {
                    if (data=="0"){
                        $("#span").attr("hidden",true);
                    }else {
                        $("#span").attr("hidden",false);
                    }
                }
            });
        });
    });
</script>
</html>
