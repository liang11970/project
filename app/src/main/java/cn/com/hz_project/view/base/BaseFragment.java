package cn.com.hz_project.view.base;

import android.app.Fragment;

import rx.Subscription;

/**
 * ==================================
 * Created by wangl on 2016/7/12.时间14:10
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public abstract class BaseFragment extends Fragment {
    protected Subscription subscription;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    private void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    protected abstract int getDialogRes();

    protected abstract int getTitleRes();
}
