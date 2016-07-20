package cn.com.hz_project.model.server;


import cn.com.hz_project.Article.ArticleDetailBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/7/10.
 */
public interface ActionService {


    //传入id查看详细信息
    @GET("/api/4/news/{id}")
    Observable<ArticleDetailBean> getNewsDetails(@Path("id") int id);

}
