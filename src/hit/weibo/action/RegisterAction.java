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
 * Created by ITX351 on 2016/10/13.
 */
public class RegisterAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        List<UserEntity> userEntities = UserRepository.findByName(username);
        if (userEntities.size() > 0) {
            session.setAttribute("registerInformation", "User with this name has existed.");
            return "register.jsp";
        }

        UserRepository.InsertWithNameAndPassword(username, password);
        session.setAttribute("indexInformation", "Register finished. Please log in with " + username);
        return "index.jsp";
    }
}
