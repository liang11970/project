package cn.com.hz_project.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.Login;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.Md5;
import cn.com.hz_project.view.base.BaseActivity;
import cn.com.projectdemos.R;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity {

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
    private SweetAlertDialog pDialog;
    private HashMap<String, String> map;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        overridePendingTransition(R.anim.ap2,R.anim.ap1);
        ButterKnife.inject(this);
        initView();
        initData();

    }

    @Override
    protected void initViewsAndEvents() {

    }

    private void initView() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.LOGINURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);

        service = new PreferencesService(this);
        map = service.getLoginInfo();
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("登陆中...");

        intent = getIntent();
    }

    private void initData() {

        //检测记住密码/自动登录
        detectLogin();

        if (!isNetworkConnected(LoginActivity.this))
            Toast.makeText(this, "当前网络不可用", Toast.LENGTH_SHORT).show();
        else {
            //点击登录
            login.setOnClickListener(view -> {
                login(pDialog);
            });
        }
    }


    /**
     * 该方法为登录操作
     * @param pDialog
     */
    private void login(final SweetAlertDialog pDialog) {

//        detectUserChoose();//登录前检测用户选择，并保存信息

        pDialog.show();


        loginService.PostField(user.getText().toString(), Md5.md5crypt(password.getText().toString()), 2)
                .doOnNext(login1 -> service.save(login1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Login>() {
                    @Override
                    public void onCompleted() {
                        pDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
//                        Log.i("----------", "" + e.toString());
                        pDialog.dismiss();
                    }

                    @Override
                    public void onNext(Login login) {

                        if (login.isSuccess()) {

                            SharedPreferences preferences=getSharedPreferences("logintag",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putBoolean("tag",login.isSuccess());
                            editor.commit();

                            startActivity(new Intent(LoginActivity.this, ViewPagerActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "帐号密码有问题检查一下吧", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    /**
     * 检测用户的选择状态，并保存信息
     */
    private void detectUserChoose() {

        if (cbRempwd.isChecked() == true){
            if (cbAutologin.isChecked() == true)
            saveInfo(user.getText().toString(),password.getText().toString(),"true","true");
            else
                saveInfo(user.getText().toString(),password.getText().toString(),"true","false");
        }else
            saveInfo(user.getText().toString(),"","false","false");
    }

    /**
     * 保存登录参数
     */
    private void saveInfo(String userName,String pwd,String remPwd,String autoLogin) {
        try {
            service.save(userName,pwd,remPwd,autoLogin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 该方法用于判断是否记住密码，自动登录
     * 思路:1.先从sp里获取上次登录是否选择了记住密码，当为false时，正常输入正常登录；
     *      2.当为true时，从sp里获取账号，密码，是否自动登录；
     *      3.当自动登录为true时自动登录，false时点击登录；
     */
    private void detectLogin() {
        /**
         * 检测用户上次登录是否选择了记住密码
         */
        if (map.get("remPwd").equals("true")) {//当上次状态为记住密码
            user.setText(map.get("name"));
            try {
                password.setText(map.get("pwd"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            cbRempwd.setChecked(true);

            if (map.get("autoLogin").equals("true")) {//当上次状态为自动登录
                //选择自动登录,保存登录信息并执行自动登录
                cbAutologin.setChecked(true);
                saveInfo(user.getText().toString(),password.getText().toString(),String.valueOf(cbRempwd.isChecked()),String.valueOf(cbAutologin.isChecked()));
                if (intent.getBooleanExtra("isQuit",false) == true ){
                    //不登录
                }else
                login(pDialog);
            }
        }else {//没有选择记住密码
            user.setText(map.get("name"));
            password.setText("");
            cbRempwd.setChecked(false);
            cbAutologin.setChecked(false);
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
