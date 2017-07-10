<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta content="yes" name="apple-mobile-web-app-capable"/>
    <meta content="yes" name="apple-touch-fullscreen"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>我的盒子</title>
    <link rel="stylesheet" type="text/css" href="/static/resources/fonts/iconfont.css">
    <link rel="stylesheet" type="text/css"  href="/static/resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/static/resources/css/swiper.min.css">
    <link rel="stylesheet" type="text/css" href="/static/resources/css/ddwang-1.0.1.css">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="admin">
<div class="tab-head">
    <ul class="tab-nav">
        <span style="color: red;font-size:20px">${errorCode}</span>
    </ul>
</div>
<section class="container" id="form-register">

    <form method="post" class="form-x" action="/wp/addAudiodo">
        <div class="form-button">
            <button class="btn btn-success btn-block btn-lg"type="submit">提交</button>
        </div>
        <div class="form-group">
            <label class="sr-only">视频标题</label>
            <input type="text" class="form-control" id="title" name="title" size="20" placeholder="请输入视频标题" data-validate="required:请输入视频标题" value="" />
            <input type="radio" id="types1" name="types" value="0" checked/>MP4|
            <input type="radio" id="types2" name="types" value="1"/>ifarme
        </div>

        <div class="form-group">
            <b style="color:red">输入视频多个地址用【@】隔开如下图</b><br/>
            <b >https://v.qq.com/iframe/player.html?vidh&tiny.mp4@</b><br/>
            <b >https://v.qq.com/iframe/player.html?vidh&tiny.mp4@</b><br/>
            <b >https://v.qq.com/iframe/player.html?vidh&tiny.mp4@</b><br/>
            <textarea class="form-control" rows="10" cols="70" id="url" name="url" placeholder=""  data-validate="required:请输入视频地址"></textarea>
        </div>


    </form>
</section>

</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/static/resources/js/jquery-1.12.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/static/resources/js/bootstrap.min.js"></script>
<script src="/static/resources/js/swiper.min.js"></script>
<jsp:include page="/WEB-INF/views/wap/wap-menu.jsp"/>

</body>
</html>
<%
    request.getSession().removeAttribute("errorCode");
%>