package cn.com.hz_project.model.server;

import cn.com.hz_project.model.bean.DataResponse;
import cn.com.hz_project.model.bean.Login;
import cn.com.hz_project.model.bean.Title;
import cn.com.hz_project.model.bean.UploadFile;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * ==================================
 * Created by wangl on 2016/7/19.时间9:56
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public interface FileServer {

    @POST("/file")
    @Multipart
    Observable<DataResponse<UploadFile>> uploadFile(@Part("file\"; filename=\"avatar.png\"") RequestBody file);


    /**
     * 登录
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Observable<Login> postText(@Body Title bean);



        @Multipart
        @POST("/")
        Call<String> upload(@Part("fileName") String des, @Part("file\"; filename=\"1.txt") RequestBody file);

}
