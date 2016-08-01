package cn.com.hz_project.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.model.bean.VideoResult;
import cn.com.hz_project.presenter.activityPresenter.NewsContract;
import cn.com.hz_project.presenter.activityPresenter.NewsPresenter;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.LogUtils;
import cn.com.hz_project.view.base.BaseActivity;
import cn.com.hz_project.view.base.BaseAdapter;
import cn.com.hz_project.view.base.ViewHolder;
import cn.com.hz_project.view.widget.LoadMorRecyclerView;
import cn.com.hz_project.view.widget.RecycleViewDivider;
import cn.com.projectdemos.R;

public class VideoListActivity extends BaseActivity  implements NewsContract.Video  {

    @InjectView(R.id.listView)
    LoadMorRecyclerView listView;
    private Context mContext;
    private NewsPresenter mPresenter;
    private ArrayList<VideoResult.ObjBean> mDataList;
    private BaseAdapter<VideoResult.ObjBean> mAdapter;
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
        mDataList = new ArrayList<>();
        mAdapter = new BaseAdapter<VideoResult.ObjBean>(mContext,R.layout.item_new,mDataList,listView) {
            @Override
            public void convert(ViewHolder holder, final VideoResult.ObjBean newslistEntity) {

//                                           holder.setText(R.id.tv_title,newslistEntity.getNBD_TITLE());
                holder.setText(R.id.tv_time,newslistEntity.getVF_UPDATE_TIME());
//                                           holder.setText(R.id.tv_content,newslistEntity.getSUBSTR());
                holder.setText(R.id.tv_content,newslistEntity.getVF_VIDEO_NAME());
//                holder.setImageWithUrl(R.id.iv_picasso,newslistEntity.getNBD_PICTURE_URL());
                holder.setOnClickListener(R.id.start_time_repairs, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VideoListActivity.this,VideoActivity.class);
                        intent.putExtra("url", Urls.touxiang+newslistEntity.getVF_VIDEO_PATH());
//                                                   Logger.e(newslistEntity.getNBD_ID()+"");
                        com.orhanobut.logger.Logger.e(Urls.touxiang+newslistEntity.getVF_VIDEO_PATH());


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
        mPresenter.start(currentPage,2);

    }

    @Override
    protected void initViewsAndEvents() {

    }



    @Override
    public void showVideo(VideoResult entity) {

        LogUtils.e("log",entity.getObj().size()+"");

        if(entity.getObj().size()==0){

            mAdapter.notifyDataSetChanged();
        }else {
            if(currentPage==1){
                mDataList.clear();
            }
            mDataList.addAll(entity.getObj());
            mAdapter.notifyDataSetChanged();
        }

    }
}
