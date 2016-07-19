package cn.com.hz_project.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

import cn.com.hz_project.model.bean.DataResponse;
import cn.com.hz_project.model.bean.UploadFile;
import cn.com.hz_project.model.server.FileServer;
import cn.com.hz_project.tools.url.Urls;
import cn.com.projectdemos.R;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FileActivity extends AppCompatActivity {

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);


//        uploadFiles();


    }


    public void uploadFiles(File file){

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.LOGINURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        //代理模式生成对应server的实例化对象
        FileServer server = retrofit.create(FileServer.class);
        //创建RequwstBody对象
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        //使用RxJava方式调度任务并监听
        server.uploadFile(requestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<DataResponse<UploadFile>, UploadFile>() {
                    @Override
                    public UploadFile call(DataResponse<UploadFile> uploadFileDataResponse) {
                        return null;
                    }
                })
                .subscribe(s -> {
                    Log.i("dd",s.getUploadFileName());


                });
    }
}
