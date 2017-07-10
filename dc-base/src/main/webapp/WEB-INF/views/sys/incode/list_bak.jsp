<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-sys-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function(){
        document.getElementById(this.id).className="";
        document.getElementById("menu-in-code").className="active";
    });
</script>
<div class="admin">
    <form method="post" action="/sys/incodelist" id="queryPage" name="queryPage">
        <input type="hidden" id="beginPage" name="curPage" value="${curPage}"/>
        <div class="panel admin-panel">
            <div class="panel-head"><strong>邀请码管理</strong></div>
            <div class="padding border-bottom">
                <a href="/sys/addInCode">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="生成邀请码"/>
                </a>


                <b><span style="color:red">${errorCode}</span></b>
            </div>
            <table class="table table-hover">
                <tr>
                    <th width="5%">ID</th>
                    <th width="10%">邀请码</th>
                    <th width="10%">状态</th>
                    <th width="30%">时间</th>
                    <th width="30%">使用用户</th>
                </tr>
                <c:forEach items="${paginationSupport.items}" var="data">
                    <tr>
                        <td>${data.id}</td>
                        <td>${data.code}</td>
                        <td><c:if test="${data.state eq '0'}"><span style="color:#00aa00">未使用</span></c:if> <c:if test="${data.state eq '1'}"><span style="color:red">已使用</span></c:if></td>
                        <td>${data.addtime}</td>
                        <td>${data.account}</td>
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