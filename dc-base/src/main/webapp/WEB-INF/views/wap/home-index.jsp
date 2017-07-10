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
    <title>我的盒子</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/static/resources/fonts/iconfont.css">
    <link rel="stylesheet" type="text/css"  href="/static/resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/static/resources/css/swiper.min.css">
    <link rel="stylesheet" type="text/css" href="/static/resources/css/ddwang-1.0.1.css">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<!--  brand-header -->
<header id="user-header" class="brand-header">
    <div class="header-bg"><img src="/static/resources/images/bg-user.jpg" alt=""></div>
    <a class="header-base" href="javascript:;">
        <i class="iconfont icon-right"></i>
        <div class="col-xs-3">
            <img src="/static/images/zhuanzhuan.gif" alt="" >
        </div>
        <div class="col-xs-9">
            <h4>${PORTAL_USER.account}</h4>
        </div>
        <div class="col-xs-9" id="times" style="color:hotpink">
            <h4></h4>
        </div>
    </a>
</header>
<!-- tabNavBar -->
<ul class="list-group m-t-sm list-user">
    <c:if test="${PORTAL_USER.userInCode ne '' && PORTAL_USER.userInCode ne null}">
        <li class="list-group-item"><span style="color:red;font-size:16px;">我的邀请码：${PORTAL_USER.userInCode}</span></li>
    </c:if>

    <li class="list-group-item">
        <a href="https://jq.qq.com/?_wv=1027&k=49kWi5L">
        <span style="color:red;font-size:16px;">点击链接加入群【诚信盒子交流群】</span>
        </a>
    </li>

    <li class="list-group-item"><a href="/wp/boxindex"><span class="iconfont icon-deliver pull-left"></span>广告配置<span class="iconfont icon-right pull-right"></span></a></li>
    <li class="list-group-item"><a href="/wp/audioList"><span class="iconfont icon-location pull-left"></span>我的视频<span class="iconfont icon-right pull-right"></span></a></li>
    <li class="list-group-item"><a href="/wp/myMember"><span class="iconfont icon-location pull-left"></span>我的下线<span class="iconfont icon-right pull-right"></span></a></li>
    <c:if test="${PORTAL_USER.advip eq '1'}">
    <li class="list-group-item"><a href="/wp/addpersonad"><span class="iconfont icon-like pull-left"></span>图片配置<span class="iconfont icon-right pull-right"></span></a></li>
    </c:if>
</ul>

<!-- nav -->
<jsp:include page="/WEB-INF/views/wap/wap-menu.jsp"/>
<!-- / -->

<script type="text/javascript">
    function disTime()
    {
        document.getElementById("times").innerHTML = "";
        var myDate = new Date();
        var h=myDate.getHours();       //获取当前小时数(0-23)
        var m=myDate.getMinutes();     //获取当前分钟数(0-59)
        var s=myDate.getSeconds();

        if (h >= 0 && h <= 5)
        {
            document.getElementById("times").innerHTML = "当前时间:["+h+":"+m+":"+s+"],夜深了,为了大咖身体健康，请减少熬夜";
        }
        else
        {
            document.getElementById("times").innerHTML = "当前时间:["+h+":"+m+":"+s+"]";
        }
    }
    setInterval("disTime()",1000);



</script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/static/resources/js/jquery-1.12.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/static/resources/js/bootstrap.min.js"></script>
<script src="/static/resources/js/swiper.min.js"></script>
<style>
    .comment{width:680px; margin:20px auto; position:relative}
    .comment h3{height:28px; line-height:28px}
    .com_form{width:100%; position:relative}
    .input{width:99%; height:60px; border:1px solid #ccc}
    .com_form p{height:28px; line-height:28px; position:relative}
    span.emotion{width:42px; height:20px; background:url(icon.gif) no-repeat 2px 2px;
        padding-left:20px; cursor:pointer}
    span.emotion:hover{background-position:2px -28px}
    .qqFace{margin-top:4px;background:#fff;padding:2px;border:1px #dfe6f6 solid;}
    .qqFace table td{padding:0px;}
    .qqFace table td img{cursor:pointer;border:1px #fff solid;}
    .qqFace table td img:hover{border:1px #0066cc solid;}
    #show{width:680px; margin:20px auto}
</style>

</body>
</html>