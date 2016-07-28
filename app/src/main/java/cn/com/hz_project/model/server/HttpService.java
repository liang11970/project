package cn.com.hz_project.model.server;


import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import cn.com.hz_project.model.bean.Advice;
import cn.com.hz_project.model.bean.BaseResultEntity;
import cn.com.hz_project.model.bean.MeetingAddBean;
import cn.com.hz_project.model.bean.RetrofitEntity;
import cn.com.hz_project.model.bean.Subject;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * service统一接口数据
 * Created by WZG on 2016/7/16.
 */
public interface HttpService {

//    @retrofit.http.POST("AppFiftyToneGraph/videoLink")
//    Call<RetrofitEntity> getAllVedio(@retrofit.http.Body boolean once_no);

    @POST("AppFiftyToneGraph/videoLink")
    Observable<RetrofitEntity> getAllVedioByss(@Body boolean once_no);

    @POST("AppFiftyToneGraph/videoLink")
    Observable<BaseResultEntity<List<Subject>>> getAllVedioBys(@Body boolean once_no);

    @FormUrlEncoded
    @POST("addFankui")
    Observable<Advice> getAllVedioBy(@Field("context") String loginName, @Field("userid") String password);



    @POST("addFankui")
    Observable<Advice> getAllVedioByss1(@Body Map<String,String> once_no);


}
