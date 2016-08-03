package cn.com.hz_project.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.OnlineQuiz;
import cn.com.hz_project.model.bean.Quiz;
import cn.com.hz_project.model.bean.QuizObj;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.view.activity.OnlineQuizActivity;
import cn.com.hz_project.view.activity.QuizItemActivity;
import cn.com.hz_project.view.adapter.QuizAdapter;
import cn.com.hz_project.view.widget.LoadMoreSwipe;
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
    Button btn_onlinquiz;

    @InjectView(R.id.list_quiz)
    ListView listView;

    @InjectView(R.id.layout_swiperefresh)
    LoadMoreSwipe swipe;

    private QuizAdapter adapter;
    private ArrayList<QuizObj> list;
    private Retrofit retrofit;
    private LoginService loginService;
    private int PAGE = 1;
    private Intent intent;
    private int currentPage = 1;
    private int i = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_quiz, null);

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

        ButterKnife.inject(this, view);

        list = new ArrayList<>();

        swipe.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent,
                R.color.green);

    }

    private void initData() {
        /**
         * 提问问题按钮
         */
        btn_onlinquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), OnlineQuizActivity.class));

            }
        });
        adapter = new QuizAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        getData(currentPage);

        /**
         * 问题详情点击
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getActivity(), QuizItemActivity.class);
                intent.putExtra("Obj", list.get(position));
                startActivity(intent);
            }
        });

        /**
         * 下拉刷新
         */
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
//                Toast.makeText(getActivity(), "您调皮了一下", Toast.LENGTH_SHORT).show();
                list.clear();

                getData(currentPage);
            }
        });

        /**
         * 上拉加载
         */
        swipe.setOnLoadListener(new LoadMoreSwipe.OnLoadListener() {
            @Override
            public void onLoad() {


                currentPage++;
                getData(currentPage);

            }
        });

    }

    /**
     * 获取数据
     */
    private void getData(int PAGE) {
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
                        if (quiz.getObj().size() == 0) {
                            Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
                        }
                        list.addAll(quiz.getObj());

                        adapter.notifyDataSetChanged();

                        swipe.setRefreshing(false);
                        swipe.setLoading(false);
                    }
                });
    }

}
