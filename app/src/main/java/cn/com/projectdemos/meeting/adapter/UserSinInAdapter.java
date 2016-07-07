package cn.com.projectdemos.meeting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.projectdemos.R;

/**
 * Created by wee on 16/6/21.
 */
public class UserSinInAdapter extends BaseAdapter{

    private LayoutInflater mInflater;


    Context mContext;
    @Override
    public int getCount() {
        return 10;
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


        View view = mInflater.inflate(R.layout.meeting_user_sin_in_item, null);
        return view;
    }



    public UserSinInAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    class ViewHolder{

        ImageView userIcLabel;
        TextView meetingName;
        TextView meetingAddress;
        TextView meetingTime;


    }


}
