package cn.com.hz_project.presenter.activityPresenter;

import android.text.TextUtils;


import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.model.server.BusinessTask;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.GsonUtil;
import cn.com.hz_project.tools.utils.LogUtils;
import rx.Subscriber;

/**
 */
public class NewsPresenter implements NewsContract.Presenter {
    private BusinessTask mNewsTask;
    private NewsContract.View mNewsView;

    public NewsPresenter(NewsContract.View mNewsView) {
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
               getSocial(page ,type);
               break;
           case 1:
               getSport(page,type);
               break;
       }
    }
    public void getSocial(int page,int type) {
        mNewsTask.geNewsList(new Subscriber<String>() {
            @Override
            public void onCompleted() {
//                LogUtils.e("PicturePresenter", "onCompleted");
            }
            @Override
            public void onError(Throwable e) {
//                LogUtils.e("PicturePresenter", "onError");
            }
            @Override
            public void onNext(String s) {
                if(!TextUtils.isEmpty(s)){
                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,HttpResult.class));
                }
            }
        }, page,type, Urls.NEWS);
    }
    public void getSport(int page,int type) {
        mNewsTask.geNewsList(new Subscriber<String>() {
            @Override
            public void onCompleted() {
//                LogUtils.e("PicturePresenter", "onCompleted");
            }
            @Override
            public void onError(Throwable e) {
//                LogUtils.e("PicturePresenter", "onError");
            }
            @Override
            public void onNext(String s) {
                if(!TextUtils.isEmpty(s)){
                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,HttpResult.class));
                }
            }
        }, page,type,Urls.NEWS);
    }


}
