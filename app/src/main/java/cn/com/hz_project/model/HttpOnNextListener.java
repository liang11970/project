package cn.com.hz_project.model;

/**
 * 成功回调处理
 * Created by WZG on 2016/7/16.
 */
public interface HttpOnNextListener<T> {
    void onNext(T t);
}
