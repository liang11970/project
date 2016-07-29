package cn.com.hz_project.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.hz_project.model.bean.Advice;
import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.model.bean.RetrofitEntity;
import cn.com.hz_project.model.server.HttpService;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.hz_project.presenter.HttpManager;
import cn.com.hz_project.presenter.activityPresenter.AdvicePresenter;
import cn.com.hz_project.presenter.activityPresenter.NewsContract;
import cn.com.hz_project.presenter.activityPresenter.NewsPresenter;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.hz_project.view.base.BaseActivity;
import cn.com.projectdemos.R;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AdviceActivity extends BaseActivity {

    @InjectView(R.id.back)
    TextView back;
    @InjectView(R.id.editText)
    EditText editText;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.bt_submit)
    Button btSubmit;
    private PreferencesService preferencesService;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.advice);
        ButterKnife.inject(this);
        preferencesService = new PreferencesService(this);
        userId = preferencesService.getPerferences().get("userId");

    }

    private void submit(String content) {


        //手动创建一个OkHttpClient并设置超时时间
        okhttp3.OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Urls.ADVICEURL)
                .build();

        //        加载框
        final ProgressDialog pd = new ProgressDialog(this);


        Map<String,String> con= new HashMap<String, String>();
        con.put("context",content);
        con.put("userid",userId);


        HttpService apiService = retrofit.create(HttpService.class);
        Observable<Advice> observable = apiService.getAllVedioByss1(con);
        observable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<Advice>() {
                            @Override
                            public void onCompleted() {
                                if (pd != null && pd.isShowing()) {
                                    pd.dismiss();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Logger.e("反馈error>>>>>>>" + e.toString());
                                ToastUtils.show(getApplicationContext(),"Error!");
                                if (pd != null && pd.isShowing()) {
                                    pd.dismiss();
                                    showDDialog("提交失败");
                                }
                            }

                            @Override
                            public void onNext(Advice retrofitEntity) {
//                                    tvMsg.setText("无封装：\n" + retrofitEntity.getData().toString());
                                Logger.e(retrofitEntity.toString());

                                editText.setText("");
                                showDDialog("您的反馈已提交");



                            }

                            @Override
                            public void onStart() {
                                super.onStart();
                                pd.show();
                            }
                        }

                );


    }

    private void showDDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdviceActivity.this);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    @Override
    protected void initViewsAndEvents() {

    }

    @OnClick({R.id.back, R.id.bt_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.bt_submit:

                if (editText.getText().length() == 0) {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_LONG).show();
                } else {
                    submit(editText.getText().toString());
                }


                break;
        }
    }


}
