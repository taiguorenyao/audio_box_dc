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
    <div class="tab">
        <div class="tab-head">
            <ul class="tab-nav">
                <li class="active"><a href="#tab-set">用户管理</a></li>
                <span style="color: red;font-size:20px">${errorCode}</span>
            </ul>
        </div>
        <div class="tab-body">
            <br />
            <div class="tab-panel active" id="tab-set">
                <form method="post" class="form-x" action="/sys/updateUserdo/${boxUser.id}">
                    <div class="form-group">
                        <div class="label"><label>用户ID</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto"  value="${boxUser.id}" size="66"  disabled/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label"><label>VIP</label></div>
                        <div class="field">
                            <c:if test="${boxUser.advip eq '1'}">
                                <input type="radio" value="1" name="advip" checked/><span style="color:green">他是广告VIP</span>
                                <input type="radio" value="0" name="advip"/>关闭他广告VIP
                            </c:if>
                            <c:if test="${boxUser.advip ne '1'}">
                                <input type="radio" value="1" name="advip" />开通他VIP
                                <input type="radio" value="0" name="advip" checked /><span style="color:red">他不是广告VIP</span>
                            </c:if>
                            ps:该功能只影响该用户是否有权限配置自己的播放页面文字广告和图片广告
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label"><label>51la账户配置</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" name="fivela"  value="${boxUser.fivela}" size="66" placeholder="请输入51拉账户ID" data-validate="required:请输入请输入51拉账户ID"  />
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="label"><label>用户资格状态</label></div>
                        <div class="field">
                            <c:if test="${boxUser.userInType eq '1'}">
                                <input type="radio" value="1" name="userInType" checked/><span style="color:green">他拥有邀请权限</span>
                                <input type="radio" value="0" name="userInType"/>关闭他邀请功能
                            </c:if>
                            <c:if test="${boxUser.userInType ne '1'}">
                                <input type="radio" value="1" name="userInType" />允许他邀请
                                <input type="radio" value="0" name="userInType" checked /><span style="color:red">他没有邀请权限</span>
                            </c:if>
                            ps:该功能只影响下线悬浮位置是否展示该用户的广告（实时影响，关闭则下线全部展示系统广告）
                        </div>
                    </div>
                    <c:if test="${boxUser.userInType eq '1'}">
                    <div class="form-group">
                        <div class="label"><label>用户级别</label></div>
                        <div class="field">
                          <select name="leval" id="leval">
                              <c:if test="${boxUser.leval eq '1'}">
                              <option value="1">用户一级，控制悬浮【1】张广告</option><option value="2">用户二级，控制悬浮【2】张广告</option><option value="3">用户三级，控制悬浮【3】张广告</option>
                              </c:if>
                              <c:if test="${boxUser.leval eq '2'}">
                                  <option value="2">用户二级，控制悬浮【2】张广告</option><option value="1">用户一级，控制悬浮【1】张广告</option><option value="3">用户三级，控制悬浮【3】张广告</option>
                              </c:if>
                              <c:if test="${boxUser.leval eq '3'}">
                                  <option value="3">用户三级，控制悬浮【3】张广告</option><option value="1">用户一级，控制悬浮【1】张广告</option><option value="2">用户二级，控制悬浮【2】张广告</option>
                              </c:if>
                              <c:if test="${boxUser.leval eq '' || boxUser.leval eq null }">
                                  <option value="1">用户一级，控制悬浮【1】张广告</option><option value="2">用户二级，控制悬浮【2】张广告</option><option value="3">用户三级，控制悬浮【3】张广告</option>
                              </c:if>
                          </select>
                        </div>
                    </div>
                    </c:if>

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