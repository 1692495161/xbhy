<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/7/1
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布议会</title>
</head>
<%--<script>
    $(function () {
        $("#startTime").blur(function () {
            var date = (new Date()).getTime();
            if ($("#startTime").val().getTime() < date) {
                $("#span").attr("hidden", true);
                $("#startTime").val("")
            } else {
                $("#span").attr("hidden", false);
            }
        });
    });
</script>--%>
<body>
<form action="/meet/meetAdd" method="post" class="form form-horizontal" style="padding-top: 5%">

    <div class="form-group">
        <label for="deptName" class="col-sm-2 control-label">部门名称</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="deptName" name="deptName">
        </div>
    </div>

    <div class="form-group">
        <label for="title" class="col-sm-2 control-label">会议标题</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="title" name="title">
        </div>
    </div>

    <div class="form-group">
        <label for="startTime" class="col-sm-2 control-label">开始时间</label>
        <div class="col-sm-5">
            <input type="datetime-local" class="form-control" id="startTime" name="startTime" onblur="meetName()">
<%--            <span hidden id="span">开始时间过早，请重新选择！</span>--%>
        </div>
    </div>

    <div class="form-group">
        <label for="endTime" class="col-sm-2 control-label">结束时间</label>
        <div class="col-sm-5">
            <input type="datetime-local" class="form-control" id="endTime" name="endTime" onblur="meetName()">
<%--            <span hidden id="span">开始时间过早，请重新选择！</span>--%>
        </div>
    </div>

    <div class="form-group">
        <label for="content" class="col-sm-2 control-label">会议内容</label>
        <textarea class="form-control" rows="3" name="content" style="width: 40%" id="content"></textarea><br><br>
    </div>

    <div class="form-group" style="float: left;margin-left: 30%">
        <div class="col-sm-offset-2 col-sm-5">
            <button type="submit" class="btn btn-info">保存</button>
        </div>
    </div>
    <div class="form-group" style="float: left;margin-left: 5%">
        <div class="col-sm-offset-2 col-sm-3">
            <button type="reset" class="btn btn-warning">重置</button>
        </div>
    </div>
</form>
</body>
</html>
