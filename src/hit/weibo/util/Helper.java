package hit.weibo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String getCurDateTime() {
        SimpleDateFormat time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDateTime = time.format(new Date());
        System.out.println("curDate = " + curDateTime);
        return curDateTime;
    }
}
