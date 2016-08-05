package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.com.hz_project.model.bean.VideoResult;
import cn.com.projectdemos.R;

/**
 * Created by hcc on 16/4/4.
 */
public class MainViewPagerAdapter extends PagerAdapter {

    private List<VideoResult.ObjBean> tops = new ArrayList<>();

    private Context mContext;


    public MainViewPagerAdapter(Context context, List<VideoResult.ObjBean> tops) {

        this.tops = tops;
        this.mContext = context;
    }

    @Override
    public int getCount() {

        return tops.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);
        ImageView mImg = (ImageView) view.findViewById(R.id.pager_img);
        TextView mTitle = (TextView) view.findViewById(R.id.pager_title);

        Glide.with(mContext).load(tops.get(position).getVF_IMG_URL()).into(mImg);
        mTitle.setText(tops.get(position).getVF_VIDEO_NAME());
//        final int id = tops.get(position).getId();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DailyDetailActivity.lanuch(mContext, id);
                /**
                 * 点击事件
                 */

            }
        });
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }
}
