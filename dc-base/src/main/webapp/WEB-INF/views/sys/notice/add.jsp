<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-sys-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function(){
        document.getElementById(this.id).className="";
        document.getElementById("menu-notice").className="active";
    });
</script>
<div class="admin">
    <div class="tab">
        <div class="tab-head">
            <ul class="tab-nav">
                <li class="active"><a href="#tab-set">增加公告</a></li>
                <span style="color: red;font-size:20px">${errorCode}</span>
            </ul>
        </div>
        <div class="tab-body">
            <br />
            <div class="tab-panel active" id="tab-set">
                <form method="post" class="form-x" action="/sys/addNoticedo">
                    <div class="form-group">
                        <div class="label"><label>公告标题</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto"  name="title"  placeholder="请输入公告标题" data-validate="required:请输入公告标题"  />
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="label"><label>公告访问地址（选填）</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" name="url" size="66" placeholder="如果公告有访问地址则填写"   />
                        </div>
                    </div>



                    <div class="form-button">
                        <button class="button bg-main" type="submit">保存</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%
    request.getSession().removeAttribute("errorCode");
%>