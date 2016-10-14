package hit.weibo.action;

import hit.weibo.util.ActionConfigParser;

/**
 * Created by ITX351 on 2016/10/11.
 */
public class ActionFactory {
    public static Action getAction(String actionName) {

        try {
            return ActionConfigParser.newAction(actionName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
