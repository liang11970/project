package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.com.hz_project.model.bean.VideoResult;
import cn.com.hz_project.view.activity.VideoActivity;
import cn.com.hz_project.view.activity.VideoListActivity;
import cn.com.projectdemos.R;

/**
 * Created by hcc on 16/4/4.
 */
public class MainViewPagerAdapter extends PagerAdapter {

    private List<VideoResult.ObjBean> tops = new ArrayList<>();

    private Context mContext;

    private Intent mIntent;


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
        TextView moreVideo = (TextView) view.findViewById(R.id.morevideo);

        moreVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,VideoListActivity.class));
            }
        });

        Glide.with(mContext).load(tops.get(position).getVF_IMG_URL()).into(mImg);
        mTitle.setText(tops.get(position).getVF_VIDEO_NAME());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 点击事件
                 */
                Toast.makeText(mContext,"点击了第"+position+"项",Toast.LENGTH_SHORT).show();
                mIntent = new Intent(mContext, VideoActivity.class);
                mIntent.putExtra("url", tops.get(position).getVF_VIDEO_PATH());
                mContext.startActivity(mIntent);

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
