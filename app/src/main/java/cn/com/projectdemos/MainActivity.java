package cn.com.projectdemos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mZxing;
    private Button btnMeeting;
    private Button btnPhoneSinIn;
    private Button meetingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mZxing = (Button) findViewById( R.id.zxing);
        btnMeeting = (Button)findViewById(R.id.btn_meeting) ;

        btnPhoneSinIn = (Button)findViewById(R.id.btn_phone_sin_in) ;

        meetingList = (Button)findViewById(R.id.meeting_list);


        mZxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ZXingDemo.class);
                startActivity(intent);
            }
        });

        btnMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserLoginActivity.class);
                startActivity(intent);
            }
        });



        btnPhoneSinIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UserSinInActivity.class);
                startActivity(intent);
            }
        });


        meetingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MeetingListActivity.class);
                startActivity(intent);
            }
        });



    }
}
