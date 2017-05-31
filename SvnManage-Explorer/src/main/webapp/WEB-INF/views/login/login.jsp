<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<%
    Cookie[] cookies = request.getCookies();
    String cookieName = "";
    if(cookies!=null) {

        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (c.getName().equalsIgnoreCase("username")) {
                cookieName = c.getValue();
                break;
            }
        }
    }

%>
<html>
<head>
    <title>svn管理系统</title>
</head>
<body class="gray-bg">

<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <div>
            <h1 class="logo-name">ASS+</h1>
        </div>
        <h3></h3>
        <p>svn管理系统登陆
        </p>
        <c:if test="${not empty message}">
            <div class="alert alert-danger display-hide">
                <button class="close" data-close="alert"></button>
                <span id="msg">${message}
                </span>
            </div>
        </c:if>



        <form id="loginform" class="m-t" role="form" modelAttribute="user" action="${ctx}/login" method="post">
            <div class="form-group">
                <c:if test="${not empty user.yhm}">
                    <input type="text" name="yhm" class="form-control" placeholder="用户名" required="" value="${user.yhm}">
                </c:if>
                <c:if test="${empty user.yhm}">
                    <input type="text" name="yhm" class="form-control" placeholder="用户名" required="" value="<%=cookieName%>">
                </c:if>

            </div>
            <div class="form-group">
                <input type="password" name="mm"  class="form-control" placeholder="密码" required="" value="${user.mm}">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登陆</button>

        </form>

        <a href="password"><small>忘记密码</small></a>
       <%-- <p class="text-muted text-center"><small>没用账户?</small></p>--%>
    <%--    <a class="btn btn-sm btn-white btn-block" href="register">创建账户</a>--%>
        <p class="m-t"> <small>倪明奇 &copy; 2017</small> </p>
    </div>
</div>
</body>
<script type="application/javascript">
    $(function () {
        return;
        $('#loginform').bootstrapValidator({
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
                        }
                    }
                }
            }
        });
    });

</script>
</html>
