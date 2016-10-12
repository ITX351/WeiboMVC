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
import java.sql.SQLException;

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
        System.out.println("pathName: " + pathName);

        int index = pathName.indexOf(".");
        String ActionName = pathName.substring(1, index);
        System.out.println("ActionName: " + ActionName);

        Action action = ActionFactory.getAction(ActionName);

        if (action == null) {
            System.out.println("Action is NULL.");
            return;
        }

        try {
            String url = action.execute(request, response);
            System.out.println("URL: " + url);
            //request.getRequestDispatcher(url).forward(request, response);
            response.sendRedirect(url);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }
}
