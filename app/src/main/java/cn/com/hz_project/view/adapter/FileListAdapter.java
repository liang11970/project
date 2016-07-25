package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import cn.com.hz_project.model.bean.ServerFileObj;
import cn.com.projectdemos.R;

/**
 * Created by ku on 2016/7/25.
 */
public class FileListAdapter extends BaseAdapter{
    private ArrayList<ServerFileObj> list;
    private Context context;
    private ArrayList<HashMap<String,String>> newList;

    public FileListAdapter(ArrayList<ServerFileObj> list, Context context){
        this.list = list;
        this.context = context;
        newList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
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

        viewHolder.tv.setText(list.get(position).getFILE_NAME());

        return convertView;
    }

    private class ViewHolder{
        ImageView iv;
        TextView tv;

        public ViewHolder(View view){
            iv = (ImageView) view.findViewById(R.id.imageview);
            tv = (TextView)view.findViewById(R.id.textview);
        }
    }
}
