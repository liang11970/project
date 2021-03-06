package cn.com.hz_project.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.view.base.ViewHolder;
import cn.com.projectdemos.R;

/**
 * Created by hcc on 16/4/2.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    /**
     * 条目布局
     */
    private View view;

    /**
     * 数据源
     */
    private List<HttpResult.ObjBean> newsList = new ArrayList<>();


    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 意图
     */
    private Intent mIntent;

//    /**
//     * 条目点击接口
//     */
//    private MyItemClickListener mMyItemClickListener;

    public NewsListAdapter(Context context, List<HttpResult.ObjBean> mNewsList) {
        this.newsList = mNewsList;
        this.mContext = context;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    /**
     * 创建viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new, parent, false);
        return new NewsViewHolder(view);
    }

    /**
     * 赋值
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(NewsListAdapter.NewsViewHolder holder, int position) {
        Picasso.with(mContext).load(newsList.get(position).getNBD_PICTURE_URL()).into(holder.newsImage);

        holder.newsContent.setText(newsList.get(position).getNBD_TITLE());

        holder.newsTime.setText(newsList.get(position).getTIME());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent = new Intent(mContext,NewContentActivity.class);
                mIntent.putExtra("id", newsList.get(position).getNBD_ID());
                mContext.startActivity(mIntent);
            }
        });
    }

    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    /**
     * 更新数据并刷新列表
     *
     * @param newsList
     */
    public void updateData(List<HttpResult.ObjBean> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    /**
     * 增加数据
     *
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


    /**
     * viewHolder实现条目点击
     */
    public class NewsViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_picasso)
        ImageView newsImage;

        @InjectView(R.id.tv_time)
        TextView newsTime;

        @InjectView(R.id.tv_content)
        TextView newsContent;

        @InjectView(R.id.start_time_repairs)
        LinearLayout layout;

        public NewsViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);

        }
    }
}
