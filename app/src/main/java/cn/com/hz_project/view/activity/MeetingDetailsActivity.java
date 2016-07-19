package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.projectdemos.R;

public class MeetingDetailsActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.tv_Meeting_tv)
    TextView tvMeetingTv;
    @InjectView(R.id.rl_meeting_personnelList)
    RelativeLayout rlMeetingPersonnelList;
    @InjectView(R.id.tv_Meeting_Name)
    TextView tvMeetingName;
    @InjectView(R.id.tv_Meeting_Time)
    TextView tvMeetingTime;
    @InjectView(R.id.bt_meeting_signin)
    Button btMeetingSignin;
    @InjectView(R.id.tv_Meeting_content)
    TextView tvMeetingContent;
    @InjectView(R.id.et_phoneNum)
    EditText etPhoneNum;
    private String meetingId;
    private PreferencesService preferencesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        ButterKnife.inject(this);

        initView();
        initData();

    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        meetingId = (String) extras.get("ID");
        tvMeetingName.setText(extras.get("name") + "");
        tvMeetingTime.setText("会议时间:" + extras.get("startTime") + "至" + extras.get("endTime"));
        tvMeetingContent.setText(extras.get("content") + "");

    }

    private void initView() {
        preferencesService = new PreferencesService(getApplicationContext());
        if (preferencesService.getPerferences().get("roleId").equals("9")) {

            etPhoneNum.setVisibility(View.VISIBLE);
            rlMeetingPersonnelList.setVisibility(View.VISIBLE);
        }else {
            etPhoneNum.setVisibility(View.INVISIBLE);
            rlMeetingPersonnelList.setVisibility(View.INVISIBLE);


        }
        Log.e("会议签到role", preferencesService.getPerferences().get("role") + ""
        );
        Log.e("会议签到roleid", preferencesService.getPerferences().get("roleId") + ""
        );
        rlMeetingPersonnelList.setOnClickListener(this);
        btMeetingSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_meeting_personnelList:
                startActivity(new Intent(getApplicationContext(), MeetingStaffPieActivity.class));
                break;
            case R.id.bt_meeting_signin:
                break;
        }
    }
}
