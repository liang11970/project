package cn.com.hz_project.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.hz_project.model.bean.VideoResult;
import cn.com.hz_project.presenter.activityPresenter.NewsContract;
import cn.com.hz_project.presenter.activityPresenter.NewsPresenter;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.view.base.BaseActivity;
import cn.com.hz_project.view.base.BaseAdapter;
import cn.com.hz_project.view.base.ViewHolder;
import cn.com.hz_project.view.widget.LoadMorRecyclerView;
import cn.com.hz_project.view.widget.RecycleViewDivider;
import cn.com.projectdemos.R;
import cn.com.projectdemos.meeting.adapter.SearchResultRecyclerViewAdapter;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class VideoListActivity extends BaseActivity implements NewsContract.Video {


    @InjectView(R.id.listView)
    LoadMorRecyclerView listView;
    @InjectView(R.id.store_house_tj_ptr_frame)
    PtrFrameLayout storeHouseTjPtrFrame;
    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.tv_back)
    TextView tvBack;

    private Context mContext;
    private NewsPresenter mPresenter;
    private List<VideoResult.ObjBean> mDataList;
    private BaseAdapter<VideoResult.ObjBean> mAdapter;
    private SearchResultRecyclerViewAdapter mSearchResultRecyclerViewAdapter;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        ButterKnife.inject(this);
        mContext = this;
        mPresenter = new NewsPresenter(this);

        initView();



    }


    private void initView() {

        StoreHouseHeader header = new StoreHouseHeader(VideoListActivity.this);
        header.setPadding(0, 40, 0, 0);

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
//                currentPage = 1;
//                mPresenter.start(currentPage,2);

                intitdata();
            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {
                Log.e("刷新结束", "onUIRefreshComplete");

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });

        //------------------------------
        mDataList = new ArrayList<>();
        mAdapter = new BaseAdapter<VideoResult.ObjBean>(mContext, R.layout.item_new, mDataList, listView) {
            @Override
            public void convert(ViewHolder holder, final VideoResult.ObjBean newslistEntity) {

//                                           holder.setText(R.id.tv_title,newslistEntity.getNBD_TITLE());
                holder.setText(R.id.tv_time, newslistEntity.getVF_UPDATE_TIME());
//                                           holder.setText(R.id.tv_content,newslistEntity.getSUBSTR());
                holder.setText(R.id.tv_content, newslistEntity.getVF_VIDEO_NAME());
                holder.setImageWithUrl(R.id.iv_picasso, newslistEntity.getVF_IMG_URL());
                holder.setOnClickListener(R.id.start_time_repairs, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VideoListActivity.this, VideoActivity.class);
                        intent.putExtra("url", Urls.touxiang + newslistEntity.getVF_VIDEO_PATH());
                        Logger.e(Urls.touxiang + newslistEntity.getVF_VIDEO_PATH());

                        startActivity(intent);
                    }
                });
            }
        };


        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        listView.setLayoutManager(layoutManager);
        listView.addItemDecoration(new RecycleViewDivider(this));
        listView.setAdapter(mAdapter);

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
        mPresenter.start(currentPage, 2);

    }

    @Override
    protected void initViewsAndEvents() {

    }

    private void initEvent() {


        listView.setLoadMoreListener(new LoadMorRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                currentPage++;
                mPresenter.start(currentPage, 2);
            }
        });
    }


    @Override
    public void showVideo(VideoResult entity) {


        Logger.e(entity.getObj().size() + "很多条");


        if (entity.getObj().size() == 0) {

            storeHouseTjPtrFrame.setPullToRefresh(false);


            mSearchResultRecyclerViewAdapter.notifyDataSetChanged();
        } else {
            if (currentPage == 1) {
                mDataList.clear();
            }
            mDataList.addAll(entity.getObj());
            mSearchResultRecyclerViewAdapter.notifyDataSetChanged();

        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        initEvent();

    }

    @OnClick({R.id.iv_back_meeting, R.id.tv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_meeting:
                this.finish();
                break;
            case R.id.tv_back:
                this.finish();
                break;
        }
    }
}
