package cn.com.hz_project.model.server;

import java.util.List;

import javax.security.auth.Subject;import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * ==================================
 * Created by wangl on 2016/7/13.时间12:13
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public interface LoginService {

//    @GET("top250")
//    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

/**
     * 登录
     * @param loginName
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Observable<Login> PostField(@Field("loginName") String loginName, @Field("password") String password);
}
