package cn.com.hz_project.model.server;

import cn.com.hz_project.model.bean.MeetingBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tan on 2016/7/16 0016.
 */
public interface MeetingService {
    @GET("meetList")
    Observable<MeetingBean> getMeetData (@Query("page") int pageNub );
}
