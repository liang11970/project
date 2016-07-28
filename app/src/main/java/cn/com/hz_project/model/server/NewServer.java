package cn.com.hz_project.model.server;

import java.util.List;

import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.model.bean.Login;
import cn.com.hz_project.model.bean.New;
import cn.com.hz_project.model.bean.Subjects;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * ==================================
 * Created by wangl on 2016/7/16.时间9:46
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public interface NewServer {



    @FormUrlEncoded
    @POST("newslist")
    Observable<String> PostField(@Field("page") int page,@Field("tab")int tab);

    @FormUrlEncoded
    @POST("newscontext")
    Observable<String> PostFieldContext(@Field("NBD_ID") int nbd_id);




}
