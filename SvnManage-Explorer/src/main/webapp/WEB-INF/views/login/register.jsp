<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<html>
<head>
    <title>用户注册</title>
</head>
<body class="gray-bg">

<div class="middle-box text-center loginscreen   animated fadeInDown">
    <div>
        <h3>注册用户</h3>
        <c:if test="${not empty message}">
            <div class="alert alert-danger display-hide">
                <button class="close" data-close="alert"></button>
                <span id="msg">${message}
                </span>
            </div>
        </c:if>
        <c:if test="${not empty messagesuccess}">
            <script type="application/javascript">c
                toastr.info(${messagesuccess});
            </script>
        </c:if>
        <form id="registerForm" class="m-t" role="form" action="login?type=2" method="post">
            <div class="form-group">
                <input type="text" name="yhm" class="form-control" placeholder="用户名" required="" oninput="${"#successMsg"}.hide();" value="${register.yhm}">
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
                <input type="text" name="dh" class="form-control" placeholder="电话" required="" value="${register.dh}">
            </div>
            <div class="form-group">
                <input type="text" name="bz" class="form-control" placeholder="备注" value="${register.bz}">
            </div>
            <div class="form-group">
                <div class="checkbox i-checks"><label> <input type="checkbox" name="tytk"><i></i> 同意注册条款 </label></div>
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">注册</button>
<%--
            <p class="text-muted text-center"><small>已经有账户?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="login">登陆</a>--%>
        </form>
        <p class="m-t"> <small>倪明奇 &copy; 2017</small> </p>
    </div>
</div>

<!-- Mainly scripts -->

<script>
    $(document).ready(function(){
      /*  $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });*/
    });

    $(function () {
        $('#registerForm').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                yhm: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                mm: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },identical: {
                            field: 'qrmm',
                            message: '两次输入密码不一致'
                        }
                    }
                },
                qrmm: {
                    validators: {
                        notEmpty: {
                            message: '确认密码不能为空'
                        },identical: {
                            field: 'mm',
                            message: '两次输入密码不一致'
                        }
                    }

                },
                xm: {
                    validators: {
                        notEmpty: {
                            message: '姓名不能为空'
                        }
                    }
                },
                yx: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        emailAddress: {
                            message: '邮箱格式不正确'
                        }
                    }
                },
                dh: {
                    validators: {
                        notEmpty: {
                            message: '电话不能为空'
                        },
                        regexp: {
                            regexp: /^1[3|5|7|8]{1}[0-9]{9}$/,
                            message: '请输入正确的手机号码'
                        }
                    }

                }
                ,
                tytk: {
                    validators: {
                        notEmpty: {
                            message: '请先同意条款'
                        }
                    }
                }

            }
        });
    });


</script>
</body>
</html>
