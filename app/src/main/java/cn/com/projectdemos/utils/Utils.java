package cn.com.projectdemos.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wee on 16/6/28.
 */
public class Utils {

    /**
     * @author wee 409535539@qq.com
     * @param time 要转换的时间，时间戳的字符串
     * @return  当前时间
     */
    public static String getTimestamp(String  time) {
        if (time == null){
            return "";
        }
        long l = Long.parseLong(time);
        Date date=new Date(l);
        SimpleDateFormat format=new SimpleDateFormat("MM-dd HH:mm:ss");
        return format.format(date);
    }

}
