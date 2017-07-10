<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-sys-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function(){
        document.getElementById(this.id).className="";
        document.getElementById("menu-user-ad").className="active";
    });
</script>
<div class="admin">
    <form method="post" action="/sys/aviadlist" id="queryPage" name="queryPage">
        <input type="hidden" id="beginPage" name="curPage" value="${curPage}"/>
        <div class="panel admin-panel">
            <div class="panel-head"><strong>用户广告列表</strong></div>
            <div class="padding border-bottom">



                <b><span style="color:red">${errorCode}</span></b>
            </div>
            <table class="table table-hover">
                <tr>
                    <th width="5%">ID</th>
                    <th width="10%">广告标题</th>
                    <th width="10%">所属用户</th>
                    <th width="10%">广告地址</th>
                    <th width="10%">字体颜色</th>
                    <th width="30%">时间</th>
                </tr>
                <c:forEach items="${paginationSupport.items}" var="data">
                    <tr>
                        <td>${data.id}</td>
                        <td>${data.adtitle}</td>
                        <td>${data.uid}</td>
                        <td>${data.adurl}</td>
                        <td>
                            <input type="color" id="fontcolor" name="fontcolor" value="#${data.fontColor}" id="bgcolor" oninput="changeBackground();">
                        </td>
                        <td>${data.addtime}</td>
                    </tr>
                </c:forEach>
            </table>
            <div class="panel-foot text-center">
                <Paging:paging pageContent="paginationSupport"></Paging:paging>
            </div>
        </div>
    </form>
</div>
<%
    request.getSession().removeAttribute("errorCode");
%>