package cn.com.hz_project.view.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * RecycleView上拉加载更多
 */
public abstract class AutoLoadOnScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;

    /**
     * 是否正在加载
     */
    private boolean loading = false;

    /**
     * item总条数，可见item最后一条
     */
    int totalItemCount, lastVisibleItem;

    /**
     * 当前页
     */
    private int currentPage = 1;

    /**
     * 布局管理器
     */
    private LinearLayoutManager mLinearLayoutManager;

    public AutoLoadOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItemCount = mLinearLayoutManager.getItemCount();
        lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();

        if (!loading && (lastVisibleItem > totalItemCount - 3) && dy > 0) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
