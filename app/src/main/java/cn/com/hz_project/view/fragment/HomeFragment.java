package cn.com.hz_project.view.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangjie.shadowviewhelper.ShadowProperty;
import com.wangjie.shadowviewhelper.ShadowViewHelper;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.presenter.activityPresenter.NewsContract;
import cn.com.hz_project.presenter.activityPresenter.NewsPresenter;
import cn.com.hz_project.view.activity.NewActivity;
import cn.com.hz_project.view.activity.SecondActivity;
import cn.com.hz_project.view.base.BaseAdapter;
import cn.com.hz_project.view.base.ViewHolder;
import cn.com.hz_project.view.widget.LoadMoreRecyclerView;
import cn.com.hz_project.view.widget.RecycleViewDivider;
import cn.com.hz_project.view.widget.SlideShowView;
import cn.com.projectdemos.R;

public class HomeFragment extends Fragment implements NewsContract.View {

    Resources resources;
    @InjectView(R.id.tv_tab_1)
    TextView tvTab1;
    @InjectView(R.id.buju1)
    LinearLayout buju1;
    @InjectView(R.id.tv_tab_2)
    TextView tvTab2;
    @InjectView(R.id.buju2)
    LinearLayout buju2;
    @InjectView(R.id.tv_tab_3)
    TextView tvTab3;
    @InjectView(R.id.buju3)
    LinearLayout buju3;
    @InjectView(R.id.tv_tab_4)
    TextView tvTab4;
    @InjectView(R.id.buju4)
    LinearLayout buju4;
    @InjectView(R.id.linearLayout1)
    LinearLayout linearLayout1;

    @InjectView(R.id.listView)
    LoadMoreRecyclerView listView;
    @InjectView(R.id.id_swiperefresh)
    SwipeRefreshLayout idSwiperefresh;
    @InjectView(R.id.mediaController)
    SlideShowView mediaController;

    //  private ViewPager mPager;
    private ArrayList<Fragment> fragmentsList;
    // private ImageView ivBottomLine;
    private TextView tvTabNew, tvTabHot, tvTabjiagou, tvTabjieshao;

    private int currIndex = 0;
    private int bottomLineWidth;
    private int offset = 0;
    private int position_one;
    public final static int num = 4;
    Fragment home1;
    Fragment home2;
    Fragment home3;
    Fragment home4;
    View view;
    private ArrayList<HttpResult.ObjBean> mDataList;
    private BaseAdapter<HttpResult.ObjBean> mAdapter;
    private int currentPage;
    private NewsPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_home, null);
        //view.findViewById(R.id.buju1).setBackgroundColor(getResources().getColor(R.color.huise));

        mPresenter = new NewsPresenter(this);
        ButterKnife.inject(this, view);

        idSwiperefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,
                R.color.green);

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


        initView();
        initEvent();


        return view;
    }

    private void initEvent() {

//        Uri uri = Uri.parse("192.168.2.35:8080/WsbxMobile/page/video/1468896951611.avi");
//        mediaController.setMediaController(new MediaController(getContext()));
//        mediaController.setVideoURI(uri);
//        mediaController.start();
//        mediaController.requestFocus();


        listView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                currentPage++;
                mPresenter.start(currentPage, 1);
            }
        });

    }

    private void initView() {
        mDataList = new ArrayList<>();
        mAdapter = new BaseAdapter<HttpResult.ObjBean>(getContext(), R.layout.item_new, mDataList, listView) {
            @Override
            public void convert(ViewHolder holder, final HttpResult.ObjBean newslistEntity) {
                holder.setText(R.id.tv_title, newslistEntity.getNBD_TITLE());
                holder.setText(R.id.tv_time, newslistEntity.getTIME());
                holder.setText(R.id.tv_content, newslistEntity.getSUBSTR());
                holder.setImageWithUrl(R.id.iv_picasso, newslistEntity.getNBD_PICTURE_URL());
                holder.setOnClickListener(R.id.start_time_repairs, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(NewActivity.this, TwoActivity.class);
//                        startActivity(intent);
                    }
                });
            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layoutManager);
        listView.addItemDecoration(new RecycleViewDivider(getContext()));
        listView.setAdapter(mAdapter);
        initdata();


    }

    private void initdata() {

        currentPage = 1;
        mPresenter.start(currentPage, 1);
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
                startActivity(new Intent(getActivity(), NewActivity.class));
            }
        });
        tvTabHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewActivity.class));
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
        Log.e("11111111", "" + offset);
        int avg = (int) (screenW / num);
        position_one = avg + offset;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showInfo(HttpResult entity) {


        if (currentPage == 1) {
            mDataList.clear();
        }
        mDataList.addAll(entity.getObj());
        mAdapter.notifyDataSetChanged();
        idSwiperefresh.setRefreshing(false);
        listView.loadComplete();


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
    }


    @Override
    public void onResume() {
        super.onResume();

        idSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                mPresenter.start(currentPage, 0);

            }
        });

    }
}
