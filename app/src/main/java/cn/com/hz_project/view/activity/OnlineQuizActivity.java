package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.Quiz;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OnlineQuizActivity extends Activity {


    @InjectView(R.id.back)
    TextView back;
    @InjectView(R.id.title)
    RelativeLayout title;
    @InjectView(R.id.et_quiz_title)
    EditText etQuizTitle;
    @InjectView(R.id.quiz_spinner)
    Spinner quizSpinner;
    @InjectView(R.id.et_quiz_content)
    EditText etQuizContent;
    @InjectView(R.id.btn_quiz)
    Button btnQuiz;
    private Retrofit retrofit;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_onlinequiz);
        ButterKnife.inject(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.QUIZ)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);


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
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginService.Quiz("1", etQuizTitle.getText().toString(), etQuizContent.getText().toString(), 1)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Quiz>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("----------", "" + e.toString());
                            }

                            @Override
                            public void onNext(Quiz quiz) {
                                if (quiz.isSuccess() == true) {
                                    Toast.makeText(OnlineQuizActivity.this, quiz.getMsg(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}
