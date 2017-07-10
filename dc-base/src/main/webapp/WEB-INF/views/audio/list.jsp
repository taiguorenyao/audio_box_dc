<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../include/zmos-menu.jsp"/>
<jsp:include page="../include/zmos-head.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function () {
        document.getElementById(this.id).className = "";
        document.getElementById("menu-audio").className = "active";
    });
</script>
<div class="admin">
    <form method="post" action="/my/audioList" id="queryPage" name="queryPage">
        <input type="hidden" id="beginPage" name="curPage" value="${curPage}"/>
        <div class="panel admin-panel">
            <div class="panel-head"><strong>我的视频</strong></div>
            <div class="padding border-bottom">
                <a href="/my/addAudio">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="新增视频"/>
                </a>

                <a href="/my/refreshAudio?uid=${PORTAL_USER.id}">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="刷新缓存"/>
                </a>

                <a href="javascript:delAll()">
                <input type="button"
                       class="button button-small bg-blue margin-big-left"
                       value="批量删除"/>
                </a>

                <a href="/my/delAllAdudioByUID/${PORTAL_USER.id}">
                    <input type="button"
                           class="button button-small bg-blue margin-big-left"
                           value="删除全部"/>
                </a>


                <b><span style="color:red">${errorCode}</span></b>
            </div>
            <table class="table table-hover">
                <tr>
                    <th>
                         <input type="checkbox" id="ckalls" onclick="ckall(this);"/>
                    </th>
                    <th width="45">ID</th>
                    <th width="200">标题</th>
                    <th width="200">地址</th>
                    <th width="100">时间</th>
                    <th width="80">操作</th>
                </tr>
                <c:forEach items="${paginationSupport.items}" var="data">
                    <tr>
                        <td>
                            <input type="checkbox" id="userck${data.id}"  name="userck"
                                   value="${data.id}"/></td>
                        <td>${data.id}</td>
                        <td>${data.title}</td>
                        <td title="${data.url}">
                            <c:choose>
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
                        </td>
                        <td>${data.addtime}</td>
                        <td><a href="/my/delAudio/${data.id}"
                               onClick="return confirm('确定删除?');">删除</a></td>
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

        window.location.href="/my/delallAvi?id="+vals;

    }
</script>
<%
    request.getSession().removeAttribute("errorCode");
%>