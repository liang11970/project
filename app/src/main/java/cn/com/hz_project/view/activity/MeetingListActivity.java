package cn.com.hz_project.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.MeetingBean;
import cn.com.hz_project.model.server.MeetingService;
import cn.com.hz_project.view.adapter.MeetingListAdapter;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeetingListActivity extends AppCompatActivity implements View.OnClickListener {


    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.lv_meeting)
    ListView lvMeeting;
    private MeetingService meetingService;
    private int pageNum = 1;
    private List<MeetingBean.ObjBean> meetList;
    private MeetingListAdapter meetingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.22:8080/WsbxMobile/appCtrl/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        meetingService = retrofit.create(MeetingService.class);


        meetingService.getMeetData(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MeetingBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("请求失败", e.getMessage());
                        Toast.makeText(getApplicationContext(), "请求网络失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(MeetingBean meetingBean) {
                        Log.e("请求成功++", meetingBean.toString());
                        meetList = meetingBean.getObj();
                        showData();


                    }
                });



    }

    private void showData() {
        meetingListAdapter = new MeetingListAdapter(this, meetList);
        lvMeeting.setAdapter(meetingListAdapter);
    }

    private void initView() {
        ivBackMeeting.setOnClickListener(this);
        tvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
