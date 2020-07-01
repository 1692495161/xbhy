<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/7/1
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看会议</title>
</head>
<body>
<form action="" method="post" class="form form-horizontal" style="padding-top: 5%">
    <div class="form-group">
        <label for="deptName" class="col-sm-2 control-label">部门名称</label>
        <div class="col-sm-5">
            <input type="text" readonly class="form-control" id="deptName" name="deptName" value="${meet.deptName}">
        </div>
    </div>

    <div class="form-group">
        <label for="title" class="col-sm-2 control-label">会议标题</label>
        <div class="col-sm-5">
            <input type="text" readonly class="form-control" id="title" name="title" value="${meet.title}">
        </div>
    </div>

    <div class="form-group">
        <label for="startTime" class="col-sm-2 control-label">开始时间</label>
        <div class="col-sm-5">
            <fmt:parseDate var="strData" value="${meet.startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
            <input type="text" readonly class="form-control" value="<fmt:formatDate value="${strData}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>" id="startTime" name="startTime" onblur="meetName()">
            <%--            <span hidden id="span">开始时间过早，请重新选择！</span>--%>
        </div>
    </div>

    <div class="form-group">
        <label for="endTime" class="col-sm-2 control-label">结束时间</label>
        <div class="col-sm-5">
            <fmt:parseDate var="endDate" value="${meet.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
            <input type="text" readonly value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>" class="form-control" id="endTime" name="endTime" onblur="meetName()">
            <%--            <span hidden id="span">开始时间过早，请重新选择！</span>--%>
        </div>
    </div>

    <div class="form-group">
        <label for="content" class="col-sm-2 control-label">会议内容</label>
        <textarea class="form-control" readonly rows="3" name="content" style="width: 40%" id="content">${meet.content}</textarea><br><br>
    </div>

    <div class="form-group" style="float: left;margin-left: 30%">
        <div class="col-sm-offset-2 col-sm-5">
            <button type="submit" class="btn btn-info">保存</button>
        </div>
    </div>
</form>
</body>
</html>
