package cn.com.hz_project.view.fragment;

import java.util.ArrayList;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangjie.shadowviewhelper.ShadowProperty;
import com.wangjie.shadowviewhelper.ShadowViewHelper;

import cn.com.hz_project.view.activity.SecondActivity;
import cn.com.projectdemos.R;

public class HomeFragment extends Fragment{

    Resources resources;
    //  private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    // private ImageView ivBottomLine;
    private TextView tvTabNew, tvTabHot,tvTabjiagou,tvTabjieshao;

    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    public final static int num = 4 ;
    Fragment home1;
    Fragment home2;
    Fragment home3;
    Fragment home4;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_home, null);
        //view.findViewById(R.id.buju1).setBackgroundColor(getResources().getColor(R.color.huise));
        ShadowViewHelper.bindShadowHelper(
                new ShadowProperty()
                        .setShadowColor(0x77000000)
                        .setShadowDy(1)
                        .setShadowRadius(1)
                , view.findViewById(R.id.linearLayout1));

        resources = getResources();
        InitWidth(view);
        InitTextView(view);
//        InitViewPager(view);
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, 0);
        tvTabHot.setTextColor(resources.getColor(R.color.lightwhite));
        animation.setFillAfter(true);
        animation.setDuration(300);
        // ivBottomLine.startAnimation(animation);

        return view;
    }

    private void InitTextView(View parentView) {
        tvTabNew = (TextView) parentView.findViewById(R.id.tv_tab_1);
        tvTabHot = (TextView) parentView.findViewById(R.id.tv_tab_2);
        tvTabjiagou = (TextView) parentView.findViewById(R.id.tv_tab_3);
        tvTabjieshao = (TextView) parentView.findViewById(R.id.tv_tab_4);

        //tvTabNew.setOnClickListener(new MyOnClickListener(0));
        //tvTabHot.setOnClickListener(new MyOnClickListener(1));
        // tvTabjiagou.setOnClickListener(new MyOnClickListener(2));
        // tvTabjieshao.setOnClickListener(new MyOnClickListener(3));
        tvTabNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SecondActivity.class));
            }
        });
        tvTabHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SecondActivity.class));
            }
        });
        tvTabjiagou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SecondActivity.class));
            }
        });
        tvTabjieshao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SecondActivity.class));
            }
        });

    }



    private void InitViewPager(View parentView) {
        // mPager = (ViewPager) parentView.findViewById(R.id.vPager);
        fragmentsList = new ArrayList<Fragment>();

        home1 = new HomeFragment_1();
        home2 = new HomeFragment_2();
        home3 = new HomeFragment_3();
        home4 = new HomeFragment_4();

        fragmentsList.add(home1);
        fragmentsList.add(home2);
        fragmentsList.add(home3);
        fragmentsList.add(home4);

//	        mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
        //      mPager.setOnPageChangeListener(new MyOnPageChangeListener());
        //      mPager.setCurrentItem(0);

    }

    private void InitWidth(View parentView) {
        //ivBottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
        // bottomLineWidth = ivBottomLine.getLayoutParams().width;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (int) ((screenW / num - bottomLineWidth) / 4);
        Log.e("11111111",""+offset);
        int avg = (int) (screenW / num);
        position_one = avg + offset;
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
//            mPager.setCurrentItem(index);
        }
    };

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    view.findViewById(R.id.buju1).setBackgroundColor(getResources().getColor(R.color.huise));
                    view.findViewById(R.id.buju2).setBackgroundColor(getResources().getColor(R.color.white));
                    view.findViewById(R.id.buju3).setBackgroundColor(getResources().getColor(R.color.white));
                    view.findViewById(R.id.buju4).setBackgroundColor(getResources().getColor(R.color.white));
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, offset, 0, 0);
                    }
                    if (currIndex == 2) {
                        animation = new TranslateAnimation(position_one*2+0, offset, 0, 0);
                    }
                    if (currIndex == 3) {
                        animation = new TranslateAnimation(position_one*3+0, offset, 0, 0);
                    }
                    break;
                case 1:
                    view.findViewById(R.id.buju1).setBackgroundColor(getResources().getColor(R.color.white));
                    view.findViewById(R.id.buju2).setBackgroundColor(getResources().getColor(R.color.huise));
                    view.findViewById(R.id.buju3).setBackgroundColor(getResources().getColor(R.color.white));
                    view.findViewById(R.id.buju4).setBackgroundColor(getResources().getColor(R.color.white));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                    }else
                    if (currIndex==2){
                        animation = new TranslateAnimation(position_one*2, 0+position_one, 0, 0);
                    }
                    if (currIndex==3){
                        animation = new TranslateAnimation(position_one*3, 0+position_one, 0, 0);
                    }
                    break;
                case 2:
                    view.findViewById(R.id.buju1).setBackgroundColor(getResources().getColor(R.color.white));
                    view.findViewById(R.id.buju2).setBackgroundColor(getResources().getColor(R.color.white));
                    view.findViewById(R.id.buju3).setBackgroundColor(getResources().getColor(R.color.huise));
                    view.findViewById(R.id.buju4).setBackgroundColor(getResources().getColor(R.color.white));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(0, position_one*2, 0, 0);
                    }
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(0+position_one, position_one*2, 0, 0);
                    }
                    else
                    if (currIndex==3) {
                        animation = new TranslateAnimation(position_one*3, 0+2*position_one, 0, 0);
                    }
                    break;
                case 3:
                    view.findViewById(R.id.buju1).setBackgroundColor(getResources().getColor(R.color.white));
                    view.findViewById(R.id.buju2).setBackgroundColor(getResources().getColor(R.color.white));
                    view.findViewById(R.id.buju3).setBackgroundColor(getResources().getColor(R.color.white));
                    view.findViewById(R.id.buju4).setBackgroundColor(getResources().getColor(R.color.huise));
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one*3, 0, 0);
                    }
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(0+position_one, position_one*3, 0, 0);
                    }
                    if (currIndex == 2) {
                        animation = new TranslateAnimation(0+2*position_one, position_one*3, 0, 0);
                    }
                    break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(300);
            //ivBottomLine.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

}
