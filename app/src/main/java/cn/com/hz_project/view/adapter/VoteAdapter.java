package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;

import cn.com.hz_project.model.bean.Vote;
import cn.com.hz_project.model.bean.VoteObj;
import cn.com.projectdemos.R;

/**
 * Created by ku on 2016/7/27.
 */
public class VoteAdapter extends BaseAdapter{
    private ArrayList<VoteObj> list;
    private Context context;

    public VoteAdapter(ArrayList<VoteObj> list,Context context){
        this.list = list;
        this.context = context;
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

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_vote,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(list.get(position).getVBD_TITLE());
        viewHolder.time.setText(list.get(position).getTIME());

        return convertView;
    }

    class ViewHolder{
        TextView title;
        TextView time;

        public ViewHolder(View view){
            time = (TextView) view.findViewById(R.id.time_vote);
            title = (TextView) view.findViewById(R.id.title_vote);
        }

    }
}
