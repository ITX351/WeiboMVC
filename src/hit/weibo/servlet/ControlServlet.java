package hit.weibo.servlet;

/**
 * Created by ITX351 on 2016/10/11.
 */
import hit.weibo.action.Action;
import hit.weibo.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String pathName = request.getServletPath();

        int index = pathName.indexOf(".");
        String ActionName = pathName.substring(1, index);

        String ActionClassName = this.getInitParameter(ActionName);
        Action action = ActionFactory.getAction(ActionClassName);

        if (action == null) {
            //TODO
            return;
        }

        String url = action.execute(request, response);
        request.getRequestDispatcher(url).forward(request, response);
    }
}
