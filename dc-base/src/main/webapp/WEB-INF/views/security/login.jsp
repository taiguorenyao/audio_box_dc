<%--
  Created by IntelliJ IDEA.
  User: whati
  Date: 2017/1/13
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script src="http://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script>
        function doSubmit() {
            jQuery("#loginForm").submit();
        }
    </script>
</head>
<body>
<%
    String uri = (String)request.getAttribute("uri");
%>
<form action="/j_spring_security_check" id="loginForm" method="post">
    用户名：<input name="j_username" type="text"/><br/>
    密码：<input name="j_password" type="text"/><br/>
    <input name="returnUrl" type="hidden" value="<%=uri%>"/>
    <input type="button" value="提交" onclick="doSubmit()"/>&nbsp;<a href="/toReg">去注册</a>
</form>
</body>
</html>
