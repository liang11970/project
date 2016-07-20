package cn.com.hz_project.Article;


import cn.com.hz_project.tools.utils.BasePrecenter;
import cn.com.hz_project.tools.utils.BaseView;

/**
 * Created by Administrator on 2016/7/10.
 */
public interface ArticleContract {
    interface View extends BaseView<Precenter> {
        void showAtrticleDetail(String content);
    }
    interface Precenter extends BasePrecenter {
        void loadArticleDetail(int id);
    }
}
