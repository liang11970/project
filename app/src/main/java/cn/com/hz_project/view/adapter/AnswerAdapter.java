package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.com.hz_project.model.bean.QuizAnswerObj;
import cn.com.hz_project.tools.utils.LogUtils;
import cn.com.projectdemos.R;

/**
 * Created by ku on 2016/7/19.
 */
public class AnswerAdapter extends BaseAdapter{
    private ArrayList<QuizAnswerObj> list;
    private Context context;

    public AnswerAdapter(ArrayList<QuizAnswerObj> list,Context context){
        this.list = list;
        this.context = context;
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
        MyViewHolder myViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_answer, null);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        }
        else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        String a = list.get(position).getUBD_REAL_NAME();
        String b = list.get(position).getWAD_CONTEXT();
        String c = list.get(position).getTIME();


        LogUtils.i("------------>",a+"---"+b+"---"+c);
        myViewHolder.replyer.setText(list.get(position).getUBD_REAL_NAME());
        myViewHolder.content.setText(list.get(position).getWAD_CONTEXT());
        myViewHolder.time.setText(list.get(position).getTIME());


        return convertView;
    }

    class MyViewHolder {
        TextView replyer;
        TextView time;
        TextView content;

        public MyViewHolder(View view) {
            replyer = (TextView) view.findViewById(R.id.replyer);
            time = (TextView) view.findViewById(R.id.time);
            content = (TextView) view.findViewById(R.id.content);
        }
    }
}
