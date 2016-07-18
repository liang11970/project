package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import cn.com.projectdemos.R;

/**
 * Created by ku on 2016/7/18.
 */
public class QuizAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<String> list;

    public QuizAdapter(Context context,ArrayList<String> list){
        this.context = context;
        this.list = list;
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_quiz,null);


        return convertView;
    }

    class MyViewHolder{
        TextView title;
        TextView time;
        TextView content;

        public MyViewHolder(View view){
            title = (TextView) view.findViewById(R.id.tv_quiz_title);
            time = (TextView) view.findViewById(R.id.tv_quiz_time);
            content = (TextView) view.findViewById(R.id.tv_quiz_content);
        }
    }
}
