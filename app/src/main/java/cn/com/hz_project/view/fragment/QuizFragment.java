package cn.com.hz_project.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.Quiz;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.view.activity.OnlineQuizActivity;
import cn.com.hz_project.view.adapter.QuizAdapter;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ku on 2016/7/16.
 */
public class QuizFragment extends Fragment {


    @InjectView(R.id.btn_onlinequiz)
    Button btnOnlinequiz;
    @InjectView(R.id.list_quiz)
    ListView listQuiz;
    private QuizAdapter adapter;
    private ArrayList<String> list;
    private Retrofit retrofit;
    private LoginService loginService;
    private int PAGE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_quiz, null);

        ButterKnife.inject(this, view);
        initView(view);
        initData();


        return view;
    }

    private void initView(View view) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.QUIZ)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);


        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("test");
        }

        adapter = new QuizAdapter(getActivity(), list);
        listQuiz.setAdapter(adapter);
    }

    private void initData() {
        /**
         * 提问问题按钮
         */
        btnOnlinequiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OnlineQuizActivity.class));
            }
        });

        /**
         * 获取数据
         */
        loginService.Quiz(PAGE)
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

                    }
                });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
