package cn.com.hz_project.model.bean;

import cn.com.hz_project.model.HttpTimeException;
import cn.com.hz_project.model.server.HttpService;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * ==================================
 * Created by wangl on 2016/7/21.时间13:46
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public abstract class BaseEntity<T> implements Func1<BaseResultEntity<T>,T> {
    /**
     * 设置参数
     *
     * @param methods
     * @return
     */
    public abstract Observable getObservable(HttpService methods);

    /**
     * 设置回调sub
     *
     * @return
     */
    public abstract Subscriber getSubscirber();

    @Override
    public T call(BaseResultEntity<T> httpResult) {
        if (httpResult.getRet() == 0) {
            throw new HttpTimeException(httpResult.getMsg());
        }
        return httpResult.getData();
    }
}
