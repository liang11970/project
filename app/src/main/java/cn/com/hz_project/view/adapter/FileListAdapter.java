package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import cn.com.hz_project.model.bean.ServerFileObj;
import cn.com.hz_project.tools.utils.LogUtils;
import cn.com.hz_project.tools.utils.scalars.FileUtils;
import cn.com.projectdemos.R;

/**
 * Created by ku on 2016/7/25.
 */
public class FileListAdapter extends BaseAdapter{
    private ArrayList<ServerFileObj> list;
    private Context context;
    private ArrayList<HashMap<String,String>> newList;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File
            .separator + "Judicial";
    private ArrayList<String> fileVec;

    public FileListAdapter(ArrayList<ServerFileObj> list, Context context){
        this.list = list;
        this.context = context;
        newList = new ArrayList<>();
        try {
            fileVec = FileUtils.getAllFileName(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_filelist, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /**
         * 根据文件名判断文件是否本地存在并设置图标
         */
        if (fileVec != null) {
            for (String fileName : fileVec) {
                if (list.get(position).getFILE_NAME().equals(fileName)) {
                    viewHolder.isDown.setVisibility(View.VISIBLE);
                }
            }
        }

        /**
         * 设置文字
         */
        viewHolder.tv.setText(list.get(position).getFILE_NAME());

        return convertView;
    }

    private class ViewHolder{
        ImageView iv;
        TextView tv;
        ImageView isDown;

        public ViewHolder(View view){
            iv = (ImageView) view.findViewById(R.id.imageview);
            tv = (TextView)view.findViewById(R.id.textview);
            isDown = (ImageView) view.findViewById(R.id.isdown);
        }
    }
}
