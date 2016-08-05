package cn.com.hz_project.view.activity;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.HttpResult;
import cn.com.projectdemos.R;

/**
 * Created by hcc on 16/4/2.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    /**
     *
     */
    private static final int ITEM_CONTENT = 0;

    /**
     *
     */
    private static final int ITEM_TIME = 1;

    /**
     * 数据源
     */
    private List<HttpResult.ObjBean> newsList = new ArrayList<>();


    /**
     * 上下文
     */
    private Context mContext;


    public NewsListAdapter(Context context, List<HttpResult.ObjBean> mNewsList) {
        this.newsList = mNewsList;
        this.mContext = context;
    }

    /**
     * 创建viewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new, parent, false));
    }

    /**
     * 赋值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(NewsListAdapter.NewsViewHolder holder, int position) {
        Picasso.with(mContext).load(newsList.get(position).getNBD_PICTURE_URL()).into(holder.newsImage);
        holder.newsContent.setText(newsList.get(position).getNBD_TITLE());
        holder.newsTime.setText(newsList.get(position).getTIME());
    }


    /**
     * 更新数据并刷新列表
     * @param newsList
     */
    public void updateData(List<HttpResult.ObjBean> newsList) {

        this.newsList = newsList;
        notifyDataSetChanged();
    }

    /**
     * 增加数据
     * @param newsList
     */
    public void addData(List<HttpResult.ObjBean> newsList) {

        if (this.newsList == null) {
            updateData(newsList);
        } else {
            this.newsList.addAll(newsList);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {

        return newsList.size() == 0 ? 0 : newsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.iv_picasso)
        ImageView newsImage;

        @InjectView(R.id.tv_time)
        TextView newsTime;

        @InjectView(R.id.tv_content)
        TextView newsContent;

        public NewsViewHolder(View view){
            super(view);
            ButterKnife.inject(this,view);
        }
    }

}
