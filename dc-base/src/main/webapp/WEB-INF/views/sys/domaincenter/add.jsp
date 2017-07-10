<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-sys-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function(){
        document.getElementById(this.id).className="";
        document.getElementById("menu-center-domain").className="active";
    });
</script>
        <div class="admin">
    <div class="tab">
        <div class="tab-head">
            <ul class="tab-nav">
                <li class="active"><a href="#tab-set">域名添加</a></li>
                <span style="color: red;font-size:20px">${errorCode}</span>
            </ul>
        </div>
        <div class="tab-body">
            <br />
            <div class="tab-panel active" id="tab-set">
                <form method="post" class="form-x" action="/sys/addDomainCenterDo">

                    <div class="form-group">
                        <div class="label"><label>域名地址</label></div>
                        <div class="field">
                            <b style="color:red">请输入域名多个地址英文逗号【,】隔开,可直接复制括号内英文逗号</b><br/>
                            <textarea class="form-control" rows="10" cols="70" id="val" name="val" placeholder="请输入域名多个地址英文逗号(,)隔开"  data-validate="required:请输入域名地址"></textarea>
                        </div>
                    </div>

                    <div class="form-button">
                        <button class="button bg-main" type="submit">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%
    request.getSession().removeAttribute("errorCode");
%>