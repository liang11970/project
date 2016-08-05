package cn.com.hz_project.view.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.model.bean.VideoResult;
import cn.com.hz_project.presenter.activityPresenter.NewsContract;
import cn.com.hz_project.presenter.activityPresenter.NewsPresenter;
import cn.com.hz_project.view.activity.MeetingSignListActivity;
import cn.com.hz_project.view.activity.NewActivity;
import cn.com.hz_project.view.activity.NewContentActivity;
import cn.com.hz_project.view.activity.NewsListAdapter;
import cn.com.hz_project.view.activity.SecondActivity;
import cn.com.hz_project.view.activity.ViewPagerActivity;
import cn.com.hz_project.view.adapter.AutoLoadOnScrollListener;
import cn.com.hz_project.view.adapter.MainViewPagerAdapter;
import cn.com.hz_project.view.base.BaseAdapter;
import cn.com.hz_project.view.base.ViewHolder;
import cn.com.hz_project.view.widget.CircleIndicator;
import cn.com.hz_project.view.widget.HeaderViewRecyclerAdapter;
import cn.com.hz_project.view.widget.RecycleViewDivider;
import cn.com.projectdemos.R;

public class HomeFragment extends Fragment implements NewsContract.View ,Runnable,NewsContract.Video{

    Resources resources;

    /**
     * 会议签到
     */
    @InjectView(R.id.buju1)
    LinearLayout buju1;

    /**
     * 研发前沿
     */
    @InjectView(R.id.buju2)
    LinearLayout buju2;

    /**
     * 领导架构
     */
    @InjectView(R.id.buju3)
    LinearLayout buju3;

    /**
     * 介绍
     */
    @InjectView(R.id.buju4)
    LinearLayout buju4;

    /**
     * 新闻列表
     */
    @InjectView(R.id.news_recycle)
    RecyclerView newsRecycler;

    /**
     * 布局视图
     */
    private View view;

    /**
     * 新闻列表适配器
     */
    private NewsListAdapter adapter;

    /**
     * 新闻列表数据源
     */
    private ArrayList<HttpResult.ObjBean> mDataList;

    /**
     * 视频列表数据源
     */
    private ArrayList<VideoResult.ObjBean> mTopList;

    /**
     * 新闻页数
     */
    private int currentPage;

    /**
     *
     */
    private NewsPresenter mPresenter;

    /**
     *
     */
    private NewsPresenter presenter;

    /**
     * 头部布局
     */
    private View headerView;

    /**
     * 轮播图VIewPager
     */
    private ViewPager mViewPager;

    /**
     * RecyclerView上拉加载更多接口
     */
    private AutoLoadOnScrollListener mAutoLoadOnScrollListener;

    /**
     *
     */
    private HeaderViewRecyclerAdapter mHeaderViewRecyclerAdapter;

    /**
     * 下拉刷新布局
     */
    @InjectView(R.id.id_swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    /**
     * 是否用户点击
     */
    public boolean mIsUserTouched = false;

    /**
     *轮播图当前页的位置
     */
    private int mPagerPosition = 0;

    /**
     *轮播图条数
     */
    private int size;

    /**
     *
     */
    private CircleIndicator mCircleIndicator;

    /**
     * 轮播图适配器
     */
    private MainViewPagerAdapter mMainViewPagerAdapter;

    /**
     * 轮播图定时器
     */
    private Timer mTimer;

    /**
     *
     */
    private BannerTask mTimerTask;

    /**
     *
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            if (msg.what == 0) {
//                getLatesDailys(true);
            } else if (msg.what == 1) {
//                hideProgress();
                mSwipeRefreshLayout.setRefreshing(false);
                finishGetDaily();
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view = inflater.inflate(R.layout.fragment_home, null);

        ButterKnife.inject(this, view);

        initView(view, headerView);
        initEvent();
        initListener();

        return view;
    }

    /**
     * 初始化
     */
    private void initView(View view, View headerView) {
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.recycle_head_layout, null);

        mViewPager = (ViewPager) headerView.findViewById(R.id.main_view_pager);

        mDataList = new ArrayList<>();

        mPresenter = new NewsPresenter(this,false);
        presenter = new NewsPresenter(this,1);

        resources = getResources();

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,
                R.color.green);

        adapter = new NewsListAdapter(getActivity(), mDataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        newsRecycler.setHasFixedSize(true);
        newsRecycler.setLayoutManager(layoutManager);
        newsRecycler.addItemDecoration(new RecycleViewDivider(getContext()));

        /*
        上拉加载后续页数数据
         */
        mAutoLoadOnScrollListener = new AutoLoadOnScrollListener(layoutManager) {

            @Override
            public void onLoadMore(int currentPage) {
                Toast.makeText(getActivity(),"加载第"+currentPage+"页",Toast.LENGTH_SHORT).show();
                mPresenter.start(currentPage, 1);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);
                //int firstPos = (recyclerView == null || recyclerView.getChildCount() == 0 ? 0 : recyclerView.getChildAt(0).getTop());

                mSwipeRefreshLayout.setEnabled(layoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        };

        newsRecycler.addOnScrollListener(mAutoLoadOnScrollListener);

        mHeaderViewRecyclerAdapter = new HeaderViewRecyclerAdapter(adapter);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.recycle_head_layout, newsRecycler, false);

        mViewPager = (ViewPager) headView.findViewById(R.id.main_view_pager);
        mCircleIndicator = (CircleIndicator) headView.findViewById(R.id.pager_indicator);

        mViewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
                    mIsUserTouched = true;
                    mSwipeRefreshLayout.setEnabled(false);
                } else if (action == MotionEvent.ACTION_UP) {
                    mIsUserTouched = false;
                } else if (action == MotionEvent.ACTION_CANCEL) {
                    mSwipeRefreshLayout.setEnabled(true);
                }
                return false;
            }
        });
        mHeaderViewRecyclerAdapter.addHeaderView(headView);

        newsRecycler.setAdapter(adapter);

        initdata();
    }

    /**
     *第一次请求初始化数据
     */
    private void initdata() {
        currentPage = 1;
        mPresenter.start(currentPage, 1);
        presenter.start(3,4);
    }

    /**
     *
     */
    private void initEvent() {

    }

    /**
     *监听事件
     */
    private void initListener() {

        /*
        导航菜单点击事件
         */
        buju1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MeetingSignListActivity.class));
            }
        });

        buju2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewActivity.class));
            }
        });

        buju3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SecondActivity.class));
            }
        });

        buju4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SecondActivity.class));
            }
        });

        /*
        下拉刷新
         */
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.start(1, 1);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     *
     */
    private void finishGetDaily() {
        newsRecycler.setAdapter(mHeaderViewRecyclerAdapter);
//        mRefreshBtn.setVisibility(View.VISIBLE);
        startViewPagerRun();
    }

    /**
     * 新闻请求成功获取数据的回调方法
     */
    @Override
    public void showInfo(HttpResult entity) {

        if (currentPage == 1) {
            mDataList.clear();
        }
        mDataList.addAll(entity.getObj());
        adapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void run() {

    }

    /**
     * 执行轮播
     */
    public void startViewPagerRun() {
        mTimer = new Timer();
        mTimerTask = new BannerTask();
        mTimer.schedule(mTimerTask, 10000, 10000);
    }

    /**
     * 视频请求成功获取数据的回调方法
     */
    @Override
    public void showVideo(VideoResult entity) {
        Logger.e("showVideo"+entity.toString());
        mTopList = (ArrayList<VideoResult.ObjBean>) entity.getObj();

        mMainViewPagerAdapter = new MainViewPagerAdapter(getActivity(),mTopList);
        mViewPager.setAdapter(mMainViewPagerAdapter);
        mCircleIndicator.setViewPager(mViewPager);
        size = mTopList.size();
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    private class BannerTask extends TimerTask {
        @Override
        public void run() {
            if (!mIsUserTouched) {
                mPagerPosition = (mPagerPosition + 1) % size;
                if (getActivity() != null) {
                    getActivity().runOnUiThread(HomeFragment.this);
                }
            }
        }
    }
}