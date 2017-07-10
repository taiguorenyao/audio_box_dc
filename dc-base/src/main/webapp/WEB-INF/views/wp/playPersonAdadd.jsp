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

        <form method="post" class="form-x" action="/wp/addpersonaddo">
            <div class="form-group">
                <div class="label" style="color:#000"><label>顶部文字广告标题</label></div>
                <input type="text" class="form-control" id="txt_title" name="txt_title" value="${personAdEvt.txt_title}" size="66" placeholder="请输入文本标题" data-validate="required:请输入文本标题" value="" />
                <input type="color" id="fontcolor" name="fontcolor" value="#${personAdEvt.fontcolor}" id="bgcolor" oninput="changeBackground();">

            </div>

            <div class="form-group">
                <div class="label" style="color:#000"><label>顶部文字跳转地址</label></div>
                <div class="field">
                    <input type="text" class="form-control" id="txt_url" name="txt_url" value="${personAdEvt.txt_url}" size="66" placeholder="请输入文本地址" data-validate="required:请输入文本地址" value="" />
                </div>
            </div>

            <div class="form-group">
                <div class="label" style="color:#000"><label>底部二维码图片地址</label></div>
                <div class="field">
                    <input type="text" class="form-control" id="img_url" name="img_url" value="${personAdEvt.img_url}" size="66" placeholder="请输入图片地址" data-validate="required:请输入图片地址" value="" />
                </div>
            </div>

            <div class="form-group">
                <div class="label" style="color:#000"><label>底部二维码图片跳转地址</label></div>
                <div class="field">
                    <input type="text" class="form-control" id="img_access_url" name="img_access_url" value="${personAdEvt.img_access_url}" size="66" placeholder="请输入文本地址" data-validate="required:请输入图片广告地址" value="" />
                </div>
            </div>

            <c:if test="${PORTAL_USER.userInType eq '1'}">

                <c:if test="${PORTAL_USER.leval >= 1}">
                    <div class="form-group">
                        <div class="label" style="color:#000"><label>底部悬浮广告位一图片地址</label></div>
                        <div class="field">
                            <input type="text" class="form-control" id="xf_img1" name="xf_img1" value="${personAdEvt.xf_img1}" size="66" placeholder="请输入图片地址" data-validate="required:请输入图片地址" value="" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label" style="color:#000"><label>跳转地址</label></div>
                        <div class="field">
                            <input type="text" class="form-control" id="xf_url1" name="xf_url1" value="${personAdEvt.xf_url1}" size="66" placeholder="请输入访问地址" data-validate="required:请输入图片广告地址" value="" />
                        </div>
                    </div>

                </c:if>

                <c:if test="${PORTAL_USER.leval >=2}">
                    <div class="form-group">
                        <div class="label" style="color:#000"><label>底部悬浮广告位二图片地址</label></div>
                        <div class="field">
                            <input type="text" class="form-control" id="xf_img2" name="xf_img2" value="${personAdEvt.xf_img2}" size="66" placeholder="请输入图片地址" data-validate="required:请输入图片地址" value="" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label" style="color:#000"><label>跳转地址</label></div>
                        <div class="field">
                            <input type="text" class="form-control" id="xf_url2" name="xf_url2" value="${personAdEvt.xf_url2}" size="66" placeholder="请输入访问地址" data-validate="required:请输入图片广告地址" value="" />
                        </div>
                    </div>
                </c:if>

                <c:if test="${PORTAL_USER.leval >= 3}">
                    <div class="form-group">
                        <div class="label" style="color:#000"><label>底部悬浮广告位三图片地址</label></div>
                        <div class="field">
                            <input type="text" class="form-control" id="xf_img3" name="xf_img3" value="${personAdEvt.xf_img3}" size="66" placeholder="请输入图片地址" data-validate="required:请输入图片地址" value="" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label" style="color:#000"><label>跳转地址</label></div>
                        <div class="field">
                            <input type="text" class="form-control" id="xf_url3" name="xf_url3" value="${personAdEvt.xf_url3}" size="66" placeholder="请输入访问地址" data-validate="required:请输入图片广告地址" value="" />
                        </div>
                    </div>
                </c:if>
            </c:if>
            <div class="form-button">
                <button class="btn btn-success btn-block btn-lg" type="submit">保存</button>
            </div>
        </form>
    </section>
    <br/><br/><br/><br/><br/>
</div>
<script type="text/java">
    function changeBackground(colorValue){document.body.style.bakcgroundColor = colorValue;}
</script>
<%
    request.getSession().removeAttribute("errorCode");
%>
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