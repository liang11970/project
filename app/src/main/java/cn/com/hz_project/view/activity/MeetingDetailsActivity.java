package cn.com.hz_project.view.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.MeetingSignInBean;
import cn.com.hz_project.model.server.MeetingService;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeetingDetailsActivity extends Activity implements View.OnClickListener {


    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.tv_Meeting_tv)
    TextView tvMeetingTv;
    @InjectView(R.id.rl_meeting_personnelList)
    RelativeLayout rlMeetingPersonnelList;
    @InjectView(R.id.ll_list)
    LinearLayout llList;
    @InjectView(R.id.tv_Meeting_Name)
    TextView tvMeetingName;
    @InjectView(R.id.tv_Meeting_Time)
    TextView tvMeetingTime;
    @InjectView(R.id.tv_Meeting_content)
    TextView tvMeetingContent;
    @InjectView(R.id.et_phoneNum)
    EditText etPhoneNum;
    @InjectView(R.id.bt_meeting_signin)
    Button btMeetingSignin;
    @InjectView(R.id.tv_Meeting_eTime)
    TextView tvMeetingETime;
    private String meetingId;
    private PreferencesService preferencesService;
    private boolean complete = false;
    private MeetingService meetingService;
    private boolean isAdmin;
    private Bundle extras;
    private String qd_user_id;
    private String content;
    private DialogPlus dialog;
    private String signPhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        ButterKnife.inject(this);

        initView();
        initData();

    }

    private void initData() {
        extras = getIntent().getExtras();
        meetingId = (String) extras.get("ID").toString();
        content = (String) extras.get("content");
        tvMeetingName.setText(extras.get("name") + "");
        String startTime = (String) extras.get("startTime");
        String endTime = (String) extras.get("endTime");
        tvMeetingTime.setText("开始时间:" + startTime.substring(0, startTime.length() - 3));
        tvMeetingETime.setText("结束时间:" + endTime.substring(0, endTime.length() - 3));
        tvMeetingContent.setText(extras.get("content") + "");

    }

    private void initView() {
        preferencesService = new PreferencesService(getApplicationContext());
        if (preferencesService.getPerferences().get("roleId").equals("9")) {
            isAdmin = true;

            etPhoneNum.setVisibility(View.VISIBLE);
            rlMeetingPersonnelList.setVisibility(View.VISIBLE);
        } else {
            isAdmin = false;
            etPhoneNum.setVisibility(View.INVISIBLE);
            rlMeetingPersonnelList.setVisibility(View.INVISIBLE);


        }
        Log.e("会议签到role", preferencesService.getPerferences().get("role") + ""
        );
        Log.e("会议签到roleid", preferencesService.getPerferences().get("roleId") + ""
        );
        rlMeetingPersonnelList.setOnClickListener(this);
        btMeetingSignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_meeting_personnelList:
                Intent intent = new Intent(getApplicationContext(), MeetingStaffPieActivity.class);
                intent.putExtra("meetingID", meetingId);
                startActivity(intent);
                break;
            case R.id.bt_meeting_signin:
                /**管理员*/
                if (isAdmin) {
                    signPhoneNum = etPhoneNum.getText().toString();
                    signInRequestCreate();
                    signInRequestData();

                    /**普通用户*/
                } else {
                    qd_user_id = preferencesService.getPerferences().get("userId");

                    if (!complete) {/**判断重复签到*/
                        signInRequestCreate();
                        signInRequestData();
                    } else {
                        ToastUtils.show(getApplicationContext(), "已签到成功，请不要重复签到");
                    }
                }

                break;
        }
    }

    private void signInRequestData() {
        if (signPhoneNum != null) {
            qd_user_id = signPhoneNum;
        }
        meetingService.getMeetingSignIn(extras.get("ID").toString()
                , preferencesService.getPerferences().get("userId")
                , content, qd_user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MeetingSignInBean>() {
                    @Override
                    public void onCompleted() {
                        complete = true;
//                        showwDialog();
//                        showDDialog();

                    }

                    @Override
                    public void onError(Throwable e) {
//                        showDDialog();
//                        showwDialog();
                        Log.e("异常", e.toString());
                        showDDialog("签到失败,请检查网络");

                    }

                    @Override
                    public void onNext(MeetingSignInBean meetingSignInBean) {
                        Log.e("会议签到,签到返回数据1", meetingSignInBean.getMsg().toString());
                        showDDialog(meetingSignInBean.getMsg());

                        etPhoneNum.setText("");
                    }
                });


    }

    private void showDDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MeetingDetailsActivity.this);
        builder.setTitle("提示");
        builder.setMessage(message);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    private void showwDialog() {
        ;

        DialogInterface.OnClickListener dialogOnclicListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("您已签到成功!");
        builder.setPositiveButton("确定", dialogOnclicListener);
        builder.create().show();

    }

    private void signInRequestCreate() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        meetingService = retrofit.create(MeetingService.class);
    }
}
