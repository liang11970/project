package cn.com.hz_project.model.impl;

import cn.com.hz_project.model.bean.MovieEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ==================================
 * Created by wangl on 2016/7/12.时间15:26
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public interface MovieService {

    @GET("server")
    Call<MovieEntity> getTopMovie(@Query("user") int username, @Query("password") int password);
}
