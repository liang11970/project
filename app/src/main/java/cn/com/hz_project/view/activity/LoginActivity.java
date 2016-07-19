package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.Login;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.Md5;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 登录界面(哈哈)
 */
public class LoginActivity extends Activity {

    @InjectView(R.id.user)
    EditText user;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.cb_rempwd)
    CheckBox cbRempwd;
    @InjectView(R.id.cb_autologin)
    CheckBox cbAutologin;
    private Retrofit retrofit;
    private LoginService loginService;
    private PreferencesService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initView();
        initData();

    }


    private void initView() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.LOGINURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);
    }

    private void initData() {

//        service = new PreferencesService(this);
//
//        Map<String, String> params = service.getPerferences();
//        user.setText(params.get("name"));
//        password.setText(Base64.encodeToString(params.get("md5password").getBytes(), Base64.DEFAULT));
//
//        if(params.get("checkbox").equals("true")){
//            cbRempwd.setChecked(true);
//        }



        if (!isNetworkConnected(LoginActivity.this))
            Toast.makeText(this, "当前网络不可用", Toast.LENGTH_SHORT).show();
        else {
            //点击登录
            login.setOnClickListener(view -> {
                /*
                if (isMobileNO(String.valueOf(user.getText()))){
                    loginService.PostField(user.getText().toString(), Md5.getMD5(password.getText().toString()))
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

                                    if(login.isSuccess()){
                                        startActivity(new Intent(LoginActivity.this,ViewPagerActivity.class));
                                    }else {
                                        Toast.makeText(LoginActivity.this,"帐号密码有问题检查一下吧",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
                else {
                    Toast.makeText(LoginActivity.this,"当前手机号码有问题，检查一下吧",Toast.LENGTH_SHORT).show();
                }



*/

//                if(cbRempwd.isChecked()){
//                    loginService.PostField(user.getText().toString(), MD5.md5crypt(password.getText().toString()), 2)
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .doOnNext(login1 -> service.save(user.getText().toString(), new String(Base64.encode(password.getText().toString().getBytes(), Base64.DEFAULT)),true))
//                            .subscribe(new Subscriber<Login>() {
//                                @Override
//                                public void onCompleted() {
//
//                                }
//
//                                @Override
//                                public void onError(Throwable e) {
//                                    Log.i("----------", "" + e.toString());
//                                }
//
//                                @Override
//                                public void onNext(Login login) {
//
//                                    if (login.isSuccess()) {
//                                        startActivity(new Intent(LoginActivity.this, ViewPagerActivity.class));
//                                    } else {
//                                        Toast.makeText(LoginActivity.this, "帐号密码有问题检查一下吧", Toast.LENGTH_SHORT).show();
//                                    }
//
//                                }
//                            });
//                }else {
                    loginService.PostField(user.getText().toString(), Md5.md5crypt(password.getText().toString()), 2)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<Login>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("----------", "" + e.toString());
                                }

                                @Override
                                public void onNext(Login login) {

                                    if (login.isSuccess()) {
                                        startActivity(new Intent(LoginActivity.this, ViewPagerActivity.class));
                                    } else {
                                        Toast.makeText(LoginActivity.this, "帐号密码有问题检查一下吧", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

//                }






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
