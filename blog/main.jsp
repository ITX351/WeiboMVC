<%@ page import="hit.weibo.util.LoginStatus" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="hit.weibo.persistence.repository.UserRepository" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="hit.weibo.persistence.entity.WeiboEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="hit.weibo.persistence.repository.WeiboRepository" %>
<%@ page import="hit.weibo.persistence.entity.CommentEntity" %>
<%@ page import="java.util.Objects" %><%--
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
        Welcome, <%= loginStatus.getUsername() %>
        <a href="/logout.jsp">Log out</a>

    <%
        HashSet<Integer> follower = null, following = null;
        List<WeiboEntity> weibos = null;
        try {
            follower = UserRepository.getFollower(loginStatus.getUser_id());
            following = UserRepository.getFollowing(loginStatus.getUser_id());
            weibos = WeiboRepository.showAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
    <form action="/blog/PublishWeibo.action" method="post">
        <br/>
        <textarea rows="3" cols="56" name="content"></textarea>
        <br/>
        <input type="submit" value="submit"/>
    </form>

    <table border="1">
        <tr>
            <th>Content</th>
            <th>DateTime</th>
            <th>Publisher</th>
            <th> </th>
            <th> </th>
        </tr>

        <% for (WeiboEntity weibo : weibos) { %>
        <tr>
            <td><%=weibo.getContent()%></td>
            <td><%=weibo.getCreateAt()%></td>
            <td><%=weibo.getCreatorName()%></td>
            <td>
                <%
                    if (!Objects.equals(weibo.getCreator(), loginStatus.getUser_id())) {
                        boolean followFrom = follower.contains(weibo.getCreator()),
                                followTo = following.contains(weibo.getCreator());
                        String show;
                        if (!followFrom && !followTo) {
                            show = "Follow";
                        } else if (!followFrom && followTo) {
                            show = "Unfollow";
                        } else if (followFrom && !followTo) {
                            show = "-> Follow";
                        } else {
                            show = "<-> Unfollow";
                        }
                %>
                    <form action="/blog/ChangeFollow.action" method="post">
                        <input type="hidden" name="from" value="<%=loginStatus.getUser_id()%>"/>
                        <input type="hidden" name="to" value="<%=weibo.getCreator()%>"/>
                        <input type="submit" value="<%=show%>"/>
                    </form>
                <% }%>
            </td>
            <td>
                <% if (Objects.equals(weibo.getCreator(), loginStatus.getUser_id())) { %>
                    <form action="/blog/DeleteWeibo.action" method="post">
                        <input type="hidden" name="weibo_id" value="<%=weibo.getId()%>"/>
                        <input type="submit" value="Delete"/>
                    </form>
                <% } %>
            </td>
        </tr>
        <tr>
            <td colspan="5">
                <table>
                    <% for (CommentEntity comment : weibo.getComments()) { %>
                    <tr>
                        <td><%=comment.getContent()%></td>
                        <td><%=comment.getCreateAt()%></td>
                        <td><%=comment.getCommenterName()%></td>
                        <td>
                            <% if (Objects.equals(comment.getCommenter(), loginStatus.getUser_id())) { %>
                                <form action="/blog/DeleteComment.action" method="post">
                                    <input type="hidden" name="comment_id" value="<%=comment.getId()%>"/>
                                    <input type="submit" value="Delete"/>
                                </form>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                    <tr>
                        <td rowspan="4">
                            <form action="/blog/PublishComment.action" method="post">
                                <input type="hidden" name="weibo_id" value="<%=weibo.getId()%>"/>
                                <input type="text" name="content" value="" />
                                <input type="submit" value="comment"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <% } %>
    </table>

    <% } else { %>
        You haven't logged in.
    <% } %>
</body>
</html>
