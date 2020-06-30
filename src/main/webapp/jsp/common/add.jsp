<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/23
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    $(function () {
        $.ajax({
            url: "/dept/list",
            type: "get",
            data: "",
            dataType: "json",
            success: function (data) {
                // console.log(data);
                var html = "";
                html += '<option value="1">请选择</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                }
                $("#select").append(html);
            }
        });

        $("#username").blur(function () {
            $.ajax({
                url: "/user/findName",
                type: "get",
                data: {"username": $("#username").val()},
                dataType: "",
                success: function (data) {
                    console.log(data);
                    if (data == 1) {
                        $("#username").val("");
                        $("#span").attr("hidden",false);
                    }else {
                        $("#span").attr("hidden",true);
                    }
                }
            })
        });
    });


</script>
<body>
<form action="/user/add" method="post" class="form form-horizontal" style="padding-top: 5%">

    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="username" name="username">
            <span id="span" style="color: #ff0000" hidden>用户已存在，请重新输入！！</span>
        </div>
    </div>

    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-5">
            <input type="password" class="form-control" id="password" name="password">
        </div>
    </div>

    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">邮箱</label>
        <div class="col-sm-5">
            <input type="email" class="form-control" id="email" name="email">
        </div>
    </div>

    <div class="form-group">
        <label for="age" class="col-sm-2 control-label">年龄</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="age" name="age">
        </div>
    </div>

    <div style="padding-left: 15%">
        <label>
            <input type="radio" name="sex" id="optionsRadios1" value="1" checked>
            男
        </label>
        <label>
            <input type="radio" name="sex" id="optionsRadios2" value="2">
            女
        </label>
    </div>

    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">简介</label>
        <textarea class="form-control" rows="3" name="description" style="width: 40%"></textarea><br><br>
    </div>

    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">部门</label>
        <select id="select">

        </select><br><br>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <button type="submit" class="btn btn-default">保存</button>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-3">
            <button type="reset" class="btn btn-default">重置</button>
        </div>
    </div>
</form>
</body>
</html>
