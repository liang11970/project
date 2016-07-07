package cn.com.projectdemos.meeting.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
    //是否禁止左右滑动
    private boolean disableScroll = false;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (disableScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(arg0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (disableScroll)
            return false;
        else
            return super.onTouchEvent(arg0);
    }

    public boolean isDisableScroll() {
        return disableScroll;
    }

    public void setDisableScroll(boolean disableScroll) {
        this.disableScroll = disableScroll;
    }

}