package cn.com.hz_project.tools.utils;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.scalars.ScalarsConverterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * 作者：chs on 2016/4/26 10:48
 * 邮箱：657083984@qq.com
 */
public class HttpUtils {

    private static final int DEFAULT_TIMEOUT = 5;

    private static HttpUtils mHttpUtils;
    //构造方法私有
    private HttpUtils() {

    }

    //获取单例
    public static HttpUtils getInstance(){
        if(mHttpUtils==null){
           synchronized (HttpUtils.class){
               if(mHttpUtils==null){
                   mHttpUtils = new HttpUtils();
               }
           }
        }
        return mHttpUtils;
    }
    public Retrofit initRetrofit(){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Urls.NEWS)
                .build();

        return retrofit;
    }
    public Retrofit initRetrofitWithHeader(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .client(genericClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(url)
                .build();
        return retrofit;
    }



    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("apikey", "2ffc3e48c7086e0e1faa003d781c0e69")
                                .build();
                        return chain.proceed(request);
                    }

                })
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        return httpClient;
    }
}
