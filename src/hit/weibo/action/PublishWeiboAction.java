package hit.weibo.action;

import hit.weibo.persistence.repository.WeiboRepository;
import hit.weibo.util.LoginStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class PublishWeiboAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        // insert into
        String content = request.getParameter("content");

        HttpSession session = request.getSession();
        LoginStatus loginStatus = (LoginStatus) session.getAttribute("loginStatus");
        if (loginStatus != null) {
            WeiboRepository.insertWeibo(loginStatus.getUser_id(), content);
            return "main.jsp";
        }
//        WeiboRepository.insertWeibo(1, content);
        return "error.html";
    }
}
