package cn.com.projectdemos;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cn.com.projectdemos.meeting.widget.YesNoDialog;
import cn.com.projectdemos.utils.Constants;
import cn.com.projectdemos.utils.HttpUtils;
import cn.com.projectdemos.utils.Utils;

/**
 * Created by wee on 16/6/23.
 */
public class UserSinInActivity extends Activity {

    private Button btnOk;
    private EditText userPhoneNu;

    private TextView title_meeting_name;
    private TextView mTitleMeetingContent;
    private TextView titleMeetingTime;
    private TextView titleMeetingOverTime;
    private Bundle bundle = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sin_in);


        btnOk = (Button) findViewById(R.id.btn_ok);
        userPhoneNu = (EditText) findViewById(R.id.userPhoneNu);

        title_meeting_name = (TextView) findViewById(R.id.title_meeting_name);
        mTitleMeetingContent = (TextView) findViewById(R.id.title_meeting_content);
        titleMeetingTime = (TextView) findViewById(R.id.title_meeting_time);
        titleMeetingOverTime = (TextView) findViewById(R.id.title_meeting_over_time);


        bundle = getIntent().getExtras();


        title_meeting_name.setText(bundle.getString("hy_mc"));
        mTitleMeetingContent.setText(bundle.getString("bz"));

        titleMeetingTime.setText("开始时间：" + Utils.getTimestamp(bundle.getString("hy_kssj")));
        titleMeetingOverTime.setText("结束时间：" + Utils.getTimestamp(bundle.getString("hy_jssj")));


        userPhoneNu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userPhoneNu.getText().toString().trim().length() > 0) {
                    btnOk.setClickable(true);
                } else {
                    btnOk.setClickable(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        final YesNoDialog.DialogListener listener = new YesNoDialog.DialogListener() {
            @Override
            public void onConfirm() {

                RequestParams map = new RequestParams();
                map.add("hy_id", bundle.getString("hy_id"));
                map.add("sjhm", userPhoneNu.getText().toString().trim());

                HttpUtils.get(Constants.URL_MEETING_SIN_IN, map, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {
                        if ((new String(responseBody)) == null) {
                            return;
                        }
                        try {
                            JSONObject jsonObject = new JSONObject(new String(responseBody));
                            String str = jsonObject.getString("status");
                            if (str.equals("1")) {
                                YesNoDialog dialog = new YesNoDialog(UserSinInActivity.this, R.style.class_dialog,
                                        YesNoDialog.NEED_BIND, "否", "确定", jsonObject.getString("msg"), new YesNoDialog.DialogListener() {
                                    @Override
                                    public void onConfirm() {
                                    }
                                    @Override
                                    public void onCancel() {
                                    }
                                });
                                dialog.show();

                            } else if (str.equals("0")) {
                                YesNoDialog dialog = new YesNoDialog(UserSinInActivity.this, R.style.class_dialog,
                                        YesNoDialog.ALERT_DELETE, "否", "确定", jsonObject.getString("result") + "签到" + jsonObject.getString("msg"), new YesNoDialog.DialogListener() {
                                    @Override
                                    public void onConfirm() {
                                        userPhoneNu.setText("");


                                    }
                                    @Override
                                    public void onCancel() {
                                    }
                                });
                                dialog.show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(UserSinInActivity.this, "没有网络了，检查一下吧～", Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onCancel() {

            }
        };
        btnOk.setClickable(false);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YesNoDialog dialog = new YesNoDialog(UserSinInActivity.this, R.style.class_dialog,
                        YesNoDialog.ALERT_DELETE, "否", "签到", "是否已经确认手机号码签到？", listener);
                dialog.show();

            }
        });
    }


}
