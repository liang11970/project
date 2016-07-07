package cn.com.projectdemos.meeting.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.karics.library.zxing.android.CaptureActivity;

import cn.com.projectdemos.ListSinActivity;
import cn.com.projectdemos.R;
import cn.com.projectdemos.ZXingDemo;
import cn.com.projectdemos.meeting.widget.FrameFragment;

/**
 * Created by wee on 16/6/20.
 */
public class MeetingScreenFragment extends FrameFragment {
    private ImageButton mImgBtnScreen;
    private TextView mIvScreen;
    private Button mHisSinIn;


    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    View mView;

    @Override
    protected View getFragmentResource(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_meeting_screen, null);
        mImgBtnScreen = (ImageButton) mView.findViewById(R.id.imgbtn_screen);
        mIvScreen = (TextView) mView.findViewById(R.id.iv_screen);
        mHisSinIn = (Button) mView.findViewById(R.id.his_sin_in);



        return mView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void doAfterCreate() {

        mImgBtnScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),
                        CaptureActivity.class);
                startActivityForResult(intent, ZXingDemo.REQUEST_CODE_SCAN);
            }
        });


        mHisSinIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListSinActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //
        if (requestCode == ZXingDemo.REQUEST_CODE_SCAN && resultCode == Activity.RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(DECODED_CONTENT_KEY);
//                mIvScreen.setText("二维码内容： \n" + content);


                Toast.makeText(getActivity(),"二维码内容： \n" + content,Toast.LENGTH_LONG).show();


//                qrCodeImage.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onResumeView() {

    }
}
