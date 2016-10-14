package hit.weibo.action;

import hit.weibo.persistence.repository.CommentRepository;
import hit.weibo.persistence.repository.WeiboRepository;
import hit.weibo.util.LoginStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * Created by ITX351 on 2016/10/14.
 */
public class PublishCommentAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Integer weibo_id = Integer.parseInt(request.getParameter("weibo_id"));
        String content = request.getParameter("content");
        Integer commenter = ((LoginStatus)request.getSession().getAttribute("loginStatus")).getUser_id();
        CommentRepository.insertComment(commenter, content, weibo_id);
        return "main.jsp";
    }
}
