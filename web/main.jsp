<%@ page import="hit.weibo.util.LoginStatus" %><%--
  Created by IntelliJ IDEA.
  User: ITX351
  Date: 2016/10/12
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weibo Main</title>
</head>
<body>
    <%
    LoginStatus loginStatus = (LoginStatus)session.getAttribute("loginStatus");
    if (LoginStatus.isLogged(loginStatus)) { %>
        Login successful.
        Welcome, <%= loginStatus.getUsername() %>
    <% } else { %>
        You haven't logged in.
    <% } %>
</body>
</html>
