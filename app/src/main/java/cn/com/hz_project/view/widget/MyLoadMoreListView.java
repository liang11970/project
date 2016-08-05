package cn.com.hz_project.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by ku on 2016/8/3.
 */
public class MyLoadMoreListView extends LoadMorRecyclerView{

    public MyLoadMoreListView(Context context) {
        super(context,null);
    }

    public MyLoadMoreListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyLoadMoreListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
