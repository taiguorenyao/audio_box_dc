<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-sys-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function(){
        document.getElementById(this.id).className="";
        document.getElementById("menu-config").className="active";
    });
</script>
<div class="admin">
    <div class="tab">
        <div class="tab-head">
            <ul class="tab-nav">
                <li class="active"><a href="#tab-set">编辑配置</a></li>
                <span style="color: red;font-size:20px">${errorCode}</span>
            </ul>
        </div>
        <div class="tab-body">
            <br />
            <div class="tab-panel active" id="tab-set">
                <form method="post" class="form-x" action="/sys/updateConfigdo/${config.id}">
                    <div class="form-group">
                        <div class="label"><label>KEY</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" value="${config.key}"  name="key"  placeholder="请输入KEY" data-validate="required:请输入KEY"  />
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="label"><label>VALUE</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" name="value" value="${config.value}"   size="66" placeholder="请输入VALUE" data-validate="required:请输入VALUE"  />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label"><label>描述</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" name="description" value="${config.description}"  size="66" placeholder="请输入描述" data-validate="required:请输入描述"  />
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