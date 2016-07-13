package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.projectdemos.R;

public class LoginActivity extends Activity {
    @Bind(R.id.user)
    EditText user;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if(isNetworkConnected(LoginActivity.this));
            else
            Toast.makeText(this,"当前网络不可用",Toast.LENGTH_SHORT).show();

        login.setOnClickListener(view -> {
            if (isMobileNO(String.valueOf(user.getText()))){

                Toast.makeText(LoginActivity.this,"登录操作",Toast.LENGTH_SHORT).show();


            }
            else Toast.makeText(LoginActivity.this,"当前手机号码有问题，检查一下吧",Toast.LENGTH_SHORT).show();


        });

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
