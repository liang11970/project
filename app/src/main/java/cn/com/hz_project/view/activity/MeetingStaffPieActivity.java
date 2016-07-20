package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.projectdemos.R;

public class MeetingStaffPieActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.bt_staff_listing)
    Button btStaffListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_pie);
        ButterKnife.inject(this);


    }

    public void clickToPie(View view) {
        startActivity(new Intent(getApplicationContext(), MeetingStaffListingActivity.class));
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
