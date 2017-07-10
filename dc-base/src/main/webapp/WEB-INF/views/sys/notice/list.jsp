<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-sys-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function () {
        document.getElementById(this.id).className = "";
        document.getElementById("menu-notice").className = "active";
    });
</script>
<div class="admin">
    <form method="post" action="/sys/noticelist" id="queryPage"
          name="queryPage">
        <input type="hidden" id="beginPage" name="curPage" value="${curPage}"/>
        <div class="panel admin-panel">
            <div class="panel-head"><strong>公告管理</strong></div>
            <div class="padding border-bottom">
                <a href="/sys/addNotice">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="增加"/>
                </a>

                <b><span style="color:red">${errorCode}</span></b>
            </div>
            <table class="table table-hover">
                <tr>
                    <th width="5%">ID</th>
                    <th width="25%">标题</th>
                    <th width="40%">地址</th>
                    <th width="20%">时间</th>
                    <th width="10%">操作</th>
                </tr>
                <c:forEach items="${paginationSupport.items}" var="data">
                    <tr>
                        <td>${data.id}</td>
                        <td>${data.title}</td>
                        <td title="${data.url}">${data.url}</td>
                        <td>${data.addtime}</td>
                        <td>
                            <a href="/sys/delNotice/${data.id}"
                               onclick="return confirm('是否确定删除');">
                                <input type="button"
                                       class="button button-small bg-blue margin-big-left"
                                       value="删除"/>
                            </a>

                        </td>
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