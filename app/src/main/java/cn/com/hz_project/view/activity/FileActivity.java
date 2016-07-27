package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebView;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.view.base.BaseActivity;
import cn.com.projectdemos.R;

public class FileActivity extends Activity {

    @InjectView(R.id.web)
    WebView web;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        ButterKnife.inject(this);
        Logger.e("FileActivity-onCreate");

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");

        getWebview(id);





    }



    private void getWebview(int id) {

        web.getSettings().setJavaScriptEnabled(true);
        String ne="http://192.168.2.240:8080/WsbxMobile/newscontext.jsp?id="+id;
        Logger.e(ne);

        web.loadUrl(ne);

    }


}












