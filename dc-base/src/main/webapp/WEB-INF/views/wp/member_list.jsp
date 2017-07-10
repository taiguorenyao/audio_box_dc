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
    <title>我的盒子-我的下线</title>
    <link rel="stylesheet" type="text/css" href="/static/resources/fonts/iconfont.css">
    <link rel="stylesheet" type="text/css"  href="/static/resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/static/resources/css/swiper.min.css">
    <link rel="stylesheet" type="text/css" href="/static/resources/css/ddwang-1.0.1.css">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="list-group">
    <div class="list-group-item">
        <h4 class="list-group-item-heading"><span class="text-danger">${errorCode}</span></h4>
        <h4 class="list-group-item-heading"><span class="text-danger">我的下线总数：${data.size()}</span></h4>
    </div>
</div>
<div class="list-media m-t-sm m-b-nav" id="batchList">
<c:forEach items="${data}" var="data">
    <div class="media clearfix">
        <div class="media-body">
            <h5 class="media-heading">${data.account}</h5>
            <h5 class="media-heading">${data.addtime}</h5>
        </div>
    </div>
</c:forEach>
</div>
<a href="#" class="navbar-fixed-bottom" id="goTop">返回<br>顶部</a>
<!-- / -->
<br/>
<br/>
<br/>
<jsp:include page="/WEB-INF/views/wap/wap-menu.jsp"/>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/static/resources/js/jquery-1.12.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/static/resources/js/bootstrap.min.js"></script>
<script src="/static/resources/js/swiper.min.js"></script>
</body>
</html>
<%
    request.getSession().removeAttribute("errorCode");
%>