<%--
  Created by IntelliJ IDEA.
  User: baishixian
  Date: 2017/10/26
  Time: 下午3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
  <head>
    <meta charset="utf-8">
    <title>Web Application Demo</title>
  </head>

  <body>
  <h1>Welcome Web Application</h1>
  <hr/>
  Hello World!<br/>

  <br/>

  <tr>
    <td colspan="2">
      <input class="button" type="button" onclick="goRegister()" value="开始注册"/>
      <input class="button" type="button" onclick="goLogin()" value="开始登录" />
    </td>
  </tr>

  </body>

  <script type="text/javascript">
      function goLogin(){
          window.location = "JSP/login.jsp";
      }

      function goRegister(){
          window.location = "JSP/register.jsp";
      }
  </script>
</html>
