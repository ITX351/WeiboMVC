package hit.weibo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ITX351 on 2016/10/11.
 */
public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        return "index.jsp";
    }
}
