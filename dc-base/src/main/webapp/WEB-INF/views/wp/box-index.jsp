<%@ page contentType="text/html;charset=UTF-8"%>
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
        <p class="list-group-item-text text-muted">

            <a href="/wp/refreshAdVip?uid=${PORTAL_USER.id}">
                <input type="button"
                       class="form-control"
                       value="刷新缓存"/>
            </a>
        </p>

        <p class="list-group-item-text text-muted">

            <a href="/wp/sinaUrl?uid=${PORTAL_USER.id}">
                <input type="button"
                       class="form-control"
                       value="生成短地址"/>
            </a>

            <a href="javascript:saveDate();">
                <input type="button"
                       class="form-control"
                       value="批量保存广告"/>
            </a>
        </p>
        <p class="list-group-item-text text-muted"></p>


        <c:if test="${!empty sort_url}">
            <textarea cols="50" id="biao1" rows="5">${sort_url}</textarea>
            <input type="button" onClick="copyUrl()" value="点击复制" />
        </c:if>

    </div>
</div>

<!--  list-record -->
<form method="post" id="subfrom" name="subfrom" class="form-x" action="/wp/saveVipAds">
<div class="list-record container">
<c:forEach items="${vipadlist}" var="vip" varStatus="index">
    <div class="record-item row">
        <div class="record-header"></div>
        <div class="record-body">
            <p>${index.index+1}|字体颜色-<input type="color" name="adcolor" width="100%" id="t_font${index.index+1}" value="#${vip.fontColor}" id="bgcolor" oninput="changeBackground()">
                |背景颜色<input type="color" name="bgColor" width="100%" id="t_bgcolor${index.index+1}" value="#${vip.bgColor}" oninput="changeBackground()">
             |
                <select name="adsize" id="adsize${index.index+1}">
                    <c:if test="${!empty vip.size}">
                        <option value="${vip.size}">${vip.size}px</option>
                    </c:if>
                    <option value="16">16px</option><option value="17">17px</option>
                    <option value="18">18px</option><option value="19">19px</option>
                    <option value="20">20px</option><option value="21">21px</option>
                    <option value="22">22px</option><option value="23">23px</option>
                    <option value="24">24px</option><option value="25">25px</option>
                    <option value="26">26px</option><option value="27">27px</option>
                    <option value="28">16px</option><option value="29">29px</option>
                    <option value="30">30px</option>
                </select>
            <input id="t_id${index.index+1}" name="adid" type="hidden" value="${vip.id}" width="100%" /><br/>
            <span class="emotion${index.index+1}"><img src='/static/face/arclist/1.gif' /></span>
            <div class="form-group">
                <label class="sr-only"></label>
            <input id="t_t${index.index+1}" type="text" name="adname" value="${vip.adtitle}" class="form-control" placeholder="广告标题"/><br/>
            </div>
            <div class="form-group">
                <label class="sr-only"></label>
                <input id="t_url${index.index+1}" type="text" name="adurl" value="${vip.adurl}" class="form-control" placeholder="广告地址"/><br/>
            </div>
            <a href="javascript:saveVipAd(${index.index+1})"><button type="button" class="btn btn-danger btn-block">保存</button></a>
            </p>
        </div>
    </div>
</c:forEach>
</div>
</form>
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
<script type="text/javascript" src="/static/face/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/face/js/jquery.qqFace.js"></script>
</body>
<script type="text/javascript">

    function saveDate()
    {
        subfrom.submit();
    }

    function copyUrl()
    {
        var Url2=document.getElementById("biao1");
        Url2.select();
        document.execCommand("Copy");
        alert("已复制好，可贴粘。");
    }

    function saveVipAd(i)
    {
        var t_t = $("#t_t"+i).val();
        var t_url = $("#t_url"+i).val();
        var t_font = $("#t_font"+i).val();
        var t_id = $("#t_id"+i).val();
        var adsize = $("#adsize"+i).val();
        var bgcolor = $("#t_bgcolor"+i).val();
        $.ajax({
            url: '/wp/saveVipAd',
            type: 'post',
            data: "t_id="+t_id+"&t_t="+t_t+"&t_url="+t_url+"&t_font="+t_font+"&adsize="+adsize+"&bgcolor="+bgcolor,
            dataType: 'json',
            success: function (data) {
                if (data.code == 0) {
                    alert('成功');
                    window.location.reload();
                    return;
                } else {
                    alert('失败');
                    window.location.reload();
                    return;
                }
            },
            error: function () {

            }
        });
    }

    function changeBackground(colorValue){document.body.style.bakcgroundColor = colorValue;}


    $(function(){
        for (var i=1;i<=15;i++)
        {
            $('.emotion'+i).qqFace({
                assign:'t_t'+i, //给输入框赋值
                path:'/static/face/arclist/' //表情图片存放的路径
            });
        }
    });
</script>
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
</html>
<%
    request.getSession().removeAttribute("errorCode");
    request.getSession().removeAttribute("sort_url");
%>