<%--
  Created by IntelliJ IDEA.
  User: ITX351
  Date: 2016/10/12
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log out</title>
</head>
<body>
    <% session.setAttribute("loginStatus", null); %>
    You have logged out.
    <a href="/index.jsp">Log in</a>
</body>
</html>
