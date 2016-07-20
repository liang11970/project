package cn.com.hz_project.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.hz_project.view.activity.ViewPagerActivity;
import cn.com.projectdemos.R;

public class SortFragment extends Fragment{


	private GridView gview;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	private int[] icon = { R.mipmap.icon1, R.mipmap.icon1,
			R.mipmap.icon1, R.mipmap.icon1, R.mipmap.icon1,
			R.mipmap.icon1, R.mipmap.icon1};
	private String[] iconName = { "文献1", "文献2", "文献3", "文献4", "文献5", "文献6", "文献7"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_sort, null);

		gview = (GridView) view.findViewById(R.id.bookshelf);
		//新建List
		data_list = new ArrayList<Map<String, Object>>();
		//获取数据
		getData();
		//新建适配器
		String [] from ={"image","text"};
		int [] to = {R.id.image,R.id.text};
		sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.griditem, from, to);
		//配置适配器
		gview.setAdapter(sim_adapter);





		view.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(getActivity(), UpDateActivity.class));
				ViewPagerActivity.transaction = ViewPagerActivity.fragmentManager.beginTransaction();
				Fragment sortFragment = new UpDateFragment();
				ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
				ViewPagerActivity.transaction.commit();
			}
		});
		view.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(getActivity(), UpDateActivity.class));
				ViewPagerActivity.transaction = ViewPagerActivity.fragmentManager.beginTransaction();
				Fragment sortFragment = new DownloadFragment();
				ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
				ViewPagerActivity.transaction.commit();
				//ViewPagerActivity.fragmentManager.popBackStack();
			}
		});
		return view;
	}
	public List<Map<String, Object>> getData(){
		//cion和iconName的长度是相同的，这里任选其一都可以
		for(int i=0;i<icon.length;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", icon[i]);
			map.put("text", iconName[i]);
			data_list.add(map);
		}

		return data_list;
	}
}
