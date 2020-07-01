<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/24
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改界面</title>
</head>
<script>
    function changeHead() {
        //点击头像触发文件上传事件，触发picFile的触发效果
        $("#picFile").click();
    }
    $(function () {
        //文件上传
        $("#picFile").change(function () {
            // 构造文件上传form
            var formData = new FormData();
            formData.append("iconFile", document.getElementById("picFile").files[0]);

            $.ajax({
                url:"/img/upload",
                processData: false,      //默认为true,对请求传递的参数(formData)不做编码处理
                contentType: false,       //不对请求头做处理
                type:"post",
                data:formData,
                dataType:"",
                success:function (data) {
                   if(data==1){
                       $("#img-head").attr("src","/img/getHead?id="+$("#id").val()+"&time="+new Date().getTime())
                       // $("#img-head").attr("src","/img/getHead?id=1&time="+new Date().getTime());

                   }
                }
            });
        });
    });
</script>
<body>
<form action="/user/update" method="post" class="form form-horizontal" style="padding-top: 5%">
    <%--    隐藏域--%>
    <input type="hidden" value="${user.id}" name="id" id="id">

    <img src="/img/getHead?id=${user.id}" onclick="changeHead()" class="img-circle" style="height: 120px;width: 120px" id="img-head">

    <!-- 真正的头像图片上传表单，将其隐藏，通过点击头像触发 -->
    <input type="file"  id="picFile" style="display: none"><br><br>

    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="username" name="username" value="${user.username}">
        </div>
    </div>

    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">邮箱</label>
        <div class="col-sm-5">
            <input type="email" class="form-control" id="email" name="email" value="${user.email}">
        </div>
    </div>

    <div class="form-group">
        <label for="age" class="col-sm-2 control-label">年龄</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" id="age" name="age" value="${user.age}">
        </div>
    </div>

    <div style="padding-left: 15%">
        <label>
            <input type="radio" name="sex" id="optionsRadios1" value="1" <c:if test="${user.sex==1}">checked</c:if>>
            男
        </label>
        <label>
            <input type="radio" name="sex" id="optionsRadios2" value="0" <c:if test="${user.sex==0}">checked</c:if>>
            女
        </label>
    </div>

    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">简介</label>
        <textarea class="form-control" rows="3" name="description" style="width: 40%">${user.description}</textarea><br><br>
    </div>

    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">部门</label>
        <select id="select">
            <c:forEach var="dept" items="${dept}">
                <option value="${dept.id}"
                        <c:if test="${dept.id==user.deptId}">selected</c:if>>${dept.name}</option>
            </c:forEach>
        </select><br><br>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <button type="submit" class="btn btn-default">修改</button>
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
