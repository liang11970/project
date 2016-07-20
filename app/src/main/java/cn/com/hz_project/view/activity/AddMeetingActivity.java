package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.projectdemos.R;

public class AddMeetingActivity extends Activity {

    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.ed_meetingContent)
    EditText edMeetingContent;
    @InjectView(R.id.bt_meeting_signin)
    Button btMeetingSignin;

    SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        ButterKnife.inject(this);
    }
}
