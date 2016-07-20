package cn.com.hz_project.model.server;

import cn.com.hz_project.model.bean.MeetingAddBean;
import cn.com.hz_project.model.bean.MeetingBean;
import cn.com.hz_project.model.bean.MeetingSignInBean;
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

    @GET("addMeetItem")
    Observable<MeetingSignInBean> getMeetingSignIn(@Query("meet_id") String MBD_ID,
                                                   @Query("login_user_id") String userId,
                                                   @Query("remarks") String MBD_REMARKS,
                                                   @Query("qd_user_id") String qd_user_id );
    @GET("addMeet")
    Observable<MeetingAddBean> getMeetingAdd(@Query("name") String name,
                                             @Query("start_time")String stime,
                                             @Query("end_time")String etime,
                                             @Query("remarks")String remarks);
}
