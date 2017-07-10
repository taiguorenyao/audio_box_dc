<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>盒子会员注册</title>
    <link rel="stylesheet" href="/static/css/pintuer.css">
    <link rel="stylesheet" href="/static/css/admin.css">
    <script src="/static/js/jquery.js"></script>
    <script src="/static/js/pintuer.js"></script>
    <script src="/static/js/respond.js"></script>
    <script src="/static/js/admin.js"></script>
    <link type="image/x-icon" href="/static/images/box.ico" rel="shortcut icon" />
    <link href="/static/images/box.ico" rel="bookmark icon" />
</head>
<body style="background-image: url('/static/images/wap_bg.jpg');background-attachment: fixed;background-repeat: no-repeat;background-size: cover;">
<div class="container">
    <div class="line">
        <div class="xs6 xm4 xs3-move xm4-move">
            <br /><br />
            <div class="media media-y">
                <a href="http://www.pintuer.com" target="_blank"><img src="" class="radius" alt="后台管理系统" /></a>
            </div>
            <br /><br />
            <form action="/wap/userregdo" id="loginForm" method="post">
                <div class="panel">
                    <div class="panel-head"><strong>注册盒子系统</strong><span style="font-size:18px;color: red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${errorCode}</span></div>
                    <div class="panel-body" style="padding:30px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input" name="account" placeholder="登录账号" data-validate="required:请填写账号,length#>=5:账号长度不符合要求" />
                                <span class="icon icon-user"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input" name="pwd" placeholder="登录密码" data-validate="required:请填写密码,length#>=6:密码长度不符合要求" />
                                <span class="icon icon-key"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input" name="pwd1" placeholder="重复密码" data-validate="required:请重复填写密码,length#>=6:密码长度不符合要求" />
                                <span class="icon icon-key"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input" name="t_name" placeholder="真实姓名是账号的找回的凭据" data-validate="required:请填写真实姓名,是账号的找回的凭据" />
                                <span class="icon icon-key"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input" name="phone" placeholder="手机号是账号的找回的凭据" maxlength="11" data-validate="required:请填写手机号,是账号的找回的凭据,sj:请填写正确手机号" />
                                <span class="icon icon-key"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input" name="incode" placeholder="邀请码"  data-validate="required:请填写邀请码" />
                                <span class="icon icon-key"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input" name="qq" placeholder="QQ是账号的找回的凭据" data-validate="required:请填写QQ是账号的找回的凭据,number:请填写正确QQ号" />
                                <span class="icon icon-key"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="checkbox" name="biread" value="1" id="biread" data-validate="required:请阅读注册协议" />我已阅读注册协议
                                <a href="javascript:disxieyi();">查看协议</a>
                                <textarea cols="40"  id="ladaodi" rows="10" style="display:none" readonly="readonly">诚信会员注册协议
一、合作协议的确认和接纳:诚信推广服务联盟，所提供的合作项目的所有权和运作权归诚信推广服务联盟所有。您通过完成注册 程序并点击选择"同意以下服务条款，提交注册信息"，表示您与诚信推广服务联盟网站签订本合作协议（下称"本合作协议"）并接受 所有相关条款的约束，以及表示您成为诚信推广服务联盟的成员（"联盟成员"）。
二、 关于违规及作弊 加诚信推广服务联盟的成员，必须自觉遵守本合作协议，如发现任何违规或作弊行为，诚信推广服务联盟将停止服务，同时保留进一步追究法律责任的权利。违规或作弊行为包含但不限于：
1.发布、传送、传播、储存违反国家法律法规禁止的内容；
2.煽动民族仇恨、民族歧视，破坏民族团结的；
3.散布谣言，扰乱社会秩序，破坏社会稳定的；
4.散布淫秽、色情、赌博、暴力、恐怖或者教唆犯罪的；
5.发布、传送、传播、储存侵害他人名誉权、肖像权、知识产权、商业秘密等合法权利的内容；
6.传播骚扰及垃圾信息或含有任何性或性暗示的；
三、联盟成员的注册规则
1. 联盟成员的用户名的管理：
A、联盟成员的用户名的注册及使用须遵守中华人民共和国的有关法律法规、尊重网络道德；
B、联盟成员的用户名不得含有任何威胁、恐吓、漫骂、庸俗、亵渎、色情、淫秽、非法、攻击性、伤害性、骚扰性、诽谤性、 辱骂性的或其他侵害他人合法权益的信息；
C、联盟成员不得以任何方式盗用、冒充他人用户名；
D、如联盟成员违反上述规定，应自行承担相应法律后果；诚信推广服务联盟有权在必要时采取相应措施以维护相关合法权益。
2. 联盟成员的帐号、密码和安全性：您一旦注册成功并经诚信推广服务联盟审核通过，成为诚信推广服务联盟的合法成员，将得到一个密码 和用户名。联盟成员将对用户名和密码安全负全部责任，且联盟成员对以其用户名在诚信推广服务联盟进行的所有行为承担全部责任 。联盟成员必须保管好自己的用户名和密码，谨防被盗或泄露；如因保管不善导致帐号和密码被盗或泄露，联盟成员自行承担相 应后果。如果联盟成员在发现有任何非法使用联盟成员帐号或安全漏洞的情况，可以立即通告诚信推广服务联盟以协助处理。
3. 提供详尽、准确的个人资料，并不断更新注册资料，符合及时、详尽、准确的要求。真实的联盟成员资料将作为诚信推广服务联盟提 供服务的依据和联盟成员获得法律保障的前提。因虚假资料造成的任何损失，与诚信推广服务联盟无关，诚信推广服务联盟不负任何责任。
四、 隐私保护政策 每当联盟成员提供给诚信推广服务联盟个人信息时，诚信推广服务联盟会采取合理的步骤保护联盟成员的个人信息 ，诚信推广服务联盟也会采取合理的安全手段保护已存储的个人信息。由于网上技术发展的突飞猛进，诚信推广服务联盟会随时更新并公 布诚信推广服务联盟的信息保密制度，除非以下三种情况外，诚信推广服务联盟不会在未经合法联盟成员授权时，公开、编辑或透露联盟 成员资料及其它保存在诚信推广服务联盟中的保密性内容：
1) 根据有关法律规定要求；
2) 有关权力机关要求；
3) 联盟成员授权（包括，如果联盟成员要求诚信推广服务联盟提供特定服务时，诚信推广服务联盟则需要把联盟成员的姓名和地址提供给与 此相关联的第三方如邮政服务单位）。
五、 暂停与终止合作 诚信推广服务联盟将根据联盟成员合作效果保留暂停或终止合作的权利。 任何经诚信推广服务联盟确认已违反了合作协议的联盟成员，诚信推广服务联盟有权决定是否给予其暂停或终止合作的处理，对屡犯者将 立即给予暂停合作或终止合作的处理。 诚信推广服务联盟保留其单方暂停或终止本合作协议的权利。但若诚信推广服务联盟单方决定终止的，应提前一个月通知。通知做出的形 式依据本合作协议第七条的规定。
六、 风险分担 联盟成员使用本网站将承担一定风险：诚信推广服务联盟将不承担由于联盟成员自身过错、技术或其它不可控 原因导致网站页面信息或其它方面的错误而给联盟成员造成的损失。
七、 通知 所有发给联盟成员的通知都将通过网站页面的公告或电子邮件传送。协议条款的修改、服务变更、或其它 重要事件的通知都会以此形式进行。
八、 协议条款 诚信推广服务联盟有权在必要时修改本合作协议条款的内容，且该修改以符合国家法律法规的 规定，并不侵害联盟成员的合法权益为必要前提。诚信推广服务联盟合作协议条款一旦发生变动，诚信推广服务联盟将在网页上公布修改 内容。修改后的合作协议一旦在网页上公布即有效代替原来的合作协议。您可随时造访查阅最新版合作协议。联盟 成员若不同意合作协议改动的内容，可以主动退出诚信推广服务联盟；若诚信推广服务联盟没有收到联盟成员书面通知，明确表示其退出 诚信推广服务联盟的，则视为该联盟成员选择继续享用诚信推广服务联盟服务，已接受该协议条款的变动并受变动后协议条款的约束。
九、 法律适用 本合作协议条款应符合中华人民共和国法律法规的规定，联盟成员和诚信推广服务联盟一致同意接受中华人民 共和国法律的管辖。当本合作协议条款与中华人民共和国法律相抵触时，则这些条款将完全按法律规定重新解释或重新修订，而 其它条款则依旧对诚信推广服务联盟及联盟成员产生法律效力。
十、 争议解决 与本合作协议有关的一切争议，双方应通过协商方式友好解决；如协商未果，应将争议提交北京仲裁委员 会进行仲裁，该仲裁结果是终局的并对双方均有约束力。 警告： 对任何违反国家法律和诚信推广服务联盟网站相应管理规定且侵害了诚信推广服务联盟网站合法权益的行为，诚信推广服务联盟将保留追究其 法律责任的权利。</textarea>
                                <span class="icon icon-key"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field">
                                <input type="text" class="input" name="code" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
                                <img alt="验证码" title="点击换一张" id="imgCode" onclick="getCode();" src="/security/cuapCode?code=<%=Math.random()%>"  width="80" height="32" class="passcode" />
                            </div>
                        </div>
                    </div>
                    <div class="panel-foot text-center"><button class="button button-block bg-main text-big">注册</button></div>
                </div>
            </form>

        </div>
    </div>
</div>
<%
    request.getSession().removeAttribute("errorCode");
%>
</body>
</html>
<script type="text/javascript">
    function disxieyi()
    {
        if (document.getElementById('ladaodi').style.display == "none")
        {
            document.getElementById('ladaodi').style.display="block";
        }
        else
        {
            document.getElementById('ladaodi').style.display="none";
        }
    }
    function checkCk(){
        var clientHeight = document.getElementById('ladaodi').clientHeight;
        var scrollTop    = document.getElementById('ladaodi').scrollTop;
        var scrollHeight = document.getElementById('ladaodi').scrollHeight;

        if(clientHeight + scrollTop < scrollHeight){
            alert("Please view top news terms !"); return false;
        }else{
            alert("Thanks !");
        }
    }

    function getCode() {
        document.getElementById("imgCode").src = "/security/cuapCode?code="
                + Math.random();
    }
</script>