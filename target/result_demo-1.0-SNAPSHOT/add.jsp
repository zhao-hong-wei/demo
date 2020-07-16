<%--
  Created by IntelliJ IDEA.
  User: zhw
  Date: 2020/7/12
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/res/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">

    function sendToUser(){ //在这里进行ajax 文件上传 用户的信息
        var $file1 = $("input[name='fileName1']").val();//用户文件内容(文件)
        // 判断文件是否为空
        if ($file1 == "") {
            alert("请选择上传的目标文件! ")
            return false;
        }
        //判断文件大小
        var size1 = $("input[name='fileName1']")[0].files[0].size;
        if (size1>104857600) {
            alert("上传文件不能大于100M!");
            return false;
        }
        boo1 = true;
        var type = "file";
        var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
        formData.append(type,$("#fileName1")[0].files[0]);
        $.ajax({
            type : "post",
            url : "<%=request.getContextPath()%>/user/uploadToFile",
            data : formData,
            processData : false,
            contentType : false,
            success : function(data){
                if (data=="error") {
                    alert("文件提交失败!");
                }else{
                    $("input[name='userUrl']").val(data);
                    alert("文件上传成功!");
                }}
        });
    }



    </script>
</head>
<body>
    <form id="fm">
        <input type="file" name="fileName1" id="fileName1"/>
        <input type="button" onclick="sendToUser()" value="提交" />
    </form>
</body>
</html>
