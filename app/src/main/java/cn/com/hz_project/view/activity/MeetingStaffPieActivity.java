package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.StaffBean;
import cn.com.hz_project.model.server.MeetingService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeetingStaffPieActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.bt_staff_listing)
    Button btStaffListing;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    private MeetingService meetingService;
    private String meetingID;
    private StaffBean staffdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_pie);
        ButterKnife.inject(this);

        initview();
        initData();


    }

    private void initview() {
        ivBackMeeting.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        btStaffListing.setOnClickListener(this);
    }

    private void initData() {
        meetingID = (String) getIntent().getExtras().get("meetingID");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        meetingService = retrofit.create(MeetingService.class);

        meetingService.getStaffData(meetingID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StaffBean>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(getApplicationContext(), "请求数据失败,请检查网络");

                    }

                    @Override
                    public void onNext(StaffBean staffBean) {
                        Log.e("饼图",staffBean.getObj().toString());
                        staffdata = staffBean;


                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_meeting:
                finish();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.bt_staff_listing:
                Intent intent = new Intent(this, MeetingStaffListingActivity.class);
                intent.putExtra("staffData", (Serializable) staffdata);
                startActivity(intent);

        }
    }
}
