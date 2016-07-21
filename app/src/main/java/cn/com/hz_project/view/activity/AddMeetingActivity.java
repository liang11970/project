package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.MeetingAddBean;
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

public class AddMeetingActivity extends Activity implements View.OnClickListener, OnDateSetListener {

    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.ed_meetingContent)
    EditText edMeetingContent;
    @InjectView(R.id.bt_add_meeting)
    Button btaddmeeeting;
    @InjectView(R.id.et_meetingName)
    EditText etMeetingName;
    @InjectView(R.id.et_sTime)
    EditText etSTime;
    @InjectView(R.id.et_etime)
    EditText etEtime;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @InjectView(R.id.ll_stime)
    LinearLayout llStime;
    @InjectView(R.id.ll_etime)
    LinearLayout llEtime;
    private String meetingName;
    private String sTime;
    private String eTime;
    private String content;
    private MeetingService meetingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.inject(this);


        initview();
    }

    private void initData() {
        meetingName = etMeetingName.getText().toString();
        sTime = etSTime.getText().toString();
        eTime = etSTime.getText().toString();
        content = edMeetingContent.getText().toString();


    }

    private void initview() {
        btaddmeeeting.setOnClickListener(this);
        ivBackMeeting.setOnClickListener(this);
        llEtime.setOnClickListener(this);
        llStime.setOnClickListener(this);
        tvBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_meeting:
                initData();
                if (meetingName == null || content == null || eTime == null || sTime == null) {
                    ToastUtils.show(getApplicationContext(), "请将会议信息填写完整");
                    return;
                }
                submitRequest();
                break;
            case R.id.iv_back_meeting:
                finish();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.ll_etime:
                showTimeSelector();



        }
    }

    private void showTimeSelector() {
    }

    private void submitRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        meetingService = retrofit.create(MeetingService.class);

        meetingService.getMeetingAdd(meetingName, sTime, eTime, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MeetingAddBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(AddMeetingActivity.this, "提交失败");

                    }

                    @Override
                    public void onNext(MeetingAddBean meetingAddBean) {
                        ToastUtils.show(AddMeetingActivity.this, "提交成功");
                        etMeetingName.setText("");
                        edMeetingContent.setText("");
                        etSTime.setText("");
                        etEtime.setText("");


                    }
                });


    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {

    }
}
