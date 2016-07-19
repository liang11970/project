package cn.com.hz_project.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Tan on 2016/7/18 0018.
 */
public class LoadMoreListview extends ListView implements AbsListView.OnScrollListener {

    private int mtotalItemCout;
    private int mfirstVisibleItem;
    private int lastItem;
    private boolean isLoading = false;
    private IsLoadingListener mIsLoadingListener;


    public LoadMoreListview(Context context) {
        super(context);
        initView();
    }

    public LoadMoreListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadMoreListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (lastItem == mtotalItemCout && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                mIsLoadingListener.onload();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mtotalItemCout = totalItemCount;
        lastItem = firstVisibleItem + visibleItemCount;


    }


    public interface IsLoadingListener {
        public void onload();
    }

    public void setOnLoadMordListener(IsLoadingListener isLoadingListener) {
        mIsLoadingListener = isLoadingListener;
    }
}
