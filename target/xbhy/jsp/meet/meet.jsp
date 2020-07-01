<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/30
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会议管理</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">
    <form action="/meet/meetList" method="post">
        会议标题：<input type="text" name="username" value="${username}">
        会议状态：<select name="status" id="status">
        <option value="-1" <c:if test="${status=='-1'}">selected</c:if>>会议状态</option>
        <option value="0" <c:if test="${status=='0'}">selected</c:if>>未开始</option>
        <option value="1" <c:if test="${status=='1'}">selected</c:if>>进行中</option>
        <option value="2" <c:if test="${status=='2'}">selected</c:if>>已结束</option>
    </select>
        <input type="submit" value="查询" class="btn btn-info">
        <a href="/jsp/meet/add.jsp" class="btn btn-warning col-sm-3" style="float: right">添加新会议</a>
    </form>
    <table class="table table-bordered">
        <tr>
            <td>序号</td>
            <td>部门名称</td>
            <td>会议标题</td>
            <td>会议内容</td>
            <td>发布时间</td>
            <td>会议状态</td>
            <td>开始时间</td>
            <td>结束时间</td>
            <td>操作</td>
        </tr>
        <c:forEach var="list" items="${list}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${list.deptName}</td>
                <td>${list.title}</td>
                <td>${list.content}</td>
                <td>
                    <fmt:formatDate value="${list.publishDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${list.status==0}">未开始</c:when>
                        <c:when test="${list.status==1}">进行中</c:when>
                        <c:when test="${list.status==2}">已结束</c:when>
                    </c:choose>
                </td>
                <td>
                    <fmt:parseDate var="strData" value="${list.startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                    <fmt:formatDate value="${strData}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td>
                    <fmt:parseDate var="endDate" value="${list.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                    <fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td>
                    <a href="/meet/getMeetById?id=${list.id}" class="btn btn-warning" >查看</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
