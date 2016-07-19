package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.com.hz_project.model.bean.Title;
import cn.com.projectdemos.R;

/**
 * Created by peng on 2016/7/18.
 */
public class LingdaoAdapter extends ArrayAdapter<Title> {
    private int resourceId;
    public LingdaoAdapter(Context context, int resource, List<Title> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        Title fruit = getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView fruitImage = (ImageView) view.findViewById(R.id.photo);
        TextView fruitName = (TextView) view.findViewById(R.id.name);
        TextView date = (TextView) view.findViewById(R.id.date);
        TextView jianjie = (TextView) view.findViewById(R.id.jianjie);
        fruitImage.setImageResource(fruit.getIamge());
        fruitName.setText(fruit.getName());
        date.setText(fruit.getDate());
        jianjie.setText(fruit.getJianjie());
        return view;
    }
}
