package hit.weibo.action;

import hit.weibo.persistence.repository.CommentRepository;
import hit.weibo.persistence.repository.WeiboRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by ITX351 on 2016/10/14.
 */
public class DeleteCommentAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Integer comment_id = Integer.parseInt(request.getParameter("comment_id"));
        CommentRepository.deleteComment(comment_id);
        return "main.jsp";
    }
}
