package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.Quiz;
import cn.com.hz_project.model.bean.QuizAnswer;
import cn.com.hz_project.model.bean.QuizAnswerObj;
import cn.com.hz_project.model.bean.QuizObj;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.LogUtils;
import cn.com.hz_project.view.adapter.AnswerAdapter;
import cn.com.hz_project.view.adapter.QuizAdapter;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ku on 2016/7/19.
 */
public class QuizItemActivity extends Activity {

    private Intent intent;
    private QuizObj obj;
    private Retrofit retrofit;
    private LoginService loginService;
    private ArrayList<QuizAnswerObj> ansList;
    private AnswerAdapter answerAdapter;

    @InjectView(R.id.quiz_title)
    TextView title;

    @InjectView(R.id.quiz_sponsor)
    TextView sponsor;

    @InjectView(R.id.quiz_time)
    TextView time;

    @InjectView(R.id.quiz_content)
    TextView content;

    @InjectView(R.id.et_quiz)
    EditText answer;

    @InjectView(R.id.btn_quiz)
    Button reply;

    @InjectView(R.id.list_answer)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizitem);

        ButterKnife.inject(this);

        initView();
        initData();
    }

    private void initView() {
        intent = getIntent();
        obj = new QuizObj();
        obj = (QuizObj) intent.getSerializableExtra("Obj");

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.QUIZ)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);
        ansList = new ArrayList<>();


        answerAdapter = new AnswerAdapter(ansList,QuizItemActivity.this);

    }

    private void initData() {
        title.setText(obj.getWQD_QUESTION());
        time.setText(obj.getTIME());
        content.setText(obj.getWQD_CONTEXT());


        listView.setAdapter(answerAdapter);

        queryAns();
    }

    /**
     * 查询答案
     * @param
     */
    private void queryAns(){
        loginService.QueryAns(obj.getWQD_ID(),"1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QuizAnswer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("----------", "" + e.toString());
                    }

                    @Override
                    public void onNext(QuizAnswer answer) {
                        ansList = answer.getObj();
                        LogUtils.i("=======",ansList.size()+"---"+ansList.toString());
//                        answerAdapter.notifyDataSetChanged();

                        answerAdapter = new AnswerAdapter(ansList,QuizItemActivity.this);
                        listView.setAdapter(answerAdapter);
                    }
                });
    }


    public void click(View v) {
        switch (v.getId()) {
            case R.id.btn_quiz:
                /**
                * 提交答案
                 */
                loginService.QuizReply(1, obj.getWQD_ID(), answer.getText().toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<QuizAnswer>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("----------", "" + e.toString());
                            }

                            @Override
                            public void onNext(QuizAnswer quiz) {
                                answer.setText("");
                                ansList = quiz.getObj();
                                answerAdapter = new AnswerAdapter(ansList,QuizItemActivity.this);
                                listView.setAdapter(answerAdapter);
                            }
                        });
                break;

        }
    }
}
