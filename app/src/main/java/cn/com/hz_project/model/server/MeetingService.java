package cn.com.hz_project.model.server;

import cn.com.hz_project.model.bean.DeleteMeeting;
import cn.com.hz_project.model.bean.MeetingAddBean;
import cn.com.hz_project.model.bean.MeetingBean;
import cn.com.hz_project.model.bean.MeetingSignInBean;
import cn.com.hz_project.model.bean.StaffBean;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tan on 2016/7/16 0016.
 */
public interface MeetingService {
    @GET("meetList")
    Observable<MeetingBean> getMeetData (@Query("page") int pageNub );

    @GET("addMeetItem")
    Observable<MeetingSignInBean> getMeetingSignIn(@Query("MEET_ID") String MBD_ID,
                                                   @Query("LOGIN_USER_ID") String userId,
                                                   @Query("PHONE") String qd_user_id,
                                                   @Query("MARK") String MBD_REMARKS);
    @FormUrlEncoded
    @POST("addMeet")
    Observable<MeetingAddBean> getMeetingAdd(@Field("name") String name,
                                             @Field("start_time")String stime,
                                             @Field("end_time")String etime,
                                             @Field("remarks")String remarks);


    @GET("qdUserList")
    Observable<StaffBean> getStaffData(@Query("meet_id") String mettingID);

    @GET("del_meeting")
    Observable<DeleteMeeting> getDeleteMeeting(@Query("MEET_ID") String meetingID);
}
