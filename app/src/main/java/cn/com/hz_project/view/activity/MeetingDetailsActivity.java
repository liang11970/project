package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
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
    private int meetingId;

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
        meetingId = (int) extras.get("ID");
        tvMeetingName.setText(extras.get("name") + "");
        tvMeetingTime.setText("会议时间:" + extras.get("startTime") + "至" + extras.get("endTime"));
        tvMeetingContent.setText(extras.get("content")+"");

    }

    private void initView() {
        rlMeetingPersonnelList.setOnClickListener(this);
        btMeetingSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_meeting_personnelList:
                break;
            case R.id.bt_meeting_signin:
                break;
        }
    }
}
