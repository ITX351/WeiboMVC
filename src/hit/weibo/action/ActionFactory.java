package hit.weibo.action;

/**
 * Created by ITX351 on 2016/10/11.
 */
public class ActionFactory {
    public static Action getAction(String actionName) {
        if ("AdminLogin".equals(actionName)) {
            return new LoginAction();
        }
        return null;
    }
}
