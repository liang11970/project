package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.StaffBean;
import cn.com.hz_project.view.adapter.StaffAdapter;
import cn.com.projectdemos.R;

public class MeetingStaffListingActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.lv_staff_list)
    ListView lvStaffList;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    private List<StaffBean.ObjBean> staffdata;
    private StaffAdapter staffAdapter;
    private StaffBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_listing);
        ButterKnife.inject(this);

        initView();
        initData();
    }

    private void initData() {
        Bundle extras = getIntent().getExtras();
        bean = (StaffBean) extras.get("staffData");
        showStaff();
    }

    private void showStaff() {
        staffAdapter = new StaffAdapter(getApplicationContext(),bean.getObj());
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
