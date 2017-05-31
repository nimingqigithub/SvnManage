<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/19
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>Bootstrap 实例 - 模态框（Modal）插件</title>
</head>
<body>

<h2>创建模态框（Modal）</h2>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" data-toggle="modal"
        data-target="#myModal">
    开始演示模态框
</button>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
                </h4>
            </div>
            <div class="modal-body">
                123123123
                <form id="registerForm" class="m-t" role="form" action="login?type=2" method="post">
                    <div class="form-group">
                        <input type="text" name="yhm" class="form-control" placeholder="用户名" required="" value="${register.yhm}">
                    </div>
                    <div class="form-group">
                        <input type="password" name="mm" class="form-control" placeholder="密码" required="" value="${register.mm}">
                    </div>
                    <div class="form-group">
                        <input type="password" name="qrmm" class="form-control" placeholder="确认密码" required="" value="${register.mm}">
                    </div>
                    <div class="form-group">
                        <input type="text" name="xm" class="form-control" placeholder="姓名" required="" value="${register.xm}">
                    </div>
                    <div class="form-group">
                        <input type="email" name="yx" class="form-control" placeholder="邮箱" required="" value="${register.yx}">
                    </div>
                    <div class="form-group">
                        <input type="text" name="bz" class="form-control" placeholder="备注" value="${register.bz}">
                    </div>
                    <div class="form-group">
                        <div class="checkbox i-checks"><label> <input type="checkbox" name="tytk"><i></i> 同意注册条款 </label></div>
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">注册</button>

                    <p class="text-muted text-center"><small>已经有账户?</small></p>
                    <a class="btn btn-sm btn-white btn-block" href="login">登陆</a>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->

</body>
</html>
