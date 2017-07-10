<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="Paging" uri="/WEB-INF/tld/Paging.tld" %>
<jsp:include page="../include/zmos-head.jsp"/>
<jsp:include page="../include/zmos-menu.jsp"/>
<script type="text/javascript">
    $('.portal-all_menu li').each(function(){
        document.getElementById(this.id).className="";
        document.getElementById("menu-audio").className="active";
    });
</script>
        <div class="admin">
    <div class="tab">
        <div class="tab-head">
            <ul class="tab-nav">
                <li class="active"><a href="#tab-set">视频添加</a></li>
                <li class=""><a href="#tab-set">严禁使用本平台传播违反法律的淫秽、色情片源，违规视频，本平台有权停止站点服务，并配合相关部门调查！</a></li>
                <span style="color: red;font-size:20px">${errorCode}</span>
            </ul>
        </div>
        <div class="tab-body">
            <br />
            <div class="tab-panel active" id="tab-set">
                <form method="post" class="form-x" action="/my/addAudiodo">
                    <div class="form-group">
                        <div class="label"><label>视频标题</label></div>
                        <div class="field">
                            <input type="text" class="input input-auto" id="title" name="title" size="20" placeholder="请输入视频标题" data-validate="required:请输入视频标题" value="视频播放" />
                            <input type="radio" id="types1" name="types" value="0" checked/>MP4|
                            <input type="radio" id="types2" name="types" value="1"/>ifarme
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="label"><label>视频地址</label></div>
                        <div class="field">
                            <b style="color:red">输入视频多个地址用【@】隔开如下图</b><br/>
                            <b >https://v.qq.com/iframe/player.html?vidh&tiny.mp4@</b><br/>
                            <b >https://v.qq.com/iframe/player.html?vidh&tiny.mp4@</b><br/>
                            <b >https://v.qq.com/iframe/player.html?vidh&tiny.mp4@</b><br/>
                            <textarea class="form-control" rows="10" cols="70" id="url" name="url" placeholder=""  data-validate="required:请输入视频地址"></textarea>
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