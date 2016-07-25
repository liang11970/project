package cn.com.hz_project.view.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.com.hz_project.model.UploadFileRequestBody;
import cn.com.hz_project.model.server.FileServer;
import cn.com.hz_project.model.server.UploadService;
import cn.com.hz_project.tools.DefaultProgressListener;
import cn.com.hz_project.tools.utils.RetrofitUtil;
import cn.com.hz_project.tools.utils.scalars.ScalarsConverterFactory;
import cn.com.hz_project.view.activity.ViewPagerActivity;
import cn.com.projectdemos.R;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by peng on 2016/7/15.
 */
public class UpDateFragment extends Fragment {
    @InjectView(R.id.xuanze)
    TextView xuanze;
    @InjectView(R.id.submit_file)
    Button submitFile;
    private Retrofit retrofit;
    private FileServer netWorkService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.activity_up_date, null);
        net();

        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.transaction = ViewPagerActivity.fragmentManager.beginTransaction();
                Fragment sortFragment = new SortFragment();
                ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
                ViewPagerActivity.transaction.commit();
            }
        });
        ButterKnife.inject(this, view);

        xuanze.setOnClickListener(view1 -> {
            Logger.e("上传文件按钮");


            File file = new File(Environment.getExternalStorageDirectory(), "faq.docx");
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
                        public void onCompleted() {}

                        @Override
                        public void onError(Throwable e) {
                            System.out.println("---the error is ---" + e);
                        }

                        @Override
                        public void onNext(ResponseBody s) {
                            try {
                                System.out.println("---the next string is --" + s.string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });


        });

        return view;
    }

    private void net() {


        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.17:8080/WsbxMobile/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        //让框架自动实现我们的请求接口,让我们的请求接口可以被调用
        netWorkService = retrofit.create(FileServer.class);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.submit_file)
    public void onClick() {
//        netWorkService.postFile()

        Logger.e("上传文件");
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case 1:
                    if (msg.what > 0) {
//                        uploadImageView.updatePercent(msg.what);
                        Log.e("dd","dd");
                    }
                    break;
            }
        }
    };



}
