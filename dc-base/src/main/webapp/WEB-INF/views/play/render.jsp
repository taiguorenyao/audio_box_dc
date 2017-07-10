<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
﻿﻿<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>正在进入</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<%-- banner --%>
<link href="/static/css/ectouch.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/static/css/swiper.min.css">
<style>

#ad_value_{animation:change 10s linear 0s infinite;font-size:30px;font-weight:700;}
@keyframes change{0%   {color:#333;}25%{color:#ff0;}50%{color:#f60;}75%{color:#cf0;}100% {color:#f00;}}
#slider-banner{ background-color:#fff}
#slider-banner img{ width:100%;}
#slider-banner .swiper-container-horizontal>.swiper-pagination{ width:auto; right:30px; left:inherit}
#slider-banner .swiper-pagination-bullet-active{ background-color:#fff}
</style>

<style type="text/css">
    div.floats {
        position:absolute;
        left:-1000px;
        border:solid 1px #777;
        padding:3px;
        background:#000;
        color:white;
        width:40px;
        height:40px;
        /*opacity: 0.5;*/
        filter:alpha(opacity=50);
        border-radius:166px;border:solid rgb(100,100,100) 1px;
        background-image: url("${PF_IMG}");
        background-size:100% 100%;
    }
    div#test1,div#test2{top:100px;}/*居中向左右定位容器初始高度*/
    div#test3,div#test4{top:200px;}/*左右定位的两个容器初始高度*/
</style>

<script type="text/javascript">
    var D=new Function('obj','return document.getElementById(obj);')
    function htmlbody(){
        return (
                document.documentElement.clientHeight<=document.body.clientHeight
                &&document.documentElement.clientHeight!=0
        )
                ?document.documentElement:document.body;
    }
    //浏览器滚动条位置
    function scrollLeft(){return (!window.pageYOffset)?htmlbody().scrollLeft:window.pageXOffset;}
    function scrollTop(){return (!window.innerHeight)?htmlbody().scrollTop:window.pageYOffset;}

    //实际应距左距离
    function getleft(strobjs,strLeftType,strleft){
        var temp_getleft = 0;
        if (strLeftType=="left"){
            temp_getleft = scrollLeft()*1 + strleft*1;
        }else if (strLeftType=="mid"){
            (strleft*1<0)
                    ?temp_getleft = scrollLeft()*1 + strleft*1
                    + htmlbody().clientWidth*1/2 - strobjs.offsetWidth*1
                    :temp_getleft = (scrollLeft()*1+strleft*1 + htmlbody().clientWidth*1/2);
        }else if (strLeftType=="right"){
            temp_getleft
                    = scrollLeft()*1 + htmlbody().clientWidth*1
                    - strleft*1 - strobjs.offsetWidth*1;
        }
        return temp_getleft;
    }

    function moveTips(strobj,theTop,theLeft,theLeftType) {
        var old,nowobj = D(strobj);
        var nowleft = nowobj.style.left.replace("px","")*1;//返回在改变窗口大小或移动横滚动条前的距左部距离（数值）
        var temp_left = getleft(nowobj,theLeftType,theLeft);//实际应距左距离
        var re_theTop = theTop;
        if (temp_left!=nowleft){//横向递增
            (Math.abs(temp_left-nowleft)>3&&Math.abs(temp_left-nowleft)<600)
                    ?((temp_left>nowleft)?nowleft += Math.abs(temp_left-nowleft)/5
                    :nowleft -= Math.abs(temp_left-nowleft)/5)
                    :nowleft = temp_left;
            nowobj.style.left = nowleft + "px";
        }
        if (!openweb){old = re_theTop;var openweb;}/*这是默认高度*/;
        var pos,tt=50;
        pos = scrollTop()*1-nowobj.offsetTop*1+re_theTop*1;
        pos = nowobj.offsetTop+pos/10;//纵向开始递增
        if (pos < re_theTop) pos = re_theTop;
        if (pos != old) {nowobj.style.top = pos+"px";tt=5;}
        old = pos;
        moveTips(strobj,theTop,theLeft,theLeftType);
        //setTimeout("moveTips('"+strobj+"','"+theTop+"','"+theLeft+"','"+theLeftType+"')",9999999);
    }

</script>
<%-- banner --%>
</head>

<body>

<%-- 如果只允许在微信打开则判断是否是微信浏览器， 是则跳转，否则跳转到新浪首页  --%>

<!--head-->
<c:if test="${PLAY_TOP_AD_IMG_S eq '1'}">
<dl class="user_top_avi" style="position:static;">
<dd>
	<a href="${PLAY_TOP_AD_IMG_URL}"><image src="${PLAY_TOP_AD_IMG}" width="100%" height="90px;" /></a>
</dd>
</c:if>
<div class="">
   
</div>
</dl>

<section class="goodsInfo" >
	<div id="qrcodeCanvas" style="background:#ccc">
	  <center><h1 style="font-size:16px;color:#800080;">
    <a href="${play_person_ad.txt_url}" style="color:#${play_person_ad.fontcolor}">${play_person_ad.txt_title}</a>
</h1></center>
	<center>
    <c:if test="${playAvi.types eq '0'}">
      <video id="video" controls="" src="${playAvi.url}" width="100%" height="160"></video>
    </c:if>
      <!-- 315 -->
    <c:if test="${playAvi.types eq '1'}">

    <iframe frameborder="0" width="100%" height="160px" src="${playAvi.url}" allowfullscreen></iframe>
    </c:if>
    </center>
	</div>
</section>

<section class="goodsInfo" style="background-color: #fff;">
 <div class="list_box padd1 radius10" style="background: #fff;padding-top:0;padding-bottom:0;">
     <ul>
        <c:forEach items="${list}" var="vip">
	   <a href="${vip.adurl}" style="border-bottom:1px dashed red;"> <li style="font-size:${vip.size}px;padding-top:0px;text-align:center;color:#${vip.fontColor};background-color:#${vip.bgColor};border-radius: 66px;" id="replyFace_${vip.id}">${vip.adtitle} </li> </a>
	    </c:forEach>
     </ul>

 </div>	
 <p class="order_handler" style="padding-bottom:20px">
 
</section>


<c:if test="${XF_S eq '1'}">
<div id="content_xf" class="footer mr-t20" style="background-color:#096; z-index:999; position:fixed; bottom:-7px; left:-10px; width:100%; _position:absolute;
 _top: (documentElement.scrollTop + documentElement.clientHeight-this.offsetHeight); overflow:visible;">

    <section id="slider-banner">
          <div class="swiper-container tb_carousel">
            <div class="swiper-wrapper">
              <div class="swiper-slide" data-href="#"> <a href="javascript:reloads();"><a href="${XF_1_URL}"><img  width="100%" height="85px;" src="${XF_1}" alt="" /></a> </div>
              <div class="swiper-slide" data-href="#"> <a href="javascript:reloads();"> <a href="${XF_2_URL}"><img  width="100%" height="85px;" src="${XF_2}" alt="" /></a> </div>
              <div class="swiper-slide" data-href="#"> <a href="javascript:reloads();"><a href="${XF_3_URL}"><img  width="100%" height="85px;" src="${XF_3}" alt="" /></a> </div>
            </div>
            <div class="swiper-pagination"></div>
          </div>
    </section>

</div>
</c:if>
<script type="text/javascript">

    function reloads()
    {
        window.location.reload();
    }

</script>

<c:if test="${PLAY_TOP_FOOTER_IMG_S eq '1'}">
<div id="content" class="footer mr-t20">
	<p class="region">
		<a href="${PLAY_TOP_FOOTER_IMG_URL}"><image src="${PLAY_TOP_FOOTER_IMG}" width="100%" height="100px;" /></a>
	</p>
</div>
</c:if>


<%-- start --%>
<c:if test="${!empty play_person_ad.img_url}">
<div id="content" class="footer mr-t20">
	<p class="region">
		<a href="${play_person_ad.img_access_url}"><image src="${play_person_ad.img_url}" width="100%" height="500px;" /></a>
	</p>
</div>
</c:if>
<%-- end --%>





<c:if test="${uid ne '20170525669835' && uid ne '20170612238176'}">
<div id="content" class="footer mr-t20">
	<p class="region" style="font-size:16px">
        <a href="//www.51.la/?19155999" target="_blank"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="//img.users.51.la/19155999.asp" style="border:none;display:none;" /></a>
		本平台免费提供联系微信：3353400836<br/>
        特别提示：本平台禁止用于推广违反法律的淫秽、色情视频，如有违规，我方有权停止站点服务，并配合相关部门调查。
<a href="http://www.51.la/?19092138" target="_blank" style="display:none;"><img alt="&#x6211;&#x8981;&#x5566;&#x514D;&#x8D39;&#x7EDF;&#x8BA1;" src="http://img.users.51.la/19092138.asp" style="border:none" /></a>
	</p>
</div>
</c:if>
<div id="content" class="footer mr-t20"></div>
<div id="content" class="footer mr-t20"></div>
<div id="content" class="footer mr-t20"></div>
<div id="content" class="footer mr-t20"></div>

<!-- 如果为1 则该会员展示默认地址 -->

<c:if test="${circel_s eq '1'}">
    <c:if test="${PF_S eq '1'}">
        <a href="${CIRCEL_URL}"><div id="test4"  class="floats"></div></a>
    </c:if>
</c:if>
<!-- 如果不为1 走原流程-->
<c:if test="${circel_s ne '1'}">
    <c:if test="${PF_S eq '1'}">
        <a href="${PF_URL}"><div id="test4"  class="floats"></div></a>
    </c:if>
</c:if>


<script src="/static/js/swiper.min.js"></script>
<script>
    var swiper = new Swiper('.tb_carousel', {
        pagination: '.tb_carousel .swiper-pagination',
        paginationClickable: true,
                autoplay: 2500
    });

</script>
<script type="text/javascript">
    moveTips('test4','200','10','right',"1");
</script>
<script src="/static/js/jquery.js"></script>
<script type="text/javascript">
   //处理表情
   function replace_em(str){
       //str = str.replace(/\</g,'&lt;');
       //str = str.replace(/\>/g,'&gt;');
       str = str.replace(/\n/g,'<br/>');
       str = str.replace(/\[em_([0-9]*)\]/g,"<img src='/static/face/arclist/$1.gif' style='width:24px;height:24px;' />");
       return str;
   }

   $("[id^='replyFace_']").each(function(){
       var val = $(this).text();
       var texts = replace_em(val);
       document.getElementById(this.id).innerHTML=texts;
   });


</script>
</body>
</html>
