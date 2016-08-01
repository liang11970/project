package cn.com.hz_project.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.presenter.activityPresenter.NewsContract;
import cn.com.hz_project.presenter.activityPresenter.NewsPresenter;
import cn.com.hz_project.tools.utils.LogUtils;
import cn.com.hz_project.view.base.BaseActivity;
import cn.com.hz_project.view.base.BaseAdapter;
import cn.com.hz_project.view.base.ViewHolder;
import cn.com.hz_project.view.widget.LoadMorRecyclerView;
import cn.com.hz_project.view.widget.RecycleViewDivider;
import cn.com.projectdemos.R;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class NewActivity extends BaseActivity implements NewsContract.View {


    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.title_new)
    RelativeLayout titleNew;
    @InjectView(R.id.listView)
    LoadMorRecyclerView listView;
    @InjectView(R.id.store_house_tj_ptr_frame)
    PtrFrameLayout storeHouseTjPtrFrame;
    //    @InjectView(R.id.id_swiperefresh)
//    SwipeRefreshLayout idSwiperefresh;
    private Context mContext;
    private BaseAdapter<HttpResult.ObjBean> mAdapter;
    private NewsPresenter mPresenter;
    private ArrayList<HttpResult.ObjBean> mDataList;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.inject(this);
        mContext = this;
        mPresenter = new NewsPresenter(this);

//        idSwiperefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,
//                R.color.green);






        initView();
        initEvent();

    }

    @Override
    protected void initViewsAndEvents() {

    }

    private void initView() {

        //----------------------------------------------------------------

        StoreHouseHeader header = new StoreHouseHeader(NewActivity.this);
        header.setPadding(0, 40, 0, 40);

        //设置头文
        header.initWithString("now");
        header.setTextColor(R.color.colorPrimary);
        storeHouseTjPtrFrame.disableWhenHorizontalMove(true);
        storeHouseTjPtrFrame.setHeaderView(header);
        storeHouseTjPtrFrame.addPtrUIHandler(header);
        storeHouseTjPtrFrame.setResistance(1.7f);
        storeHouseTjPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        storeHouseTjPtrFrame.setDurationToClose(200);
        storeHouseTjPtrFrame.setDurationToCloseHeader(600);

        storeHouseTjPtrFrame.setPullToRefresh(true);
        storeHouseTjPtrFrame.setKeepHeaderWhenRefresh(true);


        storeHouseTjPtrFrame.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {


            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {
                currentPage = 1;
                mPresenter.start(currentPage,0);

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {
                Log.e("刷新结束", "onUIRefreshComplete");

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });


        //-------------------------------------------------------------------------------------------------------


        mDataList = new ArrayList<>();
        mAdapter = new BaseAdapter<HttpResult.ObjBean>(mContext, R.layout.item_new, mDataList, listView) {
            @Override
            public void convert(ViewHolder holder, final HttpResult.ObjBean newslistEntity) {

//                                           holder.setText(R.id.tv_title,newslistEntity.getNBD_TITLE());
                holder.setText(R.id.tv_time, newslistEntity.getTIME());
//                                           holder.setText(R.id.tv_content,newslistEntity.getSUBSTR());
                holder.setText(R.id.tv_content, newslistEntity.getNBD_TITLE());
                holder.setImageWithUrl(R.id.iv_picasso, newslistEntity.getNBD_PICTURE_URL());
                holder.setOnClickListener(R.id.start_time_repairs, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(NewActivity.this, NewContentActivity.class);
                        intent.putExtra("id", newslistEntity.getNBD_ID());
//                                                   Logger.e(newslistEntity.getNBD_ID()+"");
                        Logger.e(newslistEntity.getNBD_ID() + "");
                        startActivity(intent);
                    }
                });
            }
        };

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        listView.setLayoutManager(layoutManager);
        listView.addItemDecoration(new RecycleViewDivider(this));
        listView.setAdapter(mAdapter);
        intitdata();



        storeHouseTjPtrFrame.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                storeHouseTjPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        storeHouseTjPtrFrame.refreshComplete();
                    }
                }, 500);
            }
        });

        storeHouseTjPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                storeHouseTjPtrFrame.autoRefresh(false);
            }
        }, 100);


    }

    private void intitdata() {

        currentPage = 1;
        mPresenter.start(currentPage, 0);
    }

    private void initEvent() {
        listView.setLoadMoreListener(new LoadMorRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                currentPage++;
                mPresenter.start(currentPage, 0);
            }
        });
    }

    @Override
    public void showInfo(HttpResult entity) {

Logger.e(entity.getObj().size()+"条数据");

        if (entity.getObj().size() == 0) {

//            listView.removeView();

            listView.notifyMoreFinish();
            listView.sethide();
            listView.loadComplete();
            mAdapter.notifyDataSetChanged();
        } else {
            if (currentPage == 1) {
                mDataList.clear();
            }
            mDataList.addAll(entity.getObj());
            mAdapter.notifyDataSetChanged();
//            idSwiperefresh.setRefreshing(false);
        }


    }


    //--------------------------------------------------
    @OnClick(R.id.iv_back_meeting)
    public void onClick() {
        this.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        idSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                currentPage = 1;
//                mPresenter.start(currentPage,0);
//
//            }
//        });

    }

}
