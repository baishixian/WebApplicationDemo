<%--
  Created by IntelliJ IDEA.
  User: baishixian
  Date: 2017/10/25
  Time: 下午10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<h1>注            册</h1>
<hr>
<form action="RegisterServlet" method="post">
    <table class="centertable">
        <tr>
            <td class="td1">用 户 名：</td>
            <td class="td2"><input class="text1" type="text"
                                   name="userName" /></td>
        </tr>
        <tr>
            <td class="td1">密    码：</td>
            <td class="td2"><input class="text1" type="password"
                                   name="password" /></td>
        </tr>
        <tr>
            <td class="td1">确认密码：</td>
            <td class="td2"><input class="text1" type="password"
                                   name="confirmPassword" /></td>
        </tr>
        <tr>
            <td colspan="2"><input class="button" type="submit" value="注册"
                                   onchange="checkpwd()" /> <input class="button" type="button"
                                                                   onclick="javascript:history.back(-1);" value="返回" /></td>
        </tr>
    </table>
</form>
</body>
</html>