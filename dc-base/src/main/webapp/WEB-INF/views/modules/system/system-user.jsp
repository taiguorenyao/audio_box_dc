<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:include page="sub/system-head.jsp"/>
<div class="admin">
    <form method="post" action="">
        <div class="panel admin-panel">
            <div class="panel-head"><strong>用户管理</strong></div>
            <div class="padding border-bottom">
                <a href="add-user.html" class="button button-small bg-yellow float-right" value="" >添加用户</a>
                <label>部门</label>
                <select class="margin-small-left button button-small">
                    <option>销售1部</option>
                    <option>销售2部</option>
                    <option>广州分部</option>
                    <option>深圳分部</option>
                </select>
                <label class="margin-big-left">姓名</label>
                <input type="text" class="button button-small" />

                <input type="button" class="button button-small bg-blue margin-big-left" value="搜索" />

            </div>
            <table class="table table-hover">
                <tr>
                    <th width="100">用户名</th>
                    <th width="">姓名</th>
                    <th width="">部门</th>
                    <th width="">联系电话</th>
                    <th width="">用户角色</th>
                    <th width="">上次登录时间</th>
                    <th width="">上次登录IP</th>
                    <th width="180">操作</th>
                </tr>
                <tr>
                    <td>admin</td>
                    <td>Admin</td>
                    <td>销售1部</td>
                    <td>13513241234</td>
                    <td>销售总监</td>
                    <td>2014-10-1</td>
                    <td>192.168.1.1</td>
                    <td>
                        <a class="button border-blue button-little" href="edit-user.html">修改</a>
                        <a class="button border-green button-little" href="#" onClick="{if(alert('密码已重置，新密码：123456'))return false;}">重置密码</a>
                        <a class="button border-yellow button-little" href="#" onClick="{if(confirm('确认删除?')){return true;}return false;}">离职</a>
                    </td>
                </tr>
                <tr>
                    <td>admin</td>
                    <td>Admin</td>
                    <td>销售1部</td>
                    <td>13513241234</td>
                    <td>销售总监</td>
                    <td>2014-10-1</td>
                    <td>192.168.1.1</td>
                    <td>
                        <a class="button border-blue button-little" href="edit-user.html">修改</a>
                        <a class="button border-green button-little" href="#" onClick="{if(alert('密码已重置，新密码：123456'))return false;}">重置密码</a>
                        <a class="button border-yellow button-little" href="#" onClick="{if(confirm('确认删除?')){return true;}return false;}">离职</a>
                    </td>
                </tr>
                <tr>
                    <td>admin</td>
                    <td>Admin</td>
                    <td>销售1部</td>
                    <td>13513241234</td>
                    <td>销售总监</td>
                    <td>2014-10-1</td>
                    <td>192.168.1.1</td>
                    <td>
                        <a class="button border-blue button-little" href="edit-user.html">修改</a>
                        <a class="button border-green button-little" href="#" onClick="{if(alert('密码已重置，新密码：123456'))return false;}">重置密码</a>
                        <a class="button border-yellow button-little" href="#" onClick="{if(confirm('确认删除?')){return true;}return false;}">离职</a>
                    </td>
                </tr>
            </table>
            <div class="panel-foot text-center">
                <ul class="pagination"><li><a href="#">上一页</a></li></ul>
                <ul class="pagination pagination-group">
                    <li><a href="#">1</a></li>
                    <li class="active"><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                </ul>
                <ul class="pagination"><li><a href="#">下一页</a></li></ul>
            </div>
        </div>
    </form>
</div>