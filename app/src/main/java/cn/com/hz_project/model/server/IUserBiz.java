package cn.com.hz_project.model.server;

import cn.com.hz_project.model.bean.Quiz;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * ==================================
 * Created by wangl on 2016/7/20.时间14:52
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public interface IUserBiz {

    @Multipart
    @POST ("uploadfile")
    Call<Quiz> editUser (@Header("Authorization") String authorization, @Part("file\"; filename=\"1.text") RequestBody file , @Part("FirstName") RequestBody fname, @Part("Id") RequestBody id);


}
