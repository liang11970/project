package cn.com.hz_project.presenter.activityPresenter;

import cn.com.hz_project.model.server.BusinessTask;

/**
 * ==================================
 * Created by wangl on 2016/7/27.时间16:25
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class AdvicePresenter implements NewsContract.Presenter {

    private final BusinessTask mNewsTask;
    private NewsContract.View mNewsView;

    public AdvicePresenter(NewsContract.View mNewsView) {
        this.mNewsView = mNewsView;
        mNewsTask = new BusinessTask();
    }


    @Override
    public void start(int page, int type) {

    }

    @Override
    public void start(int page) {



    }
}
