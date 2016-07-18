package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.model.server.NewServer;
import cn.com.hz_project.presenter.activityPresenter.NewsContract;
import cn.com.hz_project.presenter.activityPresenter.NewsPresenter;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.LogUtils;
import cn.com.hz_project.view.adapter.NewListViewAdapter;
import cn.com.hz_project.view.base.BaseAdapter;
import cn.com.hz_project.view.base.ViewHolder;
import cn.com.hz_project.view.widget.LoadListView;
import cn.com.hz_project.view.widget.LoadMoreRecyclerView;
import cn.com.hz_project.view.widget.RecycleViewDivider;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewActivity extends Activity implements NewsContract.View {


    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.title_new)
    RelativeLayout titleNew;
    @InjectView(R.id.listView)
    LoadMoreRecyclerView listView;
    @InjectView(R.id.id_swiperefresh)
    SwipeRefreshLayout idSwiperefresh;
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

        idSwiperefresh.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,
                R.color.green);

        initView();
        initEvent();

    }

    private void initView() {
        mDataList = new ArrayList<>();
        mAdapter = new BaseAdapter<HttpResult.ObjBean>(mContext,R.layout.item_new,mDataList,listView) {
                                       @Override
                                       public void convert(ViewHolder holder, final HttpResult.ObjBean newslistEntity) {
                                           holder.setText(R.id.tv_title,newslistEntity.getNBD_TITLE());
                                           holder.setText(R.id.tv_time,newslistEntity.getTIME());
                                           holder.setText(R.id.tv_content,newslistEntity.getSUBSTR());
                                           holder.setImageWithUrl(R.id.iv_picasso,newslistEntity.getNBD_PICTURE_URL());
                                           holder.setOnClickListener(R.id.start_time_repairs, new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                        Intent intent = new Intent(NewActivity.this,TwoActivity.class);
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
    }

    private void intitdata() {

         currentPage = 1;
        mPresenter.start(currentPage,0);
    }

    private void initEvent() {
        listView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                currentPage++;
                mPresenter.start(currentPage,0);
            }
        });
    }

    @Override
    public void showInfo(HttpResult entity) {

        LogUtils.e("log",entity.getObj().size()+"");

        if(entity.getObj().size()==0){
            Toast.makeText(NewActivity.this,"没有数据了",Toast.LENGTH_LONG);
            mAdapter.notifyDataSetChanged();
        }else {
            if(currentPage==1){
                mDataList.clear();
            }
            mDataList.addAll(entity.getObj());
            mAdapter.notifyDataSetChanged();
            idSwiperefresh.setRefreshing(false);
            listView.loadComplete();
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
        idSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                mPresenter.start(currentPage,0);

            }
        });

    }

}
