package cn.com.hz_project.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.hz_project.model.UploadFileRequestBody;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.hz_project.model.server.UploadService;
import cn.com.hz_project.tools.DefaultProgressListener;
import cn.com.hz_project.tools.utils.RetrofitUtil;
import cn.com.hz_project.view.activity.ViewPagerActivity;
import cn.com.projectdemos.R;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by peng on 2016/7/15.
 */
public class UpDateFragment extends Fragment {
    TextView xuanze;
    public static EditText lujing;
    @InjectView(R.id.back)
    TextView back;


    @InjectView(R.id.submit_file)
    Button submitFile;
    private PreferencesService service;
    private HashMap map;
    private SweetAlertDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.activity_up_date, null);
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.transaction = ViewPagerActivity.fragmentManager.beginTransaction();
                Fragment sortFragment = new FileFragment();
                ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
                ViewPagerActivity.transaction.commit();
            }
        });
        xuanze = (TextView) view.findViewById(R.id.xuanze);
        xuanze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);

            }
        });
        lujing = (EditText) view.findViewById(R.id.lujing);

        lujing.setText(ViewPagerActivity.lujing);

        ButterKnife.inject(this, view);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.submit_file)
    public void onClick() {

        if(ViewPagerActivity.lujing.isEmpty()){
            Toast.makeText(getContext(),"请选择文件",Toast.LENGTH_LONG).show();
        }else {
            final ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setMessage("上传中，请稍后...");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(false);
            dialog.show();

            File file = new File(ViewPagerActivity.lujing);

            UploadService uploadFileService = RetrofitUtil.createService(UploadService.class);
            Map<String, String> optionMap = new HashMap<>();
            optionMap.put("Platformtype", "Android");
            optionMap.put("userName","zhangsan") ;

            Map<String, RequestBody> requestBodyMap = new HashMap<>();
            UploadFileRequestBody fileRequestBody = new UploadFileRequestBody(file, new DefaultProgressListener(mHandler,1));
            requestBodyMap.put("file\"; filename=\"" + file.getName(), fileRequestBody);

            uploadFileService.uploadFileInfo(optionMap, requestBodyMap).
                    subscribeOn(Schedulers.io()).
                    observeOn(AndroidSchedulers.mainThread()).
                    subscribe(new Subscriber<ResponseBody>() {
                        @Override
                        public void onCompleted() {
                            dialog.dismiss();

                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(ResponseBody s) {
                            Toast.makeText(getContext(),"上传成功",Toast.LENGTH_LONG).show();

                        }
                    });
        }



    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case 1:
                    if (msg.what > 0) {
//                        uploadImageView.updatePercent(msg.what);
                    }
                    break;
            }
        }
    };
}
