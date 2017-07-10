<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-sys-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function () {
        document.getElementById(this.id).className = "";
        document.getElementById("menu-center-domain").className = "active";
    });
</script>
<div class="admin">
    <form method="post" action="/sys/domaincenterlist" id="queryPage"
          name="queryPage">
        <input type="hidden" id="beginPage" name="curPage" value="${curPage}"/>
        <div class="panel admin-panel">
            <div class="panel-head"><strong>中间页域名管理</strong></div>
            <div class="padding border-bottom">
                <a href="/sys/addDocmainCenter">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="增加新域名"/>
                </a>

                <a href="javascript:;"
                   onclick="subInput();">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="强制变更检测域名"/>
                </a>

                <a href="javascript:;"
                   onclick="delallData();">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="批量删除"/>
                </a>

                <b><span style="color:red">${errorCode}</span></b>
            </div>
            <table class="table table-hover">
                <tr>
                    <th width="5%">
                        <input type="checkbox" id="ckalls" onclick="ckall(this);"/>
                    </th>
                    <th width="5%">ID</th>
                    <th width="10%">域名</th>
                    <th width="10%">状态</th>
                    <th width="10%">最后一次检测记录</th>
                    <th width="10%">操作</th>
                </tr>
                <c:forEach items="${paginationSupport.items}" var="data">
                    <tr>
                        <td>
                            <input type="checkbox" id="userck${data.id}"  name="userck"
                                   value="${data.id}"/></td>
                        <td>${data.id}</td>
                        <td>${data.val}</td>
                        <td>

                            <c:if test="${data.status eq '0'}">
                                <span style="color:green">使用中</span>
                            </c:if>
                            <c:if test="${data.status eq '1'}">
                                <span style="color:red">已爆毒</span>
                            </c:if>
                            <c:if test="${data.status eq '2'}">
                                <span style="color:blue">待用域名</span>
                            </c:if>

                        </td>
                        <td title="${data.txt}">${data.txt}</td>
                        <td>

                            <a href="/sys/delDomainCenter/${data.id}"
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

    function subInput()
    {
        var len = $("input:checkbox:checked").length;
        if (len > 1)
        {
            alert('只能选择一个域名，并且先删除正在检测的域名,才能强制变更成功');
            return;
        }

        var vals = "";
        $("[id^='userck']").each(function(){

            var cked = document.getElementById(this.id).checked;
            if (cked)
            {
                vals += this.value;
            }
        });

        if (vals == "")
        {
            alert('请勾选数据');
            return;
        }

        if (!confirm("确定选择["+vals+"]这个域名？"))
        {
            return;
        }

        window.location.href="/sys/refreshDomainCenter/"+vals;


    }


    function　delallData()
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

        window.location.href="/sys/deldomains_center?id="+vals;
    }

</script>
<%
    request.getSession().removeAttribute("errorCode");
%>