<%--
  Created by IntelliJ IDEA.
  User: zhw
  Date: 2020/7/11
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/res/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">

        function x() {
            $.ajax({
                type:'POST',
                url:'<%=request.getContextPath()%>/user/',
                contentType: "application/json; charset=utf-8",
                data:JSON.stringify({"userName":"ZHW","password":125}),
                dataType:"json",
                success:function (message) {
                    alert("提交成功"+JSON.stringify(message));
                },
                error:function (message) {
                    alert("提交失败"+JSON.stringify(message));
                }
            });
        }




        function test() {
            $.get("/user/test",
                $("#fm").serialize(),
                function (data) {
                    if(data.code=="200"){
                        console.log(data.data);
                    }
                })
        }

    </script>
</head>
<body>
<button type="button" onclick="x()">提交</button>
<button type="button" onclick="test()">test</button>

</body>
</html>
