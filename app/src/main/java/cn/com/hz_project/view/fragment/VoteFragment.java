package cn.com.hz_project.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.Quiz;
import cn.com.hz_project.model.bean.Vote;
import cn.com.hz_project.model.bean.VoteObj;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.view.adapter.VoteAdapter;
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
public class VoteFragment extends Fragment {

    @InjectView(R.id.list_vote)
    ListView listVote;

    @InjectView(R.id.id_swiperefresh)
    SwipeRefreshLayout refreshLayout;

    private ArrayList<VoteObj> list;
    private VoteAdapter voteAdapter;
    private Retrofit retrofit;
    private LoginService loginService;
    private int PAGE = 1;
    private ArrayList<VoteObj> newList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_vote, null);

        ButterKnife.inject(this, view);

        initView(view);
        initData(view);

        return view;
    }

    private void initView(View view) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);

        list = new ArrayList<>();

        voteAdapter = new VoteAdapter(list, getActivity());
        listVote.setAdapter(voteAdapter);

        refreshLayout.setColorSchemeColors(R.color.colorPrimary, R.color.colorAccent,
                R.color.green,R.color.white,R.color.yellow);
    }

    private void initData(View view) {
        getList(PAGE);
//        list.addAll(getList(PAGE));

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getList(PAGE);
            }
        });

    }

    private ArrayList<VoteObj> getList(int page) {
        loginService.getVoteList(PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Vote>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("----------", "" + e.toString());
                    }

                    @Override
                    public void onNext(Vote vote) {
                        newList = vote.getObj();
                        Logger.e(newList.toString());

                        voteAdapter = new VoteAdapter(newList, getActivity());
                        listVote.setAdapter(voteAdapter);
                        refreshLayout.setRefreshing(false);
                    }
                });
        return newList;
    }

}
