package hit.weibo.action;

import hit.weibo.persistence.entity.UserEntity;
import hit.weibo.persistence.repository.UserRepository;
import hit.weibo.util.LoginStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ITX351 on 2016/10/11.
 */
public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        List<UserEntity> userEntities = UserRepository.findByNameAndPassword(username, password);
        if (userEntities.size() > 0) {
            session.setAttribute("loginStatus", new LoginStatus(true, username, userEntities.get(0).getId()));
            return "main.jsp";
        }

        session.setAttribute("loginInformation", "Authentication failed.");
        session.setAttribute("loginStatus", null);
        return "index.jsp";
    }
}
