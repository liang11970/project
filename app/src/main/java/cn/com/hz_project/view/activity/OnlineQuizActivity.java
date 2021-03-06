package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.OnlineQuiz;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OnlineQuizActivity extends Activity {

    @InjectView(R.id.et_quiz_title)
    EditText quizTitle;

    @InjectView(R.id.et_quiz_content)
    EditText quizContent;

    @InjectView(R.id.quiz_spinner)
    Spinner quizAnsObj;

    @InjectView(R.id.btn_quiz)
    Button quiz;

    private Retrofit retrofit;
    private LoginService loginService;

    private int ansObj = -1;
    private PreferencesService preferencesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_onlinequiz);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.inject(this);
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.QUIZ)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);
        preferencesService = new PreferencesService(this);

        /**
         * spinner选中值的获取
         */
        quizAnsObj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                    ansObj = 2;
                else if (position == 1)
                    ansObj = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /**
         * 返回点击
         */
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 提问按钮
         */
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quizContent.length()==0&&quizTitle.length()==0){
                    ToastUtils.show(getApplicationContext(),"请填写问题的信息");
                    return;
                }else if (quizContent.length()==0){
                    ToastUtils.show(getApplicationContext(),"请输入问题内容");
                    return;
                }else if (quizTitle.length()==0){
                    ToastUtils.show(getApplicationContext(),"请输入问题标题");
                    return;
                }
                loginService.Quiz(preferencesService.getPerferences().get("userId"),quizTitle.getText().toString(),quizContent.getText().toString(),ansObj)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<OnlineQuiz>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("----------",""+e.toString());
                            }

                            @Override
                            public void onNext(OnlineQuiz quiz) {
                                if (quiz.isSuccess() == true){

                                    Intent intent = new Intent();
                                    intent.setAction("com.xiazdong");
                                    intent.putExtra("name", "xiazdong");
                                    OnlineQuizActivity.this.sendBroadcast(intent);

                                    Toast.makeText(OnlineQuizActivity.this,quiz.getMsg(),Toast.LENGTH_SHORT).show();


                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
