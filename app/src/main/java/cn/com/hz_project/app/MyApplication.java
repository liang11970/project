package cn.com.hz_project.app;

import android.app.Application;

import cn.com.hz_project.model.server.FileServer;
import cn.com.hz_project.tools.utils.scalars.ScalarsConverterFactory;
import retrofit2.Retrofit;

/**
 * ==================================
 * Created by wangl on 2016/7/11.时间14:11
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class MyApplication extends Application {


    /**
     * 声明请求的接口
     */
    public static FileServer netWorkService;

    /**
     * 网络请求框架
     */
    public static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();


    }


}
