package cn.com.projectdemos;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import cn.com.projectdemos.widget.LoadMoreRecyclerView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by wee on 16/8/1.
 */
public class TestActivity extends Activity {
    private LoadMoreRecyclerView loadmorerecycle;
    private PtrFrameLayout storeHouseTjPtrFrame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        loadmorerecycle = (LoadMoreRecyclerView) findViewById(R.id.loadmorerecycle);
        storeHouseTjPtrFrame = (PtrFrameLayout) findViewById(R.id.store_house_tj_ptr_frame);


        init();


    }

    public void init() {


        StoreHouseHeader header = new StoreHouseHeader(TestActivity.this);
        header.setPadding(0, 40, 0, 40);

        //设置头文
        header.initWithString("XVAN SINCE");
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
                Log.e("sh刷新开始", "onUIRefreshBegin");

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {
                Log.e("刷新结束", "onUIRefreshComplete");

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });


        loadmorerecycle.setHasFixedSize(true);
        loadmorerecycle.setLayoutManager(new LinearLayoutManager(this));
//－－－－－－－－－－－－－－－－－
        /**
         * 这里设置adapter内容
         */
//        mSearchResultRecyclerViewAdapter = new SearchResultRecyclerViewAdapter(this, mTjList);
//        loadmorerecycle.setAdapter(mSearchResultRecyclerViewAdapter);
// －－－－－－－－－－－－－－－－－


        loadmorerecycle.setAutoLoadMoreEnable(true);

        loadmorerecycle.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 0) {
                    storeHouseTjPtrFrame.setPullToRefresh(false);
                } else {
                    storeHouseTjPtrFrame.setPullToRefresh(false);
                }
                super.onScrollStateChanged(recyclerView, newState);


            }
        });


        //下拉到底部的时候请求接口
        loadmorerecycle.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadmorerecycle.postDelayed(new Runnable() {
                    @Override
                    public void run() {

//                        mIsStart = false;
//                        page++;
//                        getData(mSearchKeyword.getText().toString());

                    }
                }, 1000);
            }
        });


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


}
