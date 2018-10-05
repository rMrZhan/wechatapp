package top.jianghuling.wechatapp.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class MyTimeUtil {
    /**
     * @auth Jason
     * @param
     * @return
     * @throws
     */
    public static Date parseTime(String time)throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.parse(time);
    }
}
