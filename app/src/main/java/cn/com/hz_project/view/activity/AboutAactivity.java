package cn.com.hz_project.view.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.hz_project.view.base.BaseActivity;
import cn.com.projectdemos.R;

public class AboutAactivity extends BaseActivity {

    @InjectView(R.id.back)
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_aactivity);
        ButterKnife.inject(this);
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick(R.id.back)
    public void onClick() {
        this.finish();
        System.gc();
    }
}
