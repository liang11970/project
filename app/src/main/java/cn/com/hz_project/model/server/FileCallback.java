package cn.com.hz_project.model.server;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public abstract class FileCallback implements Callback<ResponseBody> {

    /**
     * 订阅下载进度
     */
    private CompositeSubscription rxSubscriptions = new CompositeSubscription();
    /**
     * 目标文件存储的文件夹路径
     */
    private String destFileDir;
    /**
     * 目标文件存储的文件名
     */
    private String destFileName;

    /**
     *
     */
    private Context mContext;

    public FileCallback(String destFileDir, String destFileName, Context context) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        this.mContext = context;
        subscribeLoadProgress();
    }

    public void onSuccess(File file) {
        unsubscribe();
    }

    public abstract void progress(long progress, long total);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            saveFile(response);
        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    /**
     * 保存
     * @param response
     * @return
     * @throws IOException
     */
    public File saveFile(Response<ResponseBody> response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            if (response.body() == null) {
                Toast.makeText(mContext, "服务器文件异常", Toast.LENGTH_SHORT).show();
                return null;
            } else {

                is = response.body().byteStream();

                File dir = new File(destFileDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, destFileName);
                fos = new FileOutputStream(file);
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.flush();
                onSuccess(file);
                return file;
            }
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 订阅文件下载进度
     */
    private void subscribeLoadProgress() {
        rxSubscriptions.add(RxBus.getDefault()
                .toObservable(FileLoadEvent.class)
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FileLoadEvent>() {
                    @Override
                    public void call(FileLoadEvent fileLoadEvent) {
                        progress(fileLoadEvent.getProgress(), fileLoadEvent.getTotal());
                    }
                }));
    }

    /**
     * 取消订阅，防止内存泄漏
     */
    private void unsubscribe() {
        if (!rxSubscriptions.isUnsubscribed()) {
            rxSubscriptions.unsubscribe();
        }
    }

}
