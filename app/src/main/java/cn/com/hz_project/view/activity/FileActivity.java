package cn.com.hz_project.view.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import cn.com.hz_project.model.bean.DataResponse;
import cn.com.hz_project.model.bean.UploadFile;
import cn.com.hz_project.model.server.FileServer;
import cn.com.hz_project.tools.url.Urls;
import cn.com.projectdemos.R;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FileActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String FILENAME = "data.txt";
    private static final String TAG = "DataStorageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);


//        uploadFiles();


        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.56.1")
                .build();
        FileServer service =retrofit.create(FileServer.class);
        File file = new File(Environment.getExternalStorageDirectory() + "/" + "1.txt");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        Call<String> model = service.upload("this is txt",requestBody);
        model.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.e(TAG, "onResponse: " + response.body().toString() );
                Log.e("onResponse", "onResponse: " + response.body().toString() );
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });




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


    public void saveData() throws IOException {

        File sdCard = Environment.getExternalStorageDirectory();
        // 获取外部存储设备（SD卡）的路径
        Log.i(TAG, sdCard.getAbsolutePath());
        // 查看LogCat,获取的sd卡的绝对路径为 /storage/sdcard
        sdCard = new File(sdCard, "/MyFiles");
        sdCard.mkdirs();// 创建MyFiles目录(可创建多级目录)
        sdCard = new File(sdCard, FILENAME);
        FileOutputStream out = new FileOutputStream(sdCard);
        Writer writer = new OutputStreamWriter(out);
        try {
            String str = "来自保存在内部存储设备的数据";
            writer.write(str);
        } finally {
            writer.close();
        }
    }

    public void loadData() throws FileNotFoundException, IOException {
        BufferedReader reader = null;
        StringBuilder data = new StringBuilder();
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            sdCard = new File(sdCard, "/MyFiles/" + FILENAME);
            FileInputStream in = new FileInputStream(sdCard);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = new String();
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
//            dataView.setText(data);
        } catch (FileNotFoundException e) {
//            dataView.setText("没有发现保存的数据");
        } finally {
            reader.close();
        }
    }
}
