package cn.com.hz_project.tools.utils.scalars;


import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import cn.com.hz_project.model.DataManager;
import cn.com.hz_project.model.HDialogBuilder;
import cn.com.hz_project.model.server.FileApi;
import cn.com.hz_project.model.server.FileCallback;
import cn.com.hz_project.tools.url.Urls;
import cn.com.projectdemos.R;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by ku on 2016/7/25.
 * 下载工具类
 */
public class DownLoadUtils {
    private TextView txtProgress;
    private HDialogBuilder hDialogBuilder;
    private Context context;
    private Intent intent1 = new Intent();
    private String UPDATEWEA = "downLoadSuccess";


    public DownLoadUtils(Context context){
        this.context = context;
        hDialogBuilder = new HDialogBuilder(context);
    }

    public void download(String fileName,String fileStoreName){

        String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File
                .separator + "Judicial";

        showLoadingDialog();

        FileApi.getInstance(Urls.FileURL)
                .loadFileByName(fileName, new FileCallback(fileStoreDir, fileStoreName) {


                    @Override
                    public void onSuccess(File file) {
                        super.onSuccess(file);
                        hDialogBuilder.dismiss();
                        Toast.makeText(context,"下载成功",Toast.LENGTH_LONG).show();
                        intent1.setAction(UPDATEWEA);
                        context.sendBroadcast(intent1);
                    }

                    @Override
                    public void progress(long progress, long total) {
                        updateProgress(progress, total);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        hDialogBuilder.dismiss();
                        call.cancel();
                        Toast.makeText(context,"下载失败",Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * 显示下载对话框
     */
    private void showLoadingDialog() {
        txtProgress = (TextView) View.inflate(context, R.layout.layout_hd_dialog_custom_tv, null);
        hDialogBuilder = new HDialogBuilder(context);
        hDialogBuilder.setCustomView(txtProgress)
                .title("下载")
                .nBtnText("取消")
                .nBtnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hDialogBuilder.dismiss();
                        FileApi.cancelLoading();
                    }
                }).show();

    }

    /**
     * 更新下载进度
     *
     * @param progress
     * @param total
     */
    private void updateProgress(long progress, long total) {
                txtProgress.setText(String.format("正在下载：(%s/%s)",
                DataManager.getFormatSize(progress),
                DataManager.getFormatSize(total)));
    }
}
