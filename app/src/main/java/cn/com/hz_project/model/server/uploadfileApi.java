package cn.com.hz_project.model.server;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * ==================================
 * Created by wangl on 2016/7/20.时间15:35
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public interface uploadfileApi {
    @Multipart
    @POST("uploadfile")
    Call<String> upload(@Part("fileName") String des,
                        @Part("file\"; filename=\"1.txt") RequestBody file);
}
