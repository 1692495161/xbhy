<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/6/22
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    window.onload = function (ev) {
        // $.ajax({
        //     url: "/muen",
        //     type: "get",
        //     data: "",
        //     dataType: "json",
        //     success: function (data) {
        //         for (var i = 0; i < data.length; i++) {
        //             console.log(data[i])
        //         }
        //         // alert(data)
        //         var html = "";
        //         for (var i = 0; i < data.length; i++) {
        //             var menu_1 = data[i];
        //             // console.log("pid:"+menu_1.pId);
        //             if (menu_1.type == 1) {
        //                 html += menu_1.name;
        //                 html += "<ul>";
        //                 for (var j = 0; j < data.length; j++) {
        //                     var menu_2 = data[j];
        //                     if (menu_2.pId == menu_1.id) {
        //                         html += '<li><a href="' + menu_2.url + '">' + menu_2.name + '</a></li>';
        //                     }
        //                 }
        //                 html += "</ul>"
        //             }
        //         }
        //         $("#left").append(html);
        //     }
        // });

        //优化
        $.ajax({
            url: "/muen",
            type: "get",
            data: "",
            dataType: "json",
            success: function (data) {
                //获取一级菜单list
                var parent = data.parent;
                //获取二级菜单list
                var son=data.son;
                var html = '';
                for (var i = 0; i < parent.length; i++) {
                    html += parent[i].name;
                    html += '<ul>';
                    for (var j = 0; j < son.length; j++) {
                        if (son[j].pId == parent[i].id) {
                            html += '<li><a href="' + son[j].url + '">' + son[j].name + '</a></li>';
                    }
                    }
                    html += '</ul>';
                }
                $("#left").append(html);
            }
        });
    }
</script>
<style>
    #left {
        margin-top: 1%;
        height: 85%;
        width: 15%;
        border: 1px solid #ff0000;
        float: left;
    }

    #right {
        margin-top: 1%;
        margin-left: 1%;
        height: 85%;
        width: 83%;
        float: left;
        border: 1px solid #ff0000;
    }

    li {
        list-style: none;
    }
</style>
<body>
<div id="left">

</div>
</body>
</html>
