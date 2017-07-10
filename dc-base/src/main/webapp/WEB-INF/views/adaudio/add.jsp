<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../include/zmos-head.jsp"/>
<jsp:include page="../include/zmos-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function(){
        document.getElementById(this.id).className="";
        document.getElementById("menu-person-ad").className="active";
    });
</script>
<div class="admin">
    <div class="tab">
        <div class="tab-head">
            <ul class="tab-nav">
                <li class="active"><a href="#tab-set">视频广告添加</a></li>
                <span style="color: red;font-size:20px">${errorCode}</span>
            </ul>
        </div>
        <div class="tab-body">
            <br />
            <div class="tab-panel active" id="tab-set">
                <form method="post" class="form-x" action="/my/addpersonaddo">
                    <div class="form-group">
                        <div class="label"><label>顶部文字广告标题</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" id="txt_title" name="txt_title" value="${personAdEvt.txt_title}" size="66" placeholder="请输入文本标题" data-validate="required:请输入文本标题" value="" />
                            <input type="color" id="fontcolor" name="fontcolor" value="#${personAdEvt.fontcolor}" id="bgcolor" oninput="changeBackground();">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label"><label>顶部文字跳转地址</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" id="txt_url" name="txt_url" value="${personAdEvt.txt_url}" size="66" placeholder="请输入文本地址" data-validate="required:请输入文本地址" value="" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label"><label>底部二维码图片地址</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" id="img_url" name="img_url" value="${personAdEvt.img_url}" size="66" placeholder="请输入图片地址" data-validate="required:请输入图片地址" value="" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="label"><label>底部二维码跳转地址</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" id="img_access_url" name="img_access_url" value="${personAdEvt.img_access_url}" size="66" placeholder="请输入文本地址" data-validate="required:请输入图片广告地址" value="" />
                        </div>
                    </div>

                    <c:if test="${PORTAL_USER.userInType eq '1'}">

                        <c:if test="${PORTAL_USER.leval >= 1}">
                            <div class="form-group">
                                <div class="label"><label>底部悬浮广告位一图片地址</label></div>
                                <div class="field">
                                    <input type="text" class="input input-auto" id="xf_img1" name="xf_img1" value="${personAdEvt.xf_img1}" size="66" placeholder="请输入图片地址" data-validate="required:请输入图片地址" value="" />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="label"><label>跳转地址</label></div>
                                <div class="field">
                                    <input type="text" class="input input-auto" id="xf_url1" name="xf_url1" value="${personAdEvt.xf_url1}" size="66" placeholder="请输入访问地址" data-validate="required:请输入图片广告地址" value="" />
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${PORTAL_USER.leval >=2}">
                            <div class="form-group">
                                <div class="label"><label>底部悬浮广告位二图片地址</label></div>
                                <div class="field">
                                    <input type="text" class="input input-auto" id="xf_img2" name="xf_img2" value="${personAdEvt.xf_img2}" size="66" placeholder="请输入图片地址" data-validate="required:请输入图片地址" value="" />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="label"><label>跳转地址</label></div>
                                <div class="field">
                                    <input type="text" class="input input-auto" id="xf_url2" name="xf_url2" value="${personAdEvt.xf_url2}" size="66" placeholder="请输入访问地址" data-validate="required:请输入图片广告地址" value="" />
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${PORTAL_USER.leval >= 3}">
                            <div class="form-group">
                                <div class="label"><label>底部悬浮广告位三图片地址</label></div>
                                <div class="field">
                                    <input type="text" class="input input-auto" id="xf_img3" name="xf_img3" value="${personAdEvt.xf_img3}" size="66" placeholder="请输入图片地址" data-validate="required:请输入图片地址" value="" />
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="label"><label>跳转地址</label></div>
                                <div class="field">
                                    <input type="text" class="input input-auto" id="xf_url3" name="xf_url3" value="${personAdEvt.xf_url3}" size="66" placeholder="请输入访问地址" data-validate="required:请输入图片广告地址" value="" />
                                </div>
                            </div>
                        </c:if>


                    </c:if>

                    <input type="hidden" name="nodata" value="${nodata}"/>
                    <div class="form-button">
                        <button class="button bg-main" type="submit">保存</button>
                    </div>




                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/java">
    function changeBackground(colorValue){document.body.style.bakcgroundColor = colorValue;}
</script>
<%
    request.getSession().removeAttribute("errorCode");
%>