package cn.com.hz_project.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.hz_project.model.bean.Login;
import cn.com.hz_project.model.bean.OnlineQuiz;
import cn.com.hz_project.model.bean.ServerFile;
import cn.com.hz_project.model.bean.ServerFileObj;
import cn.com.hz_project.model.server.LoginService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.LogUtils;
import cn.com.hz_project.tools.utils.Md5;
import cn.com.hz_project.view.activity.ViewPagerActivity;
import cn.com.hz_project.view.adapter.FileListAdapter;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SortFragment extends Fragment{


	private GridView gview;
	private ArrayList<ServerFileObj> list;
	private Retrofit retrofit;
	private LoginService loginService;
	private FileListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_sort, null);

		//初始化数据
		init(view);

		initListener(view);

		return view;
	}

	private void init(View view) {
		retrofit = new Retrofit.Builder()
				.baseUrl(Urls.baseURL)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();

		loginService = retrofit.create(LoginService.class);

		gview = (GridView) view.findViewById(R.id.bookshelf);

		loginService.getFileList()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<ServerFile>() {
					@Override
					public void onCompleted() {
						LogUtils.i("----------->","onCompleted");
					}

					@Override
					public void onError(Throwable e) {
						LogUtils.i("----------->","onError"+e.toString());
					}

					@Override
					public void onNext(ServerFile file) {
						LogUtils.i("----------->","onNext"+file.toString());

						list = file.getObj();
						adapter = new FileListAdapter(list,getActivity());
						gview.setAdapter(adapter);
					}
				});

	}

	private void initListener(View view){
		view.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewPagerActivity.transaction = ViewPagerActivity.fragmentManager.beginTransaction();
				Fragment sortFragment = new UpDateFragment();
				ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
				ViewPagerActivity.transaction.commit();
				Logger.e("点击了文件上传");
			}
		});

		view.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewPagerActivity.transaction = ViewPagerActivity.fragmentManager.beginTransaction();
				Fragment sortFragment = new DownloadFragment();
				ViewPagerActivity.transaction.replace(R.id.content, sortFragment);
				ViewPagerActivity.transaction.commit();
			}
		});
	}

}
