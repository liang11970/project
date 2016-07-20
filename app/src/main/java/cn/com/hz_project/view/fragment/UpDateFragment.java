package cn.com.hz_project.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.hz_project.view.activity.ViewPagerActivity;
import cn.com.projectdemos.R;

/**
 * Created by peng on 2016/7/15.
 */
public class UpDateFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.activity_up_date, null);
        view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.transaction =  ViewPagerActivity.fragmentManager.beginTransaction();
                Fragment sortFragment = new SortFragment();
                ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
                ViewPagerActivity.transaction.commit();
            }
        });
        return view;
    }
}
