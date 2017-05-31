<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<html>
<head>
    <title>找回密码</title>
</head>
<body class="gray-bg">

<div class="middle-box text-center loginscreen   animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">ASS+</h1>

        </div>
        <h3>找回密码</h3>
        <c:if test="${not empty message}">
            <div class="alert alert-danger display-hide">
                <button class="close" data-close="alert"></button>
                <span id="msg">${message}
                </span>
            </div>
        </c:if>
        <c:if test="${nexttype=='4' or empty nexttype}">
            <form id="selectUser" class="m-t-xl" role="form" action="password?type=1" method="post">
                <h4 >请在下列表格中输入您的svn用户名，系统会根据用户名将密码重置链接发送到您的邮箱中<br>请注意查收邮件。</h4>
                <div class="form-group">
                    <input type="text" name="yhm" class="form-control" placeholder="用户名" required="" value="${usermail.yhm}">
                </div>

                <button type="submit" class="btn btn-primary block full-width m-b">下一步</button>
                <p class="text-muted text-center"><small>已经有账户?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="login">登陆</a>
            </form>

        </c:if>
        <c:if test="${nexttype=='3'}">
            <form id="postMail" class="m-t-xl" role="form" action="password?type=4" method="post">
                <div id="sendMailText" class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span id="msgText">
                    </span>
                </div>
                <div class="form-group">
                    <input type="text" id="fsyhm" name="fsyhm" class="form-control" placeholder="用户名" readonly="readonly" required="" value="${usermail.fsyhm}">
                </div>
                <div class="form-group">
                    <input type="email" name="yx" class="form-control" placeholder="邮箱" readonly="readonly" required="" value="${usermail.yx}">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">上一步</button>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="postMail();">发送邮件</button>
                <p class="text-muted text-center"><small>已经有账户?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="login">登陆</a>
            </form>
        </c:if>
        <p class="m-t"> <small>倪明奇 &copy; 2017</small> </p>
    </div>
</div>

<!-- Mainly scripts -->

<script>
    $(function () {
        $("#sendMailText").hide();
        $('#selectUser').bootstrapValidator({
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
                }
            }
        });
    });


</script>
<script type="application/javascript">
    function postMail(){
        $.ajax({
            type: "GET",
            url: "user/postMail?yhm="+$("#fsyhm").val(),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (message) {

                $("#sendMailText").removeClass("alert-danger");
                $("#sendMailText").addClass("alert-success");
                $("#msgText").text("发送成功！");
                $("#sendMailText").show();

            },
            error: function (message) {
                $("#sendMailText").removeClass("alert-success");
                $("#sendMailText").addClass("alert-danger");
                $("#sendMailText").show();
                $("#msgText").text(dealRestServiceError(message.responseText))
            }
        });
    }

</script>
</body>
</html>
