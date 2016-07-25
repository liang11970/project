package cn.com.hz_project.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.StaffBeanModle;
import cn.com.hz_project.model.server.MeetingService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.projectdemos.R;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeetingStaffPieActivity extends Activity implements View.OnClickListener {

    @InjectView(R.id.iv_back_meeting)
    ImageView ivBackMeeting;
    @InjectView(R.id.title_meeting)
    RelativeLayout titleMeeting;
    @InjectView(R.id.bt_staff_listing)
    Button btStaffListing;
    @InjectView(R.id.tv_back)
    TextView tvBack;
    @InjectView(R.id.chart)
    PieChart chart;
    private MeetingService meetingService;
    private String meetingID;
    private StaffBeanModle staffdata;
    private PieChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_pie);
        ButterKnife.inject(this);

        initview();
//        initData();
        mChart = (PieChart) findViewById(R.id.chart);
        PieData pieData = getPie(4, 100);
        showChart(mChart, pieData);

    }

    private PieData getPie(int count, int range) {
        ArrayList<String> content = new ArrayList<String>();

        content.add(0, "教育部");
        content.add(1, "技术部");
        content.add(2, "保障部");
        content.add(3, "商务部");


        ArrayList<Entry> data = new ArrayList<>();
        float num0 = 22;
        float num1 = 33;
        float num2 = 11;
        float num3 = 30;

        data.add(new Entry(num0, 0));
        data.add(new Entry(num1, 1));
        data.add(new Entry(num2, 2));
        data.add(new Entry(num3, 3));

        PieDataSet pieDataSet = new PieDataSet(data, "各部门到场人数");
        pieDataSet.setSliceSpace(1f); /**饼图间间隔*/


        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#b284f3"));
        colors.add(Color.parseColor("#fe7c2e"));
        colors.add(Color.parseColor("#7494d6"));
        colors.add(Color.parseColor("#42c0fa"));
        pieDataSet.setColors(colors);  /**设置颜色*/


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px);

        PieData pieData = new PieData(content, pieDataSet);
        pieData.setValueTextSize(14f);
        pieData.setValueTextColor(getResources().getColor(R.color.white));
        pieData.setDrawValues(true);
        return pieData;

    }

    private void showChart(PieChart mChart, PieData pieData) {
        mChart.setHoleRadius(50f);
        mChart.setTransparentCircleRadius(54f);
        mChart.setDrawCenterText(true);
        mChart.setDrawHoleEnabled(true);
        mChart.setCenterText("签到人数占总人数百分比");
        mChart.setCenterTextSize(14);  //饼图中间文字大小
        mChart.setDescriptionTextSize(12);  //饼图模块描述字体大小
        mChart.setRotationAngle(90);

        mChart.setNoDataText("正在加载数据");
        mChart.setRotationEnabled(true);
        mChart.setDrawSliceText(true); //是否显示各模块在饼图名称
        mChart.setUsePercentValues(true); //

        mChart.setDescription(null);

        mChart.setData(pieData);

        Legend legend = mChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
//        legend.setFormSize(10f);
        legend.setTextSize(14);
        legend.setXEntrySpace(5f);
        legend.setYEntrySpace(5f);
        legend.setWordWrapEnabled(true);


        mChart.animateXY(1000, 1000);


    }

    private void initview() {
        ivBackMeeting.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        btStaffListing.setOnClickListener(this);
    }

    private void initData() {
        meetingID = (String) getIntent().getExtras().get("meetingID");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        meetingService = retrofit.create(MeetingService.class);

        meetingService.getStaffData(meetingID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StaffBeanModle>() {


                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.show(getApplicationContext(), "请求数据失败,请检查网络");

                    }

                    @Override
                    public void onNext(StaffBeanModle staffBean) {
                        Log.e("饼图", staffBean.getObj().toString());
                        staffdata = staffBean;
                        PieData pieData = getPie(4, 100);
                        showChart(mChart, pieData);


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
            case R.id.bt_staff_listing:
                Intent intent = new Intent(this, MeetingStaffListingActivity.class);
                intent.putExtra("meetingIDa", meetingID);
                startActivity(intent);

        }
    }
}
