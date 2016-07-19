package cn.com.hz_project.presenter.activityPresenter;

import android.text.TextUtils;


import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.model.server.BusinessTask;
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
//           case 2:
//               getTechnology(page,type);
//               break;
//           case 3:
//               getWorld(page,type);
//               break;
//           case 4:
//               getRecreation(page,type);//娱乐
//               break;
//           case 5:
//               getRemarkable(page,type);//奇闻
//               break;
//           case 6:
//               getHealth(page,type);
//               break;
       }
    }
    public void getSocial(int page,int type) {
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
                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,HttpResult.class));
                }
            }
        }, page,type);
    }
    public void getSport(int page,int type) {
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
                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,HttpResult.class));
                }
            }
        }, page,type);
    }
//    public void getTechnology(int page,int type) {
//        mNewsTask.geNewsList(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                LogUtils.e("PicturePresenter", "onCompleted");
//            }
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.e("PicturePresenter", "onError");
//            }
//            @Override
//            public void onNext(String s) {
//                if(!TextUtils.isEmpty(s)){
//                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,NewsEntity.class));
//                }
//            }
//        }, page,type);
//    }
//    public void getWorld(int page,int type) {
//        mNewsTask.geNewsList(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                LogUtils.e("PicturePresenter", "onCompleted");
//            }
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.e("PicturePresenter", "onError");
//            }
//            @Override
//            public void onNext(String s) {
//                if(!TextUtils.isEmpty(s)){
//                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,NewsEntity.class));
//                }
//            }
//        }, page,type);
//    }
//    public void getRecreation(int page,int type) {
//        mNewsTask.geNewsList(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                LogUtils.e("PicturePresenter", "onCompleted");
//            }
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.e("PicturePresenter", "onError");
//            }
//            @Override
//            public void onNext(String s) {
//                if(!TextUtils.isEmpty(s)){
//                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,NewsEntity.class));
//                }
//            }
//        }, page,type);
//    }
//    public void getRemarkable(int page,int type) {
//        mNewsTask.geNewsList(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                LogUtils.e("PicturePresenter", "onCompleted");
//            }
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.e("PicturePresenter", "onError");
//            }
//            @Override
//            public void onNext(String s) {
//                if(!TextUtils.isEmpty(s)){
//                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,NewsEntity.class));
//                }
//            }
//        }, page,type);
//    }
//    public void getHealth(int page,int type) {
//        mNewsTask.geNewsList(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//                LogUtils.e("PicturePresenter", "onCompleted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                LogUtils.e("PicturePresenter", "onError");
//            }
//
//            @Override
//            public void onNext(String s) {
//                if(!TextUtils.isEmpty(s)){
//                    mNewsView.showInfo(GsonUtil.changeGsonToBean(s,NewsEntity.class));
//                }
//            }
//        }, page,type);
//    }
}