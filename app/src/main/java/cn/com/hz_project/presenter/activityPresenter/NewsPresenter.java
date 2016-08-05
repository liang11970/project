package cn.com.hz_project.presenter.activityPresenter;

import android.text.TextUtils;
import android.util.Log;


import com.orhanobut.logger.Logger;

import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.model.bean.VideoResult;
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
    private NewsContract.Video mNewsVideo;

    public NewsPresenter(NewsContract.View mNewsView,Boolean s) {
        this.mNewsView = mNewsView;
        mNewsTask = new BusinessTask();
    }

    public NewsPresenter(NewsContract.Video mNewsVideo) {
        this.mNewsVideo = mNewsVideo;
        mNewsTask = new BusinessTask();
    }

    public NewsPresenter(NewsContract.Video mNewsVideo,int a) {
        this.mNewsVideo = mNewsVideo;
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
           case 2:
               Logger.e("视频在第几页"+page);
               getVideo(page,3);
               break;
           case 4://获取首页轮播图相关视频
               Log.i("------------>","1---走4");
               getTops(3,4);
               break;
       }
    }

    private void getVideo(int page, int type) {
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
                    mNewsVideo.showVideo(GsonUtil.changeGsonToBean(s,VideoResult.class));
                }
            }
        }, page,type,Urls.VIDEO);
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

    /**
     * 获取首页轮播图视频列表
     * @param pageSize
     * @param type
     */
    public void getTops(int pageSize,int type){
        mNewsTask.geNewsList(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogUtils.e("PicturePresenter", "onCompleted");
            }
            @Override
            public void onError(Throwable e) {
                LogUtils.e("PicturePresenter", "onError");
            }
            @Override
            public void onNext(String s) {
                if(!TextUtils.isEmpty(s)){
                    mNewsVideo.showVideo(GsonUtil.changeGsonToBean(s,VideoResult.class));
                    LogUtils.e("PicturePresenter", "onNext");
                }
            }
        }, pageSize,type,Urls.VIDEO);
    }

}
