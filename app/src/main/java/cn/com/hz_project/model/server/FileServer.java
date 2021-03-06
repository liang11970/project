package cn.com.hz_project.model.server;

import cn.com.hz_project.model.bean.DataResponse;
import cn.com.hz_project.model.bean.Login;
import cn.com.hz_project.model.bean.Title;
import cn.com.hz_project.model.bean.UploadFile;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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


    /**
     * 单
     * @return
     */
    @Multipart
    @POST("appCtrl/uploadfile")
    public Call<String> postFile(@Part("file\"; filename=\"faq.docx") RequestBody fileBody);

}
