package cn.com.hz_project.view.activity;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

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

public class AddMeetingActivity extends FragmentActivity implements View.OnClickListener, OnDateSetListener {


    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.et_meetingName)
    EditText etMeetingName;
    @InjectView(R.id.et_sTime)
    EditText etSTime;
    @InjectView(R.id.ll_stime)
    LinearLayout llStime;
    @InjectView(R.id.et_etime)
    EditText etEtime;
    @InjectView(R.id.ll_etime)
    RelativeLayout llEtime;
    @InjectView(R.id.ed_meetingContent)
    EditText edMeetingContent;
    @InjectView(R.id.bt_add_meeting)
    Button btaddmeeeting;
    private String meetingName;
    private String sTime;
    private String eTime;
    private String content;
    private MeetingService meetingService;
    private boolean isStime;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private TimePickerDialog timeSelector;
    private Date dateStime;
    private Date dateEtime;

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

        Log.e("添加会议", "meetingName=" + meetingName + "content=" + content + "eTime=" + eTime + "sTime=" + sTime);


    }

    private void initview() {
        btaddmeeeting.setOnClickListener(this);
        ivBackMeeting.setOnClickListener(this);
        llEtime.setOnClickListener(this);
        llStime.setOnClickListener(this);
        tvBack.setOnClickListener(this);

        etSTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isStime = true;
                        showTimeSelector("请选择会议开始时间");
                        timeSelector.show(getSupportFragmentManager(), "dddd");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }

                return false;
            }
        });
        etEtime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isStime = false;
                        showTimeSelector("请选择会议结束时间");
                        timeSelector.show(getSupportFragmentManager(), "dddd");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }

                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_meeting:
                initData();

                /**非空判断*/
                if (meetingName.length() == 0 || content.length() == 0 || eTime.length() == 0 || sTime.length() == 0) {
                    ToastUtils.show(getApplicationContext(), "请将会议信息填写完整");
                    return;
                }

                /**时间判断*/
                if (dateStime != null && dateEtime != null) {

                    if (dateStime.compareTo(dateEtime) > 0) {

                        ToastUtils.show(getApplicationContext(), "会议结束时间早于开始时间,请重新选择时间");
                        return;
                    }
                }

                submitRequest();
                break;
            case R.id.iv_back_meeting:
                finish();
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }

    private void showTimeSelector(String qin) {
        timeSelector = new TimePickerDialog.Builder()
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId(qin)
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setType(Type.ALL)
                .setWheelItemTextSize(15)
                .setCallBack(this)
                .build();


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
        String time = getTime(millseconds);


        if (isStime) {
            if (dateStime != null && dateEtime != null) {

                if (dateStime.compareTo(dateEtime) > 0) {

                    ToastUtils.show(getApplicationContext(), "会议结束时间早于开始时间,请重新选择时间");
                }
            }


            etSTime.setText(time);
        } else {
            if (dateStime != null && dateEtime != null) {

                if (dateStime.compareTo(dateEtime) > 0) {

                    ToastUtils.show(getApplicationContext(), "会议结束时间早于开始时间,请重新选择时间");
                }
            }

            etEtime.setText(time);
        }


    }

    public String getTime(long millseconds) {
        Date d = new Date(millseconds);
        if (isStime) {
            dateStime = d;
        } else {
            dateEtime = d;
        }
        String format = simpleDateFormat.format(d);

        return format;
    }
}
