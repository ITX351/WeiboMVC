package hit.weibo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by ITX351 on 2016/10/11.
 */
public interface Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException;
}
