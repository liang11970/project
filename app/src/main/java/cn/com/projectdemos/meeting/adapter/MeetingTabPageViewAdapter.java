package cn.com.projectdemos.meeting.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import cn.com.projectdemos.meeting.fragment.MeetingScreenFragment;
import cn.com.projectdemos.meeting.fragment.MeetingVoteFragment;

/**
 * Created by wee on 16/6/20.
 */
public class MeetingTabPageViewAdapter extends FragmentStatePagerAdapter {


    private Context mContext;

    /* 学习圈tab适配器 */
    public MeetingTabPageViewAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int index) {
        MeetingVoteFragment meetingVoteFragment  = new MeetingVoteFragment();   //投票
        MeetingScreenFragment meetingScreenFragment = new MeetingScreenFragment();  //扫描签到


       if(index==0){
           return meetingScreenFragment;
       }else{
           return meetingVoteFragment;
       }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
