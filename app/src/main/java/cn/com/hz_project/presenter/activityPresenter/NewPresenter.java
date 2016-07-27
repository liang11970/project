package cn.com.hz_project.presenter.activityPresenter;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.model.bean.NewsContext;
import cn.com.hz_project.model.server.BusinessTask;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.GsonUtil;
import cn.com.hz_project.tools.utils.LogUtils;
import rx.Subscriber;

/**
 */
public class NewPresenter implements NewContext.Presenter {
    private BusinessTask mNewsTask;
    private NewContext.View mNewsView;

    public NewPresenter(NewContext.View mNewsView) {
        this.mNewsView = mNewsView;
        mNewsTask = new BusinessTask();
    }

    @Override
    public void start(int page) {
    }
    @Override
    public void start(int page, int type) {
       switch (type){
           case 0:
               getSocial(page);
               break;

       }
    }
    public void getSocial(int page) {
        mNewsTask.geNewsList(new Subscriber<String>() {
            @Override
            public void onCompleted() {
//                LogUtils.e("PicturePresenter", "onCompleted");
                Logger.e("onCompleted");
            }
            @Override
            public void onError(Throwable e) {
//                LogUtils.e("PicturePresenter", "onError");
                Logger.e("onError");
            }
            @Override
            public void onNext(String s) {
                if(!TextUtils.isEmpty(s)){
                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,NewsContext.class));
                }
            }
        }, page,2, Urls.NEWSCONTEXT);
    }

}
