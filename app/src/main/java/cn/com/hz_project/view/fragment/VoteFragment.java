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

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.projectdemos.R;

/**
 * Created by ku on 2016/7/16.
 */
public class VoteFragment extends Fragment{
    @InjectView(R.id.wv_vote)
    WebView wv_vote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_vote,null);

        ButterKnife.inject(this,view);

        wv_vote.loadUrl("http://mp.weixin.qq.com/s?src=3&timestamp=1468910533&ver=1&signature=RbnX4tUBODpql9qsvp4jJRDrtHc-LSXXm9gSM*BNY*M1iyxZAFmTcAp0BIOouqvvdFBdqnbsKoRVcPUGcUskCOEMeV*UXEaaCZ4zhB-6nc6ZQsmrWOvaLAXsTylb6COHDmQPRiweUDUmNTEqDPJwJsK9dY2X1Nli1Gybroh47NU=");
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
