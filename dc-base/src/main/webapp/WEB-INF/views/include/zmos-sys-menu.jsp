<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.audio.util.StringUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="lefter">
	<div class="logo"><a href="/index" target="_blank"><img src="/static/logo/logo.png" style="width:166px;" alt="盒子系统" /></a>
		<%
			if (StringUtil.clientIsMoblie(request))
			{
        %>
		<ul id="portal-all_menu_1" class="portal-all_menu">
			<li ><a href="/sys/configlist">配置项管理</a></li>
			<li ><a href="/sys/incodelist">邀请码管理</a></li>
			<li ><a href="/sys/userlist">用户管理</a></li>
			<li ><a href="/sys/aviadlist">用户广告列表</a></li>
			<li ><a href="/sys/domainlist">播放域名管理</a></li>
			<li ><a href="/sys/domaincenterlist">中间页域名管理</a></li>
			<li ><a href="/sys/noticelist">公告管理</a></li>
		</ul>
		<%
			}
		%>

	</div>

</div>
<div class="righter nav-navicon" id="admin-nav">
	<div class="mainer">
		<div class="admin-navbar">
            <span class="float-right">
                <a class="button button-little bg-yellow" href="/login/syslogin">注销登录</a>
            </span>
			<ul class="nav nav-inline admin-nav">
				<li class="active"><a href="/index" class="icon-home"> 我的盒子</a>
					<ul id="portal-all_menu" class="portal-all_menu">
						<li id="menu-config"><a href="/sys/configlist">配置项管理</a></li>
						<li id="menu-in-code"><a href="/sys/incodelist">邀请码管理</a></li>
						<li id="menu-user"><a href="/sys/userlist">用户管理</a></li>
						<li id="menu-user-ad"><a href="/sys/aviadlist">用户广告列表</a></li>
						<li id="menu-domain"><a href="/sys/domainlist">播放域名管理</a></li>
						<li id="menu-center-domain"><a href="/sys/domaincenterlist">中间页域名管理</a></li>
						<li id="menu-notice"><a href="/sys/noticelist">公告管理</a></li>
					</ul>
				</li>
			</ul>

		</div>
		<div class="admin-bread">
			<span>您好，${SYS_USER.account}，欢迎您的光临。</span>
			<ul class="bread">
				<li><a href="/index" class="icon-home"> 我的工作台</a></li>
				<li>盒子</li>
				<!--<li>内容管理</li>-->
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">

</script>

