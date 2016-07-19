package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.hz_project.model.bean.Login;
import cn.com.hz_project.model.bean.Quiz;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.MD5;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OnlineQuizActivity extends Activity {

    @Bind(R.id.et_quiz_title)
    EditText quizTitle;

    @Bind(R.id.et_quiz_content)
    EditText quizContent;

    @Bind(R.id.quiz_spinner)
    Spinner quizAnsObj;

    @Bind(R.id.btn_quiz)
    Button quiz;

    private Retrofit retrofit;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_onlinequiz);

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.QUIZ)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);

        ButterKnife.bind(this);

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
                loginService.Quiz("1",quizTitle.getText().toString(),quizContent.getText().toString(),1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Quiz>() {
                            @Override
                            public void onCompleted() {
                                
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("----------",""+e.toString());
                            }

                            @Override
                            public void onNext(Quiz quiz) {
                                if (quiz.isSuccess() == true){
                                    Toast.makeText(OnlineQuizActivity.this,quiz.getMsg(),Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
