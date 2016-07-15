package cn.com.hz_project.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.projectdemos.R;

/**
 * ==================================
 * Created by wangl on 2016/7/12.时间14:21
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class TestActivity extends Activity {
    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result_TV)
    TextView resultTV;
    private Button bt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);
        bt = (Button) findViewById(R.id.zxing);
//     bt.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//             Toast.makeText(getApplicationContext(), "Lambda", Toast.LENGTH_LONG).show()
//         }
//     });

        bt.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Lambda", Toast.LENGTH_LONG).show());
    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {
//        getMovie();
    }

    //进行网络请求
    private void getMovie() {


    }
}