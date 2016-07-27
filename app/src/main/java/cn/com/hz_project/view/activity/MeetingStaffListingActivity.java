package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.StaffBean;
import cn.com.hz_project.model.bean.StaffBeanModle;
import cn.com.hz_project.model.server.MeetingService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.hz_project.view.adapter.StaffAdapter;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeetingStaffListingActivity extends Activity implements View.OnClickListener {


    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.lv_staff_list)
    ListView lvStaffList;
    @InjectView(R.id.tv_null)
    TextView tvNull;
    private List<StaffBeanModle.ObjBean> staffdata;
    private StaffAdapter staffAdapter;
    private StaffBeanModle bean;
    private String meetingID;
    private MeetingService meetingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_listing);
        ButterKnife.inject(this);

        initView();
        initData();
    }

    private void initData() {

        meetingID = (String) getIntent().getExtras().get("meetingIDa");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        meetingService = retrofit.create(MeetingService.class);

        meetingService.getStaffData(meetingID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StaffBeanModle>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        tvNull.setVisibility(View.VISIBLE);
                        ToastUtils.show(getApplicationContext(), "请求数据失败,请检查网络");


                    }

                    @Override
                    public void onNext(StaffBeanModle staffBean) {
                        bean = staffBean;
                        Log.e("到场人员列表", staffBean.toString());
                        if (staffBean.getObj().size()<1){
                            tvNull.setVisibility(View.VISIBLE);
                            return;
                        }

                        showStaff();

                    }
                });


    }

    private void showStaff() {
        staffAdapter = new StaffAdapter(getApplicationContext(),bean.getObj()
        );
        lvStaffList.setAdapter(staffAdapter);
    }

    private void initView() {
        ivBackMeeting.setOnClickListener(this);
        tvBack.setOnClickListener(this);
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

        }
    }
}
