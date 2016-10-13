package hit.weibo.util;

/**
 * Created by ITX351 on 2016/10/13.
 */
public class Helper {
    public static String toString( Object object ){
        return toString( object , "" );
    }

    private static String toString( Object object , String defaultVal ){
        return (null == object) ? defaultVal : String.valueOf( object );
    }
}
