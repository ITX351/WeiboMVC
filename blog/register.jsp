<%@ page import="hit.weibo.util.Helper" %><%--
  Created by IntelliJ IDEA.
  User: ITX351
  Date: 2016/10/13
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%
    out.print(Helper.toString(session.getAttribute("registerInformation")));
    session.setAttribute("registerInformation", null);
%>
<form action="/Register.action" method="post">
    Username: <input type="text" name="username"/><br/>
    Password: <input type="password" name="password"/><br/>
    <input type="submit" name="register" value="register"/>
</form>
</body>
</html>
