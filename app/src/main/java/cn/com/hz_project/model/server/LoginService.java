package cn.com.hz_project.model.server;

import cn.com.hz_project.model.bean.Login;
import cn.com.hz_project.model.bean.OnlineQuiz;
import cn.com.hz_project.model.bean.Quiz;
import cn.com.hz_project.model.bean.QuizAnswer;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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
    Observable<Login> PostField(@Field("loginName") String loginName, @Field("password") String password,@Field("mark")int key);

    /**
     * 提问
     */
    @FormUrlEncoded
    @POST("addQuestion")
    Observable<OnlineQuiz> Quiz(@Field("USER_ID") String userId, @Field("WQD_QUESTION") String title, @Field("WQD_CONTEXT")String content, @Field("WQD_ANSWER_ROLE") int obj);

    /**
     * 问题列表
     */
    @FormUrlEncoded
    @POST("querQuestion")
    Observable<Quiz> Quiz(@Field("PAGE") int page);

    /**
     * 回答问题
     */
    @FormUrlEncoded
    @POST("addAnswer")
    Observable<QuizAnswer> QuizReply(@Field("USER_ID") String uesrId,@Field("WQD_ID") String quizId,@Field("WAD_CONTEXT") String content);

    /**
     * 查询问题答案
     */
    @FormUrlEncoded
    @POST("queryAnswerByid")
    Observable<QuizAnswer> QueryAns(@Field("WQD_ID") String id, @Field("PAGE")String page);
}
