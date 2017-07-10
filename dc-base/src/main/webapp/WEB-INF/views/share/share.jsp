<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>share</title>
    <link rel="stylesheet" href="/static/css/pintuer.css">
    <link rel="stylesheet" href="/static/css/admin.css">
    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/pintuer.js"></script>
    <script src="/static/js/respond.js"></script>
    <script src="/static/js/admin.js"></script>
    <link type="image/x-icon" href="/static/images/box.ico" rel="shortcut icon" />
    <link href="/static/images/box.ico" rel="bookmark icon" />
</head>
<body style="margin:0px;">
<c:if test="${t eq '2'}">
<img style="-webkit-user-select: none" src="/static/images/share_2.jpg" width="300px" height="300px"/>
</c:if>
<c:if test="${t eq '3'}">
<img style="-webkit-user-select: none" src="/static/images/share_3.jpg" width="300px" height="300px"/>
</c:if>

<c:if test="${t eq '1'}">
<img  style="-webkit-user-select: none" src="/static/images/share_1.jpg" width="300px" height="300px"/>
</c:if>
</body>