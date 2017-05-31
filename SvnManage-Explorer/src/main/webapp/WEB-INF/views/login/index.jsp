<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include/taglib.jsp"%>
<html>
<head>
    <title>svn管理系统</title>
</head>
<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="${ctx}/htmlsource/img/profile_small.jpg" />
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">David Williams</strong>
                             </span> <span class="text-muted text-xs block">Art Director <b class="caret"></b></span> </span> </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="profile.html">Profile</a></li>
                            <li><a href="contacts.html">Contacts</a></li>
                            <li><a href="mailbox.html">Mailbox</a></li>
                            <li class="divider"></li>
                            <li><a href="login?type=1">注销</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">
                        IN+
                    </div>
                </li>
                <li class="active">
                    <a href="#"><i class="fa fa-th-large"></i> <span class="nav-label">用户管理</span> <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li class="active"><a href="javascript:setIframe('register')">用户注册</a></li>
                        <li><a href="javascript:setIframe('userManage')">用户信息管理</a></li>
                        <li><a href="dashboard_3.html">密码修改</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>

    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div id="rowTitle" class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search">
                        </div>
                    </form>
                </div>


                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <span class="m-r-sm text-muted welcome-message">欢迎您， ${staffname}</span>
                    </li>
                    <li class="dropdown">

                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li>
                                <a data-target="#myModal" role="button" class="btn" data-toggle="modal"><i class="fa fa-gear fa-fw"></i> 修改密码</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="login?type=1" target='_parent'><i class="fa fa-sign-out fa-fw"></i> 注销</a>
                            </li>
                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                </ul>

            </nav>
        </div>
        <div  class="row">
            <div class="col-lg-12">
                <div class="wrapper wrapper-content">
                    <iframe id="iframeText" style="border:none;overflow:hidden"  src="" width="100%" height="500"></iframe>
                </div>
                <div id="rowFoot" class="footer">
                    <div class="pull-right">
                        10GB of <strong>250GB</strong> Free.
                    </div>
                    <div>
                        <strong>Copyright</strong> 倪明奇 &copy; 2017-永久
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    密码修改
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <input type="text" id="yhm" name="yhm" class="form-control" placeholder="用户名" required="" disabled="disabled" value="${username}">
                </div>
                <div class="form-group">
                    <input type="text" id="xm" name="xm" class="form-control" placeholder="姓名" required="" disabled="disabled" value="${staffname}">
                </div>
                <div class="form-group">
                    <input type="password" id="ymm" name="ymm" class="form-control" placeholder="原密码" required="" value="">
                </div>
                <div class="form-group">
                    <input type="password" id="mm" name="mm" class="form-control" placeholder="新密码" required="" value="">
                </div>
                <div class="form-group">
                    <input type="password" id="qrmm" name="qrmm" class="form-control" placeholder="确认新密码" required="" value="">
                </div>
                <div id="alertMsg" class="alert alert-danger display-hide">
                    <button class="close" data-close="alert"></button>
                    <span id="msg">
                    </span>
                </div>
            </div>
            <div class="modal-footer">
                <button id="close" type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="modifyPassword();">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script type="application/javascript">
    $(function () {
        $("#alertMsg").hide();
        $('.modal-body').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                ymm: {
                    validators: {
                        notEmpty: {
                            message: '原密码不能为空'
                        }
                    }
                },
                mm: {
                    validators: {
                        notEmpty: {
                            message: '新密码不能为空'
                        },identical: {
                            field: 'mm',
                            message: '两次输入密码不一致'
                        },different: {
                            field: 'ymm',
                            message: '新密码不能和原密码一样'
                        }
                    }
                },
                qrmm: {
                    validators: {
                        notEmpty: {
                            message: '确认新密码不能为空'
                        },identical: {
                            field: 'mm',
                            message: '两次输入密码不一致'
                        },different: {
                            field: 'ymm',
                            message: '新密码不能和原密码一样'
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
        }).on('success.form.bv',function(e){
            modifyPasswordBase();
        });
    });
</script>
<script type="application/javascript">
    var modifyPassword = function(){
       $('.modal-body').bootstrapValidator('validate')
    }
    var modifyPasswordBase=function(){
        $("#alertMsg").hide();
        var jsonData={};
        jsonData["yhm"] = $("#yhm").val();
        jsonData["mm"] =$("#mm").val()
        jsonData["ymm"] = $("#ymm").val();
        $.ajax({
            type: "POST",
            url: "user/modifyPassword",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function (message) {
                $("#myModal").modal("hide");
            },
            error: function (message) {
                $("#msg").text(dealRestServiceError(message.responseText));
                $("#alertMsg").show();
            }
        });
    }
</script>

<script type="application/javascript">
    $(function(){
        $("#iframeText").height(document.body.clientHeight  - $("#rowTitle").height() - $("#rowFoot").height()-50);
    })
    function setIframe(url){
        $("#iframeText").attr("src",url);
    }
</script>
</body>
</html>
