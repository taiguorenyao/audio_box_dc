<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<style type="text/css">
	.cls_container{border:0px solid #ccc;width:430px;font-size:12px;height:24px;overflow:hidden;    z-index: 10;
		margin-top: -32px;    margin-left: 30%;}
	.cls_container ul{list-style-type:none;margin:0;padding:0;margin-left:32px;}
	.cls_container ul li{height:24px;line-height:24px;width:320px;float:left;display:inline;}
</style>
<div class="lefter">
	<div class="logo"><a href="index.html" target="_blank"><img src="/static/logo/logo.png" style="width:166px;" alt="盒子系统" /></a></div>
</div>
<div class="righter nav-navicon" id="admin-nav">
	<div class="mainer">
		<div class="admin-navbar">

            <span class="float-right">
                <a class="button button-little bg-yellow" href="/login/userlogin">注销登录</a>
            </span>
			<span class="float-right" style="margin-right:38%;">
				<span class="button button-little bg-yellow" id="times" ></span>
				<a class="button button-little bg-yellow"  href="https://jq.qq.com/?_wv=1027&k=49kWi5L">点击链接加入群【诚信盒子交流群】</a>
			</span>
			<ul class="nav nav-inline admin-nav">
				<li class="active"><a href="/index" class="icon-home"> 我的盒子</a>
					<ul id="portal-all_menu" class="portal-all_menu">
						<li id="menu-ad" class="active"><a href="/index">广告配置</a></li>
						<li id="menu-audio"  ><a href="/my/audioList">我的视频</a></li>
						<li id="menu-member"  ><a href="/my/myMember">我的下线</a></li>
						<c:if test="${PORTAL_USER.advip eq '1'}">
						<li id="menu-person-ad" ><a href="/my/addpersonad">图片配置</a></li>
						</c:if>
					</ul>
				</li>
		</div>
		<div class="admin-bread">
			<span>您好，${PORTAL_USER.account}，欢迎您的光临。</span>
			<c:if test="${PORTAL_USER.userInCode ne '' && PORTAL_USER.userInCode ne null}">
				<span style="color:red;font-size:16px;">我的邀请码：${PORTAL_USER.userInCode}</span>
			</c:if>
			<ul class="bread">
				<li><a href="/index" class="icon-home"> 我的工作台</a></li>
				<li>盒子</li>
			</ul>
			<!-- 公告展示区 -->
			<div id="myscroll" class="cls_container" style="color: #red;">
				<ul>
					<Paging:notice types="0"></Paging:notice>
				</ul>
			</div>
			<div id="showhint"></div>
		</div>
	</div>
</div>



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

<script type="text/javascript">
	function $(element){
		if(arguments.length>1){
			for(var i=0,length=arguments.length,elements=[];i<length;i++){
				elements.push($(arguments[i]));
			}
			return elements;
		}
		if(typeof element=="string"){
			return document.getElementById(element);
		}else{
			return element;
		}
	}
	var Class={
		create:function(){
			return function(){
				this.initialize.apply(this,arguments);
			}
		}
	}
	Function.prototype.bind=function(object){
		var method=this;
		return function(){
			method.apply(object,arguments);
		}
	}
	var Scroll=Class.create();
	Scroll.prototype={
		initialize:function(element,height){
			this.element=$(element);
			this.element.innerHTML+=this.element.innerHTML;
			this.height=height;
			this.maxHeight=this.element.scrollHeight/2;
			this.counter=0;
			this.scroll();
			this.timer="";
			this.element.onmouseover=this.stop.bind(this);
			this.element.onmouseout=function(){this.timer=setTimeout(this.scroll.bind(this),1000);}.bind(this);
		},
		scroll:function(){
			if(this.element.scrollTop<this.maxHeight){
				this.element.scrollTop++;
				this.counter++;
			}else{
				this.element.scrollTop=0;
				this.counter=0;
			}
			if(this.counter<this.height){
				this.timer=setTimeout(this.scroll.bind(this),20);
			}else{
				this.counter=0;
				this.timer=setTimeout(this.scroll.bind(this),3000);
			}
		},
		stop:function(){
			clearTimeout(this.timer);
		}
	}
	var myscroll = new Scroll("myscroll",24);

</script>

