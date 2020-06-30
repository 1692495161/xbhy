<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/22
  Time: 0:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../top/top.jsp" %>
<%@include file="../left/left.jsp" %>
<div id="right">
    <form action="/user/list">
        用户名：<input type="text" name="username" id="username" value="${username}">
        性别：<select name="select" id="select">
                <option value="-1" <c:if test="${sex=='-1'}">selected</c:if>>请选择</option>
                <option value="1" <c:if test="${sex=='1'}">selected</c:if>>男</option>
                <option value="0" <c:if test="${sex=='0'}">selected</c:if>>女</option>
             </select>
        <input type="submit" value="查询" class="btn btn-info">
    </form>

    <a href="/jsp/common/add.jsp" class="btn btn-info btn-default btn-sm" style="float: right">添加</a>

    <table class="table table-hover">
        <tr>
            <td>序号</td>
            <td>用户名</td>
            <td>邮箱</td>
            <td>年龄</td>
            <td>性别</td>
            <td>简介</td>
            <td>部门</td>
            <td>注册时间</td>
            <td>操作</td>
        </tr>
        <c:forEach var="user" items="${page.data}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.age}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.sex==1}">男</c:when>
                        <c:when test="${user.sex==0}">女</c:when>
                        <c:otherwise>其他</c:otherwise>
                    </c:choose>
                </td>
                <td>${user.description}</td>
                <td>${user.deptName}</td>
                <td>
                    <fmt:formatDate value="${user.registerTime}" pattern="yyyy年MM月dd日 HH时mm分ss秒"></fmt:formatDate>
                </td>
                <td>
                    <a href="/user/getUserById?id=${user.id}" class="btn btn-info">修改</a>
                    <a href="/user/delete?id=${user.id}" class="btn btn-info">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    总记录数：${page.count}
    当前页数：${page.pageCurrent}
    总页数：${page.pageCount}
    <a href="/user/list?page=${page.pageCurrent-1>0?page.pageCurrent-1:1}&username=${username}&select=${sex}">上一页</a>
    <a href="/user/list?page=${page.pageCurrent+1>=page.pageCount?page.pageCount:page.pageCurrent+1}&username=${username}&select=${sex}">下一页</a>
</div>
</body>
</html>
