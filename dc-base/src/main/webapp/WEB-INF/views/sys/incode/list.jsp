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
            <div class="panel-head"><strong>用户邀请码管理</strong></div>
            <div class="padding border-bottom">
                <input type="text" id="localtionUser"/>
                <a href="javascript:adduserIncode();">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="生成邀请码"/>
                </a>


                <b><span style="color:red">${errorCode}</span></b>
            </div>
            <div class="padding border-bottom">
            <c:forEach items="${paginationSupport.items}" var="data">
               <input type="radio" value="${data.id}" name="suser" id="ids_${data.id}" data-attr="${data.account}" onchange="selectUser(${data.id});" />${data.account}
            </c:forEach>
            </div>
            <div class="panel-foot text-center">
                <Paging:paging pageContent="paginationSupport"></Paging:paging>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    function adduserIncode()
    {
        var luser = $("#localtionUser").val();
        console.log(luser);
        if (luser == "")
        {
            alert("目标用户不能为空!");
            return;
        }

        if (confirm("确定给"+luser+"生成邀请码吗？"))
        {
            val = $('input:radio[name="suser"]:checked').val();
           window.location.href="/sys/addInCode?uid="+val;
        }




    }

    function selectUser(id)
    {
        var luser = $("#ids_"+id).attr("data-attr");
        $("#localtionUser").val(luser);

    }

</script>
<%
    request.getSession().removeAttribute("errorCode");
%>