package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.view.base.BaseActivity;
import cn.com.projectdemos.R;

public class FileActivity extends Activity {
    private WebView mWebView;
    private String ne;
//
//    @InjectView(R.id.web)
//    WebView web;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
//        ButterKnife.inject(this);
        Logger.e("FileActivity-onCreate");

        Bundle extras = getIntent().getExtras();
        int id = extras.getInt("id");

//        getWebview(id);

         ne="http://116.228.202.122:8080/WsbxMobile/newscontext.jsp?id="+id;

         mWebView = new WebView(this.getApplicationContext());
        LinearLayout mll  =(LinearLayout)findViewById(R.id.lll);
        mll.addView(mWebView);

        mWebView.getSettings().setJavaScriptEnabled(true);



    }


    @Override
    protected void onResume() {
        super.onResume();

        mWebView.loadUrl(ne);

    }

    private void getWebview(int id) {

//        web.getSettings().setJavaScriptEnabled(true);
//        String ne="http://116.228.202.122:8080/WsbxMobile/newscontext.jsp?id="+id;
//        Logger.e(ne);
//
//        web.loadUrl(ne);

    }


}












