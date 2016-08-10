package cn.com.hz_project.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.ServerFileObj;
import cn.com.hz_project.tools.utils.JudgeFileTypeUtils;
import cn.com.hz_project.tools.utils.scalars.FileUtils;
import cn.com.hz_project.view.activity.ViewPagerActivity;
import cn.com.hz_project.view.adapter.FileListAdapter;
import cn.com.projectdemos.R;

/**
 * Created by peng on 2016/7/15.
 * 已下载文献碎片
 */
public class DownloadFragment extends Fragment {

    @InjectView(R.id.bookshelf)
    GridView fileList;

    private ArrayList<ServerFileObj> list;
    private FileListAdapter adapter;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File
            .separator + "Judicial";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.download, null);

        ButterKnife.inject(this,view);

        initView(view);

        initListener(view);

        return view;
    }


    private void initView(View view) {

        list = new ArrayList<>();
        try {
            for (String fileName : FileUtils.getAllFileName(path)) {
                list.add(new ServerFileObj("aa",fileName,"aa","aa","aa","aa","aa","aa"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        adapter = new FileListAdapter(list, getActivity());
        fileList.setAdapter(adapter);
    }


    private void initListener(View view) {
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.transaction = ViewPagerActivity.fragmentManager.beginTransaction();
                Fragment sortFragment = new FileFragment();
                ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
                ViewPagerActivity.transaction.commit();
            }
        });


        /**
         * 文件点击事件（下载/查看）
         */
        fileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public Intent intent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = JudgeFileTypeUtils.openFile(list.get(position).getFILE_NAME(), path+"/"+list.get(position).getFILE_NAME());
                startActivity(intent);
            }
        });
    }

}
