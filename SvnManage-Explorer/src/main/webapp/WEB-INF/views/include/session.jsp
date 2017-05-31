
<%
    Object username = request.getSession().getAttribute("username");
    Object staffname = request.getSession().getAttribute("staffname");
    Object logintime = request.getSession().getAttribute("logintime");
    if(username==null){
        response.sendRedirect("login.jsp");
    }
%>
<script type="application/javascript">
    var username = ${username};
</script>
