package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.StaffBean;
import cn.com.hz_project.tools.url.Urls;
import cn.com.projectdemos.R;

/**
 * Created by Tan on 2016/7/21 0021.
 */
public class StaffAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<StaffBean.ObjBean> mData;

    public StaffAdapter(Context context, List<StaffBean.ObjBean> staffdata) {
        this.mContext = context;
        this.mData = staffdata;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_meeting_staff, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvPstnName = (TextView) convertView.findViewById(R.id.tv_pstn_name);
            viewHolder.tvDeptName = (TextView) convertView.findViewById(R.id.tv_dept_name);
            viewHolder.ivHeadX = (ImageView) convertView.findViewById(R.id.iv_headX);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(mData.get(position).getUBD_REAL_NAME());
        viewHolder.tvDeptName.setText(mData.get(position).getUBD_DEPT_NAME());
        viewHolder.tvPstnName.setText(mData.get(position).getUBD_PSTN_NAME());

        Picasso.with(mContext)
                .load(Urls.touxiang+mData.get(position).getUBD_IMG_URL())
                .error(R.mipmap.man_error)
                .into(viewHolder.ivHeadX);

        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.iv_headX)
        ImageView ivHeadX;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.tv_dept_name)
        TextView tvDeptName;
        @InjectView(R.id.tv_pstn_name)
        TextView tvPstnName;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
