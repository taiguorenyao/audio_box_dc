<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-sys-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function(){
        document.getElementById(this.id).className="";
        document.getElementById("menu-user").className="active";
    });
</script>
<div class="admin">
    <form method="post" action="/sys/userlist" id="queryPage" name="queryPage">
        <input type="hidden" id="beginPage" name="curPage" value="${curPage}"/>

        <div class="panel admin-panel">
            <div class="panel-head"><strong>用户管理</strong></div>
            <div class="padding border-bottom">
                <b><span style="color:red">${errorCode}</span></b>
                <input type="text" id="keyword" name="keyword" placeholder="用户名\QQ\手机\上线用户" value="${keyword}"/>
                <a href="javascript:searchParam()">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="搜索"/>
                </a>
            </div>
            <table class="table table-hover">
                <tr>
                    <%--<th width="5%">ID</th>--%>
                    <th width="10%">用户名</th>
                    <th width="10%">用户信息</th>
                    <th width="15%">用户邀请码</th>
                    <th width="10%">上线用户</th>
                    <th width="10%">注册时间</th>
                    <th width="10%">51la账户</th>
                    <th width="10%">操作</th>
                </tr>
                <c:forEach items="${paginationSupport.items}" var="data">
                    <tr>
                    <%--<td>${data.id}</td>--%>
                   <td>${data.account}</td>
                   <td>
                       真实姓名：${data.t_name}<br/>
                       手机号：${data.phone}<br/>
                       QQ：${data.qq}<br/>
                       密码：${data.pwd}<br/>
                       下线总数：${data.underCount}
                   </td>
                   <td>${data.userInCode}-
                   <c:if test="${data.userInType eq '1'}">
                        <span style="color:green">已开通</span>
                   </c:if>
                   <c:if test="${data.userInType ne '1'}">
                       <span style="color:red">未开通</span>
                   </c:if>
                   </td>
                   <td>${data.up_user}</td>
                   <td>${data.addtime}</td>
                   <td>${data.fivela}</td>
                   <td>
                       <a href="/sys/updateUser/${data.id}">
                           <input type="button"
                                  class="button button-small bg-blue margin-big-left"
                                  value="修改"/>
                       </a>
                       <a href="/sys/delUser/${data.id}">
                           <input type="button" onclick="return confirm('是否确定删除');"
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
<script type="text/javascript">
    function searchParam()
    {
        queryPage.submit();
    }
</script>
<%
request.getSession().removeAttribute("errorCode");
%>