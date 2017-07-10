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
    <![endif]-->
</head>
<body>

<div class="list-group">
    <div class="list-group-item">
        <h4 class="list-group-item-heading"><span class="text-danger">${errorCode}</span></h4>
        <p class="list-group-item-text text-muted">

            <a href="/wp/refreshAudio?uid=${PORTAL_USER.id}">
                <input type="button"
                       class="form-control"
                       value="刷新缓存"/>
            </a>
        </p>

        <p class="list-group-item-text text-muted">

            <a href="/wp/addAudio">
                <input type="button"
                       class="form-control"
                       value="添加视频"/>
            </a>


        </p>

        <p class="list-group-item-text text-muted">

            <a href="javascript:delAll()">
                <input type="button"
                       class="form-control"
                       value="批量删除"/>
            </a>

        </p>


        <p class="list-group-item-text text-muted">

            <a href="/wp/delAllAdudioByUID/${PORTAL_USER.id}">
                <input type="button"
                       class="form-control"
                       value="删除全部"/>
            </a>

        </p>

        <p class="list-group-item-text text-muted">

            <input type="checkbox" id="ckalls" onclick="ckall(this);"/>

        </p>


        <c:if test="${!empty sort_url}">
            <textarea cols="50" rows="5">${sort_url}</textarea>
        </c:if>

    </div>
</div>
<form method="post" action="/wp/audioList" id="queryPage" name="queryPage">
    <input type="hidden" id="beginPage" name="curPage" value="${curPage}"/>
<div class="list-media m-t-sm m-b-nav" id="batchList">
<c:forEach items="${paginationSupport.items}" var="data">
    <div class="media clearfix">
        <div class="media-left">
            <input type="checkbox" id="userck${data.id}"  name="userck"
                   value="${data.id}"/>${data.id}
             </div>
        <div class="media-body">
            <h5 class="media-heading">${data.title}</h5>
            <h5 class="text-danger"  title="${data.url}"><c:choose>
                <c:when test="${fn:length(data.url) > 10}">
                    <c:out value="${fn:substring(data.url, 0, 10)}......"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${data.url}"/>
                </c:otherwise>
            </c:choose>
                <c:if test="${data.types eq '0'}">
                    <a href="${data.url}">预览MP4</a>
                </c:if>
                </h5>
            <p>${data.addtime}<span class="pull-right">
                <a href="/wp/delAudio/${data.id}"
                   onClick="return confirm('确定删除?');">删除</a>
            </span></p>
        </div>
    </div>
</c:forEach>
</div>

<div class="panel-foot text-center">
    <Paging:paging pageContent="paginationSupport"></Paging:paging>
</div>
<!-- / -->
</form>
<!-- nav -->
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
<script type="text/javascript">
    function ckall() {
        if (document.getElementById("ckalls").checked) {

            $("[id^='userck']").each(function(){
                document.getElementById(this.id).checked=true;
            });
        } else {
            $("[id^='userck']").each(function(){
                document.getElementById(this.id).checked=false;
            });
        }
    }


    function delAll()
    {
        var vals = "";
        $("[id^='userck']").each(function(){

            var cked = document.getElementById(this.id).checked;
            if (cked)
            {
                vals += this.value+",";
            }
        });

        if (vals == "")
        {
            alert('请勾选要删除的数据');
            return;
        }

        if (!confirm("确定删除["+vals+"]这些数据吗？"))
        {
            return;
        }

        window.location.href="/wp/delallAvi?id="+vals;

    }
</script>
</body>

</html>
<%
    request.getSession().removeAttribute("errorCode");
%>