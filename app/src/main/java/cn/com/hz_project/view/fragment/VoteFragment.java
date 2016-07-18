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

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.com.projectdemos.R;

/**
 * Created by ku on 2016/7/16.
 */
public class VoteFragment extends Fragment{
    @Bind(R.id.wv_vote)
    WebView wv_vote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_vote,null);

        ButterKnife.bind(this,view);

        wv_vote.loadUrl("https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.1.RMmNFy&id=535829741534&areaId=310100&cat_id=2&rn=e0e747853de22a92b2971dbef58cb732&user_id=1973345958&is_b=1");
        wv_vote.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //启用支持javascript
        WebSettings settings = wv_vote.getSettings();
        settings.setJavaScriptEnabled(true);

        return view;
    }
}
