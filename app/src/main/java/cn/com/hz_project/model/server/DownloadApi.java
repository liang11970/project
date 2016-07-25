package cn.com.hz_project.model.server;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ljd on 3/29/16.
 */
public interface DownloadApi {

    @GET("{fileName}")
    Call<ResponseBody> retrofitDownload(@Query("fileName") String userId);
}
