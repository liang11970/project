package cn.com.hz_project.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.hz_project.model.bean.NewsContext;
import cn.com.hz_project.presenter.activityPresenter.NewContext;
import cn.com.hz_project.presenter.activityPresenter.NewPresenter;
import cn.com.projectdemos.R;

public class NewContentActivity extends AppCompatActivity implements NewContext.View {


    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.title_new)
    RelativeLayout titleNew;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.ll_title)
    LinearLayout llTitle;
    @InjectView(R.id.tv_num)
    TextView tvNum;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.start_time_repairs)
    LinearLayout startTimeRepairs;
    @InjectView(R.id.lv_image)
    ImageView lvImage;
    @InjectView(R.id.tv_context)
    TextView tvContext;
    @InjectView(R.id.context)
    LinearLayout context;
    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    private NewPresenter mPresenter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        this.mContext=this;
        ButterKnife.inject(this);
        mPresenter = new NewPresenter(this);
        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");
        Logger.e(id + "");
        initData(id);




    }

    private void initData(int id) {

        mPresenter.start(id, 0);


    }

    @Override
    public void showInfo(NewsContext entity) {

        Logger.e(entity.getObj().get(0).getNBD_CONTEXT());
        Logger.e(entity.getObj().get(0).getNBD_READNUMBER()+"");
        Logger.e(entity.getObj().get(0).getTIME());
        Logger.e(entity.getObj().get(0).getNBD_CONTEXT());
        Logger.e(entity.getObj().get(0).getNBD_PICTURE_URL());

        tvTitle.setText(entity.getObj().get(0).getNBD_TITLE());
        tvNum.setText(entity.getObj().get(0).getNBD_READNUMBER()+"");
        tvTime.setText(entity.getObj().get(0).getTIME());
        tvContext.setText(entity.getObj().get(0).getNBD_CONTEXT());
        Picasso.with(mContext).load(entity.getObj().get(0).getNBD_PICTURE_URL()).resize(1250,500)
                .centerCrop().into(lvImage);



    }


    @OnClick(R.id.iv_back_meeting)
    public void onClick() {
        this.finish();
    }
}
