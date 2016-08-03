package cn.com.projectdemos.meeting.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.HashMap;
import java.util.List;

import cn.com.hz_project.model.bean.VideoResult;
import cn.com.projectdemos.R;


public class SearchResultRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<VideoResult.ObjBean> mValues;
    private boolean mIsStagger;
    private Activity activity;


    private RequestManager glideRequest;
    private OnDetailRecyclerViewListener onRecyclerViewListener;
    private HashMap<String, String> mMap;

    public SearchResultRecyclerViewAdapter(Activity activity, List<VideoResult.ObjBean> items) {
        mValues = items;
        this.activity = activity;

        if (activity != null) {
            glideRequest = Glide.with(activity);
        } else {
            return;
        }
    }


    public void setData(List<VideoResult.ObjBean> datas) {
        mValues = datas;
    }

    public void addDatas(List<VideoResult.ObjBean> datas) {
        mValues.addAll(datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_new, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final ViewHolder mHolder = (ViewHolder) holder;
        mHolder.mItem = mValues.get(position);



        mHolder.textviewShopitemToTijianName.setText(mHolder.mItem.getVF_UPDATE_TIME());
        mHolder.textviewShopitemToTijianDescript.setText(mHolder.mItem.getVF_VIDEO_NAME());


    }

    @Override
    public int getItemCount() {
        return mValues==null?0:mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;

        public VideoResult.ObjBean mItem;

        TextView textviewShopitemToTijianName;
        TextView textviewShopitemToTijianDescript;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textviewShopitemToTijianName = (TextView) view.findViewById(R.id.tv_time);
            textviewShopitemToTijianDescript = (TextView) view.findViewById(R.id.tv_content);
        }
    }


    public static interface OnDetailRecyclerViewListener {
        void onItemClickRecyler(int position, View view);
    }

    public void setOnDetailRecyclerViewListener(
            OnDetailRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public void addData(int pos, VideoResult.ObjBean comment) {
        mValues.add(pos, comment);
        notifyItemInserted(mValues.size());

    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        // TODO Auto-generated method stub
        super.setHasStableIds(hasStableIds);
    }


}
