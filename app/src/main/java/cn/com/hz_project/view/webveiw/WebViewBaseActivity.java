package cn.com.hz_project.view.webveiw;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import cn.com.projectdemos.R;


/**
 *
 * TODO:加载WebView的Activity统一专用
 *
 * @author Leo [2015年1月8日.下午2:07:44]
 */
@SuppressLint("ValidFragment")
public class WebViewBaseActivity extends Activity {
    private Context mContext;
    WebView wv_about;
    public Handler mHandler = new Handler();
    RelativeLayout re_back;
    LinearLayout lin_refresh, lin_loading, lin_head_update;
    String cacheDirPath = "";
    RelativeLayout rel_root;
    boolean falg=true;
    Handler mHandler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            ToastUtil.ToastMsgShort(mContext, Connect.NO_NETWORKS_FOUND);
        }
    };
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_newcontent);


        mContext = WebViewBaseActivity.this;
        wv_about = (WebView) findViewById(R.id.web_ablout);
        wv_about.getSettings().setJavaScriptEnabled(true);
        wv_about.getSettings().setDefaultTextEncodingName("UTF-8"); // 设置默认的显示编码
        // // 把图片加载放在最后来加载渲染
        // wv_about.getSettings().setBlockNetworkImage(true);
        wv_about.getSettings().setRenderPriority(RenderPriority.HIGH);
//         ，建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT:cache-control决定是否从网络上取数据。
//         无网络时，使用LOAD_CACHE_ELSE_NETWORK:只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
//         if (NetworkConnectivity.isConnect(this)) {
//         wv_about.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);//
////         设置缓存模式 
//         } else
         wv_about.getSettings()
         .setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);// 设置缓存模式 
         // 开启 DOM storage API 功能 
         wv_about.getSettings().setDomStorageEnabled(true);
         // 开启 database storage API 功能 
         wv_about.getSettings().setDatabaseEnabled(true);
//         cacheDirPath = getFilesDir().getAbsolutePath() + "webview"+information.getDetailUrl();
         cacheDirPath = getFilesDir().getAbsolutePath();
         Log.i("yy", "cacheDirPath=" + cacheDirPath);
         // //设置数据库缓存路径 
         wv_about.getSettings().setDatabasePath(cacheDirPath);
         // //设置  Application Caches 缓存目录 
         wv_about.getSettings().setAppCachePath(cacheDirPath);
         // 开启 Application Caches 功能 
         wv_about.getSettings().setAppCacheEnabled(true);

        // 加载需要显示的网页
            wv_about.loadUrl("http://www.baidu.com");

        wv_about.addJavascriptInterface(new DemoJavaScriptInterface(),
                "android");

        wv_about.setWebViewClient(new WebViewClient() {

            @Override
            public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
                // TODO Auto-generated method stub
                super.onUnhandledKeyEvent(view, event);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                // 这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.loadData("", "text/html", "UTF-8");

            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
        // 点击后退按钮,让WebView后退一页(也可以覆写Activity的onKeyDown方法)
        wv_about.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && wv_about.canGoBack()) { // 表示按返回键时的操作
                        wv_about.goBack(); // 后退
                        // webview.goForward();//前进
                        return true; // 已处理
                    }
                }
                return false;
            }
        });
//        re_back.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if ( wv_about.canGoBack()) {
//                    wv_about.goBack();
//                }else
//                    finish();
//            }
//        });
    }



    // 与js交互的方法
    final class DemoJavaScriptInterface {

        DemoJavaScriptInterface() {
            Log.i("aaaa", "create DemoJavaScriptInterface");
        }

        @JavascriptInterface
        public void jumpActivity(String name, String parameter) {


        }
    }
    public int px2dip( float pxValue){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
    // 设置回退
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv_about.canGoBack()) {
            wv_about.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
