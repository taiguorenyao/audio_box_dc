<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function () {
        document.getElementById(this.id).className = "";
        document.getElementById("menu-member").className = "active";
    });
</script>
<div class="admin">
    <form method="post" action="/my/audioList" id="queryPage" name="queryPage">
        <div class="panel admin-panel">
            <div class="panel-head"><strong>我的下线(总人数:${data.size()})</strong></div>
            <div class="padding border-bottom">
                <b><span style="color:red">${errorCode}</span></b>
            </div>
            <table class="table table-hover">
                <tr>
                    <th width="45">下线用户名</th>
                    <th width="200">注册时间</th>
                </tr>
                <c:forEach items="${data}" var="data">
                    <tr>
                        <td>${data.account}</td>
                        <td>${data.addtime}</td>
                    </tr>
                </c:forEach>

            </table>

        </div>
    </form>
</div>

<%
    request.getSession().removeAttribute("errorCode");
%>