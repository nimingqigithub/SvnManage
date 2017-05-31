<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<script>
    var ctx_js = "${ctx}";
    var ctxStatic_js = "${ctxStatic}"
</script>
<link href="${ctx}/htmlsource/css/bootstrap.min.css" rel="stylesheet">
<script src="${ctx}/htmlsource/js/jquery-2.1.1.js"></script>
<script src="${ctx}/htmlsource/js/bootstrap.min.js"></script>
<script src="${ctx}/htmlsource/js/bootstrapValidator.min.js"></script>

<link href="${ctx}/htmlsource/font-awesome/css/font-awesome.css" rel="stylesheet">

<link href="${ctx}/htmlsource/css/animate.css" rel="stylesheet">
<link href="${ctx}/htmlsource/css/style.css" rel="stylesheet">
<link href="${ctx}/htmlsource/css/bootstrapValidator.min.css" rel="stylesheet">

<script src="${ctx}/htmlsource/js/plugins/iCheck/icheck.min.js"></script>
<link href="${ctx}/htmlsource/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx}/htmlsource/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<script src="${ctx}/htmlsource/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctx}/htmlsource/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${ctx}/htmlsource/js/plugins/flot/jquery.flot.js"></script>
<script src="${ctx}/htmlsource/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="${ctx}/htmlsource/js/plugins/flot/jquery.flot.spline.js"></script>
<script src="${ctx}/htmlsource/js/plugins/flot/jquery.flot.resize.js"></script>
<script src="${ctx}/htmlsource/js/plugins/flot/jquery.flot.pie.js"></script>
<script src="${ctx}/htmlsource/js/plugins/peity/jquery.peity.min.js"></script>
<script src="${ctx}/htmlsource/js/demo/peity-demo.js"></script>
<script src="${ctx}/htmlsource/js/inspinia.js"></script>
<script src="${ctx}/htmlsource/js/plugins/pace/pace.min.js"></script>
<script src="${ctx}/htmlsource/js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="${ctx}/htmlsource/js/plugins/gritter/jquery.gritter.min.js"></script>
<script src="${ctx}/htmlsource/js/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="${ctx}/htmlsource/js/demo/sparkline-demo.js"></script>
<script src="${ctx}/htmlsource/js/plugins/chartJs/Chart.min.js"></script>
<script src="${ctx}/htmlsource/js/plugins/toastr/toastr.min.js"></script>


<script type="application/javascript">
    toastr.options = {
        "closeButton": false, //是否显示关闭按钮
        "debug": false, //是否使用debug模式
        "positionClass": "toast-bottom-right",//弹出窗的位置
        "showDuration": "300",//显示的动画时间
        "hideDuration": "1000",//消失的动画时间
        "timeOut": "5000", //展现时间
        "extendedTimeOut": "1000",//加长展示时间
        "showEasing": "swing",//显示时的动画缓冲方式
        "hideEasing": "linear",//消失时的动画缓冲方式
        "showMethod": "fadeIn",//显示时的动画方式
        "hideMethod": "fadeOut" //消失时的动画方式
    };





    function dealRestServiceError(text){
        if (text.match("^\{(.+:.+,*){1,}\}$")) {
            var resultJson = $.parseJSON(text);
            return  resultJson.exception;
        }
        else{
            return text;
        }
    }
</script>