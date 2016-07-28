package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;

import cn.com.projectdemos.R;

/**
 * Created by ku on 2016/7/27.
 */
public class VoteAdapter extends BaseAdapter{
    private ArrayList<String> list;
    private Context context;

    public VoteAdapter(ArrayList<String> list,Context context){
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
        convertView = LayoutInflater.from(context).inflate(R.layout.item_vote,null);

        return convertView;
    }
}
