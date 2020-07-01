<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/22
  Time: 0:30
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
            url: "/img/getHead2",
            processData: false,      //默认为true,对请求传递的参数(formData)不做编码处理
            contentType: false,       //不对请求头做处理
            type: "post",
            data: "",
            dataType: "",
            success: function (data) {
                if (data.indexOf("http")!=-1){
                    $("#headImg").attr("src",data);
                }else {
                    $("#headImg").attr("src","/img/getHead2");
                }
            }
        });
    });
</script>
<style>
    #top {
        height: 15%;
        width: 100%;
        border: 1px solid #ff0000;
    }

    #headImg {
        height: 70px;
        width: 70px;
    }
</style>
<body>
<div id="top">
    <img src="/img/getHead2" id="headImg" class="img-circle">
</div>
</body>
</html>
