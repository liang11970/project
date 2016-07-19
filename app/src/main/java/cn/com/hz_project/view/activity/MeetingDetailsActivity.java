package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.projectdemos.R;

public class MeetingDetailsActivity extends Activity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        ButterKnife.inject(this);

        initView();

    }

    private void initView() {

    }
}
