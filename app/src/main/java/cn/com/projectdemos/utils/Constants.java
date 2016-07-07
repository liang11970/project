package cn.com.projectdemos.utils;

/**
 * Created by wee on 2016/6/22.
 */
public class Constants {

    /**
     * 打release包要改为false
     */
    public final static boolean DEBUG = true;

    private final static String URL_FAST_MOVE = "http://192.168.0.117:8080/";

    /**
     * 用户登录
     */
    public final static String URL_SHOP_LOGIN = URL_FAST_MOVE + "wsbx/loginAction!login.action";



    public final static String URL_MEETING = "http://192.168.0.117:8080/";

    /**
     * 获取会议列表
     */
    public final static String URL_MEETING_LIST = URL_MEETING + "wsbx/hyAction!hylist.action";

    /**
     * 用户签到
     */
    public final static String URL_MEETING_SIN_IN = URL_MEETING + "wsbx/qdxxAction!saveOrUpdate.action";


}
