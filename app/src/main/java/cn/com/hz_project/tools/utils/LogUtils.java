package cn.com.hz_project.tools.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by ku on 2016/7/14.
 */
public class LogUtils {

    private static Boolean isDebug = true;

    public static void i(Context context,String tag,String msg){
        if (isDebug){
            Log.i(context.toString()+tag,msg);
        }
    }

    public static void e(Context context,String tag,String msg){
        if (isDebug){
            Log.e(context.toString()+tag,msg);
        }
    }

    public static void v(Context context,String tag,String msg){
        if (isDebug){
            Log.v(context.toString()+tag,msg);
        }
    }

    public static void d(Context context,String tag,String msg){
        if (isDebug){
            Log.d(context.toString()+tag,msg);
        }
    }
}
