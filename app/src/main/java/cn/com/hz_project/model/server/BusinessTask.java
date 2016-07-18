package cn.com.hz_project.model.server;


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



    public void geNewsList(Subscriber<String> subscriber, int currentPage, int type) {
        NewServer newsService = HttpUtils.getInstance().initRetrofitWithHeader().create(NewServer.class);
        Observable<String> observable = null;
        switch (type) {
            case 0:
                observable= newsService.PostField(currentPage,type);
                break;
            case 1:
                observable = newsService.PostField(currentPage, type);
                break;
//            case 2:
//                observable = newsService.getTechnology(currentPage, 10);
//                break;
//            case 3:
//                observable = newsService.getWorld(currentPage, 10);
//                break;
//            case 4:
//                observable = newsService.getRecreation(currentPage, 10);
//                break;
//            case 5:
//                observable = newsService.getRemarkable(currentPage, 10);
//                break;
//            case 6:
//                observable = newsService.getHealth(currentPage, 10);
//                break;
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
