package cn.com.projectdemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import cn.com.projectdemos.meeting.adapter.MeetingListAdapter;
import cn.com.projectdemos.model.MeetingMode;
import cn.com.projectdemos.utils.Constants;
import cn.com.projectdemos.utils.HttpUtils;

/**
 * Created by wee on 16/6/27.
 */
public class MeetingListActivity extends AppCompatActivity {

    private ListView mMeetingList;
    private MeetingMode meetingMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        mMeetingList = (ListView) findViewById(R.id.lv_meeting);


        HttpUtils.get(Constants.URL_MEETING_LIST, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Gson gson = new Gson();
                meetingMode = gson.fromJson(new String(responseBody), MeetingMode.class);


                MeetingListAdapter meetingListAdapter = new MeetingListAdapter(MeetingListActivity.this, meetingMode.result);
                mMeetingList.setAdapter(meetingListAdapter);
                Toast.makeText(getApplicationContext(), new String(responseBody), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });

        mMeetingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserSinInActivity.class);
                intent.putExtra("bz", meetingMode.result.rows.get(position).bz);
                intent.putExtra("hy_kssj", meetingMode.result.rows.get(position).hy_kssj);
                intent.putExtra("hy_mc", meetingMode.result.rows.get(position).hy_mc);
                intent.putExtra("hy_jssj", meetingMode.result.rows.get(position).hy_jssj);
                intent.putExtra("hy_id", meetingMode.result.rows.get(position).hy_id);
                startActivity(intent);
            }
        });

    }
}
