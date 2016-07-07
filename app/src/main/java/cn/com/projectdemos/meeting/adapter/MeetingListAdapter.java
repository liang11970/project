package cn.com.projectdemos.meeting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.com.projectdemos.R;
import cn.com.projectdemos.model.MeetingItemMode;

/**
 * Created by wee on 16/6/21.
 */
public class MeetingListAdapter extends BaseAdapter{

    private LayoutInflater mInflater;

    private MeetingItemMode meetingItemMode;
    View view;
    private ArrayList<MeetingItemMode.MeetingInfoMode> rows;


    Context mContext;
    @Override
    public int getCount() {
        return meetingItemMode.rows.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        view = mInflater.inflate(R.layout.meeting_user_sin_in_item, null);
        ViewHolder holder=new ViewHolder();
        holder.meetingAddress = (TextView)view.findViewById(R.id.meeting_address);


        holder.meetingAddress = (TextView)view.findViewById(R.id.meeting_address);
        holder.meetingTime = (TextView) view.findViewById(R.id.meeting_time);
        holder.meetingName = (TextView) view.findViewById(R.id.meeting_name);



        holder.meetingName.setText(meetingItemMode.rows.get(position).hy_mc.toString());
        holder.meetingAddress.setText(meetingItemMode.rows.get(position).bz.toString());


        long l = Long.parseLong(meetingItemMode.rows.get(position).hy_kssj);
        Date date=new Date(l);
        SimpleDateFormat format=new SimpleDateFormat("MM-dd HH:mm:ss");
        String str=format.format(date);


        holder.meetingTime.setText(str);
        return view;


    }



    public MeetingListAdapter(Context context,MeetingItemMode meetingItemMode) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.meetingItemMode = meetingItemMode;
    }

    class ViewHolder{


        public TextView meetingName;
        public TextView meetingAddress;
        public TextView meetingTime;


    }


}
