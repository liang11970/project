package cn.com.projectdemos;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cn.com.projectdemos.meeting.widget.MeetingTabPageView;
import cn.com.projectdemos.utils.Constants;
import cn.com.projectdemos.utils.HttpUtils;

/**
 * Created by wee on 16/6/20.
 */
public class UserLoginActivity extends AppCompatActivity {

    RelativeLayout parentView;
    private String[] tabTitle = {"签到 ", "投票", "我"};
    private MeetingTabPageView tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ViewGroup layoutView = (ViewGroup) View.inflate(this,
                R.layout.activity_shop_info, null);


        parentView = (RelativeLayout) layoutView.findViewById(R.id.parent_view);
        tab = new MeetingTabPageView(this, tabTitle);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutView.addView(tab.getLayout(), 0, params);
        tab.choosePage(0);
        setContentView(layoutView);


        RequestParams map = new RequestParams();
        map.add("userName", "");
        map.add("userPass", "");
        map.add("userPhoneInfo", Build.DEVICE.toString());

        HttpUtils.get(Constants.URL_SHOP_LOGIN, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });



    }


}
