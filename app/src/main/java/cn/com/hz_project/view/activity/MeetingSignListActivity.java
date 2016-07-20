package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.MeetingBean;
import cn.com.hz_project.model.server.MeetingService;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.hz_project.view.adapter.MeetingListAdapter;
import cn.com.hz_project.view.widget.LoadMoreListview;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeetingSignListActivity extends Activity implements View.OnClickListener, LoadMoreListview.IsLoadingListener {


    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.tv_addMeeting)
    TextView tvAddMeeting;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.lv_meeting)
    LoadMoreListview lvMeeting;
    @InjectView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;
    private MeetingService meetingService;
    private int pageNum = 1;
    private List<MeetingBean.ObjBean> meetList;
    private MeetingListAdapter meetingListAdapter;
    private List<MeetingBean.ObjBean> page2List;
    private int mtotalItemCout;
    private int lastItem;
    private boolean isLoading;
    private PopupWindow popupWindow;
    private LinearLayout ll_pop;
    private int longClickPosition;
    private PreferencesService preferencesService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);
        ButterKnife.inject(this);
        initView();
        initData();
    }

    private void initData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        meetingService = retrofit.create(MeetingService.class);


        meetingService.getMeetData(pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MeetingBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("请求失败", e.getMessage());
                        Toast.makeText(getApplicationContext(), "请求网络失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(MeetingBean meetingBean) {
                        Log.e("请求成功++", meetingBean.toString());
                        Log.e("请求成功++", "pageNum===" + pageNum + "");


                        if (pageNum == 1) {
                            meetList = meetingBean.getObj();
                            showData();
                        } else if (pageNum != 1) {
                            page2List = meetingBean.getObj();
                            addList();

                        }


                    }
                });


    }

    private void addList() {
        if (page2List.size() != 0) {
            for (MeetingBean.ObjBean bean : page2List) {
                meetList.add(bean);
            }
            meetingListAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.show(getApplicationContext(), "已无更多数据");
            pageNum--;
        }
    }


    private void showData() {
        meetingListAdapter = new MeetingListAdapter(this, meetList);
        lvMeeting.setOnLoadMordListener(this);
        lvMeeting.setAdapter(meetingListAdapter);


    }

    private void initView() {
        ivBackMeeting.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        tvAddMeeting.setOnClickListener(this);

        preferencesService = new PreferencesService(getApplicationContext());
        if (preferencesService.getPerferences().get("roleId").equals("9")) {
            tvAddMeeting.setVisibility(View.VISIBLE);
        }else {
            tvAddMeeting.setVisibility(View.INVISIBLE);
        }

        lvMeeting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MeetingDetailsActivity.class);
                intent.putExtra("name", meetList.get(position).getMBD_NAME());
                intent.putExtra("startTime", meetList.get(position).getSTIME());
                intent.putExtra("endTime", meetList.get(position).getETIME());
                intent.putExtra("content", meetList.get(position).getMBD_REMARKS());
                intent.putExtra("ID", meetList.get(position).getMBD_REMARKS());
                startActivity(intent);
            }
        });


        lvMeeting.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickPosition = position;
                View pop = view.inflate(getApplicationContext(), R.layout.item_pop_meeting, null);
                ll_pop = (LinearLayout) pop.findViewById(R.id.ll_pop);
                ll_pop.setOnClickListener(MeetingSignListActivity.this);
                if (popupWindow != null) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                popupWindow = new PopupWindow(pop, -2, -2);
                int[] location = new int[2];
                view.getLocationInWindow(location);
                popupWindow.showAtLocation(parent, Gravity.TOP + Gravity.CENTER_HORIZONTAL, 200, location[1]);
                return true;
            }
        });

        lvMeeting.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (lastItem == mtotalItemCout && scrollState == SCROLL_STATE_IDLE) {
                    if (!isLoading) {
                        onload();
                    }
                }

                if (popupWindow != null) {
                    popupWindow.dismiss();
                    popupWindow = null;

                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                mtotalItemCout = totalItemCount;
                lastItem = firstVisibleItem + visibleItemCount;

            }
        });


        initRefreshLayout();
    }

    /**
     * 下拉刷新
     */
    private void initRefreshLayout() {
        swipeContainer.setColorSchemeColors(R.color.colorPrimary);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                swipeContainer.setRefreshing(false);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_meeting:
                finish();
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_addMeeting:
                startActivity(new Intent(getApplicationContext(), AddMeetingActivity.class));
                break;
            case R.id.ll_pop:
                DeleteList();
        }
    }

    private void DeleteList() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            popupWindow = null;
        }
        meetList.remove(longClickPosition);
        meetingListAdapter.notifyDataSetChanged();
        ToastUtils.show(getApplicationContext(),"会议已删除");
    }

    @Override
    public void onload() {
        pageNum++;
        initData();

    }
}
