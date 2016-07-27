package cn.com.hz_project.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cn.com.hz_project.model.bean.ServerFile;
import cn.com.hz_project.model.bean.ServerFileObj;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.JudgeFileTypeUtils;
import cn.com.hz_project.tools.utils.LogUtils;

import cn.com.hz_project.tools.utils.scalars.DownLoadUtils;
import cn.com.hz_project.tools.utils.scalars.FileUtils;
import cn.com.hz_project.view.activity.ViewPagerActivity;
import cn.com.hz_project.view.adapter.FileListAdapter;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SortFragment extends Fragment {

    private GridView gview;
    private ArrayList<ServerFileObj> list;
    private Retrofit retrofit;
    private LoginService loginService;
    private FileListAdapter adapter;
    private DownLoadUtils downLoadUtils;

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File
            .separator + "Judicial";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_sort, null);

        //初始化数据
        init(view);

        initListener(view);

        return view;
    }

    private void init(View view) {
        downLoadUtils = new DownLoadUtils(getActivity());

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        loginService = retrofit.create(LoginService.class);

        gview = (GridView) view.findViewById(R.id.bookshelf);

        adapter = new FileListAdapter(list, getActivity());
        gview.setAdapter(adapter);

        loginService.getFileList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ServerFile>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.i("----------->", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.i("----------->", "onError" + e.toString());
                    }

                    @Override
                    public void onNext(ServerFile file) {
                        LogUtils.i("----------->", "onNext" + file.toString());

                        list = file.getObj();
                        adapter = new FileListAdapter(list, getActivity());
                        gview.setAdapter(adapter);

                    }
                });
    }

    private void initListener(View view) {
        view.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.transaction = ViewPagerActivity.fragmentManager.beginTransaction();
                Fragment sortFragment = new UpDateFragment();
                ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
                ViewPagerActivity.transaction.commit();
                Logger.e("点击了文件上传");
            }
        });

        view.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.transaction = ViewPagerActivity.fragmentManager.beginTransaction();
                Fragment sortFragment = new DownloadFragment();
                ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
                ViewPagerActivity.transaction.commit();
            }
        });

        /**
         * 文件点击事件（下载/查看）
         */
        gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public Intent intent;
            private boolean isExits = false;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                try {
                    if (FileUtils.getAllFileName(path) != null) {
                        for (String fileName : FileUtils.getAllFileName(path)) {
                            if (fileName.equals(list.get(position).getFILE_URL_NAME())) {//如果文件已存在
                                isExits = true;
//                                Toast.makeText(getActivity(), "此文件已经存在", Toast.LENGTH_SHORT).show();
                                intent = JudgeFileTypeUtils.openFile(list.get(position).getFILE_URL_NAME(), path+"/"+list.get(position).getFILE_URL_NAME());
                                startActivity(intent);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (isExits == false) {
                    isDownloadDialog(position);
                }
            }
        });
    }

    /**
     * 弹出是否下载对话框
     */
    public void isDownloadDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确认下载吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downLoadUtils.download(list.get(position).getFILE_URL_NAME());
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}