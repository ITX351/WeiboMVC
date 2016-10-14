package hit.weibo.action;

import hit.weibo.persistence.repository.FollowRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by ITX351 on 2016/10/14.
 */
public class ChangeFollowAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int from = Integer.parseInt(request.getParameter("from"));
        int to = Integer.parseInt(request.getParameter("to"));
        if (FollowRepository.findByFromAndTo(from, to)) {
            FollowRepository.deleteFollow(from, to);
        } else {
            FollowRepository.insertFollow(from, to);
        }
        return "main.jsp";
    }
}
