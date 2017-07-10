<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>盒子后台</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/static/resources/fonts/iconfont.css">
    <link rel="stylesheet" type="text/css"  href="/static/resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/static/resources/css/swiper.min.css">
    <link rel="stylesheet" type="text/css" href="/static/resources/css/ddwang-1.0.1.css">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!--  brand-header -->
<header id="user-header" class="brand-header">
    <div class="header-bg"><img src="/static/resources/images/bg-user.jpg" alt=""></div>
    <a class="header-base" href="javascript:;">
        <i class="iconfont icon-right"></i>
        <div class="col-xs-3">
            <img src="/static/images/money.png" alt="" >
        </div>
        <div class="col-xs-9">

        </div>
    </a>
</header>
<!-- tabNavBar -->
<ul class="list-group m-t-sm list-user">
    <li class="list-group-item"><a href="/syswp/configlist"><span class="iconfont icon-location pull-left"></span>配置项管理<span class="iconfont icon-right pull-right"></span></a></li>
    <!--
    <li class="list-group-item"><a href="/syswp/audioList"><span class="iconfont icon-location pull-left"></span>邀请码管理<span class="iconfont icon-right pull-right"></span></a></li>
    <li class="list-group-item"><a href="/syswp/myMember"><span class="iconfont icon-location pull-left"></span>用户管理<span class="iconfont icon-right pull-right"></span></a></li>
    <li class="list-group-item"><a href="/syswp/myMember"><span class="iconfont icon-location pull-left"></span>用户广告列表<span class="iconfont icon-right pull-right"></span></a></li>
    -->
<!-- nav -->
<jsp:include page="/WEB-INF/views/syswap/wap-menu.jsp"/>
<!-- / -->



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/static/resources/js/jquery-1.12.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/static/resources/js/bootstrap.min.js"></script>
<script src="/static/resources/js/swiper.min.js"></script>


</body>
</html>