package cn.com.hz_project.presenter.activityPresenter;


import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.view.base.BasePresenter;
import cn.com.hz_project.view.base.BaseView;

/**
 * 作者：chs on 2016/4/28 10:32
 * 邮箱：657083984@qq.com
 */
public interface NewsContract {
    interface View extends BaseView<Presenter> {
        void showInfo(HttpResult entity);
    }
    interface Presenter extends BasePresenter {
         void start(int page, int type);
    }
}
