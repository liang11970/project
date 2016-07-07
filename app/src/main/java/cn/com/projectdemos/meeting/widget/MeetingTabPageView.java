package cn.com.projectdemos.meeting.widget;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.projectdemos.R;
import cn.com.projectdemos.meeting.adapter.MeetingTabPageViewAdapter;

/**
 * Created by wee on 16/1/17.
 */
public class MeetingTabPageView extends RelativeLayout implements View.OnClickListener {

    private FragmentActivity mActivity;
    private View layoutView;
    public ViewPager mPager;
    private MeetingTabPageViewAdapter viewPagerAdapter;
    private TextView[] tabArray;
    private View[] lineArray;

    private MeetingTabPageView(Context context) {
        super(context);
    }


    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            choosePage(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    }



    public void choosePage(int pager){
        mPager.setCurrentItem(pager);
        for(int index = 0; index < tabArray.length; index ++){
            if(pager == index){
                lineArray[index].setVisibility(View.VISIBLE);
            }else{
                lineArray[index].setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View view) {
        int index = -1;
        switch (view.getId()) {
            case R.id.dynamic:
                index = 0;
                break;
            case R.id.teacher:
                index = 1;
                break;
            case R.id.student:
                index = 2;
                break;
        }

        choosePage(index);
    }

    public View getLayout() {
        return layoutView;
    }



    public MeetingTabPageView(Context context, String[] tabTitle) {
        this(context);
        mActivity = (FragmentActivity) context;

        layoutView = View.inflate(context, R.layout.tab_learn_circle, null);
        lineArray = new View[3];
        lineArray[0] = (View)layoutView.findViewById(R.id.dynamic_line);
        lineArray[1] = (View)layoutView.findViewById(R.id.teacher_line);
        lineArray[2] = (View)layoutView.findViewById(R.id.student_line);

        tabArray = new TextView[3];
        tabArray[0] = (TextView)layoutView.findViewById(R.id.dynamic);
        tabArray[1] = (TextView)layoutView.findViewById(R.id.teacher);
        tabArray[2] = (TextView)layoutView.findViewById(R.id.student);


        for(int index = 0; index < tabTitle.length; index ++){
            tabArray[index].setText(tabTitle[index]);
            tabArray[index].setOnClickListener(this);
        }

        initViewPager();
    }


    private void initViewPager() {
        mPager = (ViewPager) layoutView.findViewById(R.id.vPager_learn_circle);
        viewPagerAdapter = new MeetingTabPageViewAdapter(
                mActivity.getSupportFragmentManager(), mActivity);
        mPager.setAdapter(viewPagerAdapter);
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mPager.setOffscreenPageLimit(3);
    }



}
