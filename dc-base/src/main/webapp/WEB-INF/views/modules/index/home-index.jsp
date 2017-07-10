<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../include/zmos-head.jsp"/>
<jsp:include page="../../include/zmos-menu.jsp"/>
<script type="text/javascript" src="/static/face/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/face/js/jquery.qqFace.js"></script>
<script type="text/javascript">
    $('.portal-all_menu li').each(function(){
        document.getElementById(this.id).className="";
        document.getElementById("menu-ad").className="active";
            });

        $(function(){
            for (var i=1;i<=15;i++)
            {
            $('.emotion'+i).qqFace({
                assign:'t_t'+i, //给输入框赋值
                path:'static/face/arclist/' //表情图片存放的路径
            });
            }
        });

</script>
<style>
    .comment{width:680px; margin:20px auto; position:relative}
    .comment h3{height:28px; line-height:28px}
    .com_form{width:100%; position:relative}
    .input{width:99%; height:60px; border:1px solid #ccc}
    .com_form p{height:28px; line-height:28px; position:relative}
    span.emotion{width:42px; height:20px; background:url(icon.gif) no-repeat 2px 2px;
        padding-left:20px; cursor:pointer}
    span.emotion:hover{background-position:2px -28px}
    .qqFace{margin-top:4px;background:#fff;padding:2px;border:1px #dfe6f6 solid;}
    .qqFace table td{padding:0px;}
    .qqFace table td img{cursor:pointer;border:1px #fff solid;}
    .qqFace table td img:hover{border:1px #0066cc solid;}
    #show{width:680px; margin:20px auto}
</style>
<div class="admin">
    <div class="line-big">
        <div class="xm12">
           
            <div class="panel">
                <div class="panel-head"><strong>广告配置页面</strong>
                    <a href="/my/refreshAdVip?uid=${PORTAL_USER.id}">
                        <input type="button"
                               class="button button-small bg-blue margin-big-left"
                               value="刷新缓存"/>

                        <a href="/my/sinaUrl?uid=${PORTAL_USER.id}">
                            <input type="button"
                                   class="button button-small bg-blue margin-big-left"
                                   value="生成短地址"/>
                        </a>

                        <a href="javascript:saveDate();">
                            <input type="button"
                                   class="button button-small bg-blue margin-big-left"
                                   value="批量保存广告"/>
                        </a>
                    </a>
                    <strong style="color:red">${errorCode}</strong>
                    <c:if test="${!empty sort_url}">
                        <textarea cols="50" id="biao1" rows="5">${sort_url}</textarea>
                        <input type="button" onClick="copyUrl()" value="点击复制" />
                    </c:if>
                </div>
                <form method="post" id="subfrom" name="subfrom" class="form-x" action="/my/saveVipAds">
                <table class="table table-hover">
                    <tr>
                        <th width="80"><span class="padding-big-left">序号</span></th>
                        <th>广告标题</th>
                        <th>广告地址</th>
                        <th>字体颜色|大小|背景颜色</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${vipadlist}" var="vip" varStatus="index">
                    <tr>
                        <td><span class="badge bg-blue margin-big-left">${index.index+1}</span><input name="adid" id="t_id${index.index+1}" type="hidden" value="${vip.id}"/></td>
                        <td>
                            <input name="adname" id="t_t${index.index+1}" type="text" value="${vip.adtitle}"/>
                            <span class="emotion${index.index+1}"><img src='/static/face/arclist/1.gif' /></span>
                        </td>
                        <td><input name="adurl" id="t_url${index.index+1}" type="text" value="${vip.adurl}"/></td>
                        <td><input name="adcolor" type="color" id="t_font${index.index+1}" value="#${vip.fontColor}"  oninput="changeBackground()">
                        <select name="adsize" id="adsize${index.index+1}">
                         <c:if test="${!empty vip.size}">
                                <option value="${vip.size}">${vip.size}px</option>
                         </c:if>
                         <option value="16">16px</option><option value="17">17px</option>
                         <option value="18">18px</option><option value="19">19px</option>
                         <option value="20">20px</option><option value="21">21px</option>
                            <option value="22">22px</option><option value="23">23px</option>
                            <option value="24">24px</option><option value="25">25px</option>
                            <option value="26">26px</option><option value="27">27px</option>
                            <option value="28">16px</option><option value="29">29px</option>
                            <option value="30">30px</option>
                        </select>
                            <input name="bgColor" type="color" id="t_bgcolor${index.index+1}" value="#${vip.bgColor}"  oninput="changeBackground_1()">
                        </td>
                        <th><a href="javascript:saveVipAd(${index.index+1})">保存</a></th>
                    </tr>
                    </c:forEach>
                </table>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function saveDate()
    {
        subfrom.submit();
    }

    function copyUrl()
    {
        var Url2=document.getElementById("biao1");
        Url2.select();
        document.execCommand("Copy");
        alert("已复制好，可贴粘。");
    }

    function saveVipAd(i)
    {
        var t_t = $("#t_t"+i).val();
        var t_url = $("#t_url"+i).val();
        var t_font = $("#t_font"+i).val();
        var t_id = $("#t_id"+i).val();
        var size = $("#adsize"+i).val();
        var bgcolor = $("#t_bgcolor"+i).val();
        $.ajax({
            url: 'my/saveVipAd',
            type: 'post',
            data: "t_id="+t_id+"&t_t="+t_t+"&t_url="+t_url+"&t_font="+t_font+"&adsize="+size+"&bgcolor="+bgcolor,
            dataType: 'json',
            success: function (data) {
                if (data.code == 0) {
                    alert('成功');
                    window.location.reload();
                    return;
                } else {
                    alert('失败');
                    window.location.reload();
                    return;
                }
            },
            error: function () {

            }
        });
    }

    function changeBackground(colorValue){document.body.style.bakcgroundColor = colorValue;}
    function changeBackground_1(colorValue){document.body.style.bakcgroundColor = colorValue;}
</script>
<%
    request.getSession().removeAttribute("errorCode");
    request.getSession().removeAttribute("sort_url");
%>