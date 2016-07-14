package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.hz_project.model.bean.Login;
import cn.com.hz_project.model.server.LoginService;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 登录界面
 */
public class LoginActivity extends Activity {
    @Bind(R.id.user)
    EditText user;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    Button login;

    private String baseUrl = "http://192.168.2.22:8080/WsbxMobile/loginCtrl/";
    private Retrofit retrofit;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initView();
        initData();

    }


    private void initView(){
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);
    }

    private void initData(){
        if (!isNetworkConnected(LoginActivity.this))
            Toast.makeText(this, "当前网络不可用", Toast.LENGTH_SHORT).show();
        else {
            //点击登录
            login.setOnClickListener(view -> {
                loginService.PostField(user.getText().toString(),password.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Login>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("----------",""+e.toString());
                            }

                            @Override
                            public void onNext(Login login) {
                                Toast.makeText(LoginActivity.this,login.getMsg(),Toast.LENGTH_SHORT).show();
                                Log.i("----------",login.getObj().toString());
                            }
                        });

            });
        }
    }


    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();

    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


}
