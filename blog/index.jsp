<%@ page import="hit.weibo.util.Helper" %><%--
  Created by IntelliJ IDEA.
  User: ITX351
  Date: 2016/10/11
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
    <%
      out.print(Helper.toString(session.getAttribute("indexInformation")));
      session.setAttribute("indexInformation", null);
    %>
    <form action="/AdminLogin.action" method="post">
        Username: <input type="text" name="username"/><br/>
        Password: <input type="password" name="password"/><br/>
        <input type="submit" name="login" value="Login"/>
    </form>
    <p>
        <a href="/register.jsp">Register</a>
    </p>
  </body>
</html>
