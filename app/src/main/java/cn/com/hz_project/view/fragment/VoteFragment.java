package cn.com.hz_project.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.view.adapter.VoteAdapter;
import cn.com.projectdemos.R;

/**
 * Created by ku on 2016/7/16.
 */
public class VoteFragment extends Fragment{

    @InjectView(R.id.list_vote)
    ListView listVote;

    private ArrayList<String> list;
    private VoteAdapter voteAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_vote,null);

        ButterKnife.inject(this,view);

        list = new ArrayList<>();
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");

        voteAdapter = new VoteAdapter(list,getActivity());
        listVote.setAdapter(voteAdapter);

        return view;
    }
}
