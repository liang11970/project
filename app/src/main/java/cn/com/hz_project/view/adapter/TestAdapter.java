package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.com.hz_project.model.bean.VideoResult;
import cn.com.hz_project.model.bean.VoteObj;
import cn.com.projectdemos.R;

/**
 * ==================================
 * Created by wangl on 2016/8/1.时间12:29
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class TestAdapter implements Adapter {

    private ArrayList<VideoResult.ObjBean> list;
    private Context context;

    public TestAdapter(ArrayList<VideoResult.ObjBean> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

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
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_new,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(list.get(position).getVF_VIDEO_NAME2());
        viewHolder.time.setText(list.get(position).getVF_UPDATE_TIME());

        return convertView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


    class ViewHolder{
        TextView title;
        TextView time;

        public ViewHolder(View view){
            time = (TextView) view.findViewById(R.id.tv_time);
            title = (TextView) view.findViewById(R.id.tv_content);
        }

    }
}
