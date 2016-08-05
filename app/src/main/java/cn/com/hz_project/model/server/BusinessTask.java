package cn.com.hz_project.model.server;


import com.orhanobut.logger.Logger;

import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.tools.utils.HttpUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络请求
 */
public class BusinessTask {



    public void geNewsList(Subscriber<String> subscriber, int currentPage, int type,String url) {
        NewServer newsService = HttpUtils.getInstance().initRetrofitWithHeader(url).create(NewServer.class);
        Observable<String> observable = null;
        switch (type) {
            case 0:
                observable= newsService.PostField(currentPage,type);
                break;
            case 1:
                observable = newsService.PostField(currentPage, type);
                break;
            case 2:
                observable = newsService.PostFieldContext(currentPage);
                break;
            case 3:
                Logger.e("视频相关"+currentPage);
                observable = newsService.PostVideo(currentPage);
                break;

            case 4:
                observable = newsService.PostTopsVideo(3,1);
                break;

        }


        if (observable != null) {
            observable
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }




}
