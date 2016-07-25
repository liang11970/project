package cn.com.hz_project.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;

import cn.com.hz_project.model.DataManager;
import cn.com.hz_project.model.HDialogBuilder;
import cn.com.hz_project.model.server.FileApi;
import cn.com.hz_project.model.server.FileCallback;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.view.activity.ViewPagerActivity;
import cn.com.projectdemos.R;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by peng on 2016/7/15.
 */
public class DownloadFragment extends Fragment {
    private TextView txtProgress;
    private HDialogBuilder hDialogBuilder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.download, null);
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.transaction =  ViewPagerActivity.fragmentManager.beginTransaction();
                Fragment sortFragment = new SortFragment();
                ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
                ViewPagerActivity.transaction.commit();
            }
        });

        retrofitDownload();
        return view;



    }


    private void retrofitDownload(){
        String fileName = "afdc147f-9fa4-4395-a044-ff2a7d23dee9.apk";
        String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File
                .separator + "Judicial";
        String fileStoreName = fileName;
        showLoadingDialog();
        FileApi.getInstance(Urls.FileURL)
                .loadFileByName(fileName, new FileCallback(fileStoreDir, fileStoreName) {
                    @Override
                    public void onSuccess(File file) {
                        super.onSuccess(file);
                        hDialogBuilder.dismiss();
                        Toast.makeText(getContext(),"下载成功",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void progress(long progress, long total) {
                        updateProgress(progress, total);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        hDialogBuilder.dismiss();
                        call.cancel();
                        Toast.makeText(getContext(),"下载失败",Toast.LENGTH_LONG).show();
                    }
                });

    }

    /**
     * 显示下载对话框
     */
    private void showLoadingDialog() {
        txtProgress = (TextView) View.inflate(getContext(), R.layout.layout_hd_dialog_custom_tv, null);
        hDialogBuilder = new HDialogBuilder(getContext());
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
