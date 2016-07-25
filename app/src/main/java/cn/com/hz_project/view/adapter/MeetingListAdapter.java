package cn.com.hz_project.view.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.MeetingBean;
import cn.com.hz_project.model.bean.MeetingListBean;
import cn.com.projectdemos.R;

/**
 * Created by Tan on 2016/7/16 0016.f
 */
public class MeetingListAdapter extends BaseAdapter {
    private Context mContext;
    private List<MeetingListBean.ObjBean> mMeetList;
    private int isSign;

    public MeetingListAdapter(Context context, List<MeetingListBean.ObjBean> meetList) {
        this.mContext = context;
        this.mMeetList = meetList;
    }


    @Override
    public int getCount() {
        return mMeetList.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= mMeetList.size() || position == 0) {
            return null;
        } else {
            return mMeetList.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_meeting, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.tvMeetingName = (TextView) convertView.findViewById(R.id.tv_Meeting_Name);
            viewHolder.tvMeetingTime = (TextView) convertView.findViewById(R.id.tv_Meeting_Time);
            viewHolder.tvMeetingContent = (TextView) convertView.findViewById(R.id.tv_Meeting_content);
            viewHolder.tvSignInState = (TextView) convertView.findViewById(R.id.tv_SignIn_State);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvMeetingName.setText(mMeetList.get(position).getMBD_NAME());
        String etime = mMeetList.get(position).getETIME();
        String stime = mMeetList.get(position).getSTIME();
        String substringE = etime.substring(etime.length()-8, etime.length()-3);
        String substringS = stime.substring(0, stime.length()-3);
        viewHolder.tvMeetingTime.setText("会议时间:"+substringS+"-"+substringE);
        viewHolder.tvMeetingContent.setText(mMeetList.get(position).getMBD_REMARKS() + "");
        isSign = mMeetList.get(position).getIsSign();
        if (isSign==1){
            viewHolder.tvSignInState.setText("未签到");

        }else if (isSign==0){
            viewHolder.tvSignInState.setText("已签到");
        }
        return convertView;
    }


    static class ViewHolder {
        @InjectView(R.id.iv_meeting)
        ImageView ivMeeting;
        @InjectView(R.id.tv_Meeting_Name)
        TextView tvMeetingName;
        @InjectView(R.id.tv_Meeting_Time)
        TextView tvMeetingTime;
        @InjectView(R.id.tv_Meeting_content)
        TextView tvMeetingContent;
        @InjectView(R.id.tv_SignIn_State)
        TextView tvSignInState;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
