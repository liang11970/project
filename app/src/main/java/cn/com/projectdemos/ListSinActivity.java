package cn.com.projectdemos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import cn.com.projectdemos.meeting.adapter.UserSinInAdapter;

/**
 * Created by wee on 16/6/21.
 */
public class ListSinActivity extends AppCompatActivity {

    private ListView mListSinIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sin);

        mListSinIn = (ListView)findViewById(R.id.lv_list_sin_in);


        UserSinInAdapter adapter = new UserSinInAdapter(ListSinActivity.this);
        mListSinIn.setAdapter(adapter);
        mListSinIn.setDividerHeight(40);

    }

}
