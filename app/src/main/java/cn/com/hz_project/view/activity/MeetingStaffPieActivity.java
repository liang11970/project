package cn.com.hz_project.view.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.bean.SignInStaffBean;
import cn.com.hz_project.model.server.MeetingService;
import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.projectdemos.R;
import cn.com.projectdemos.utils.ColorUtil;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
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
    @InjectView(R.id.ll_legend)
    LinearLayout llLegend;
    @InjectView(R.id.tv_null)
    TextView tvNull;
    private MeetingService meetingService;
    private String meetingID;
    private SignInStaffBean staffdata;
    private PieChart mChart;
    /**
     * 总人数
     */
    private int total;
    private List<SignInStaffBean.ObjBean.ListBean> list;
    private String[] labels;   //饼图标签
    //    private float[] datas;   //饼图的数据
    private int[] colors;   //饼图标签颜色
    private ArrayList<Float> percentList;  //百分比的集合
    private ArrayList<Integer> numberList;   //部门人数 集合


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_pie);
        ButterKnife.inject(this);

        initview();
        initData();


//        PieData pieData = getPie(departmentList, dataList);
//        showChart(mChart, pieData);

    }


    public static String[] toStringArray(List<String> strList) {
        String[] array = new String[strList.size()];
        strList.toArray(array);
        return array;
    }

    private PieData getPie(ArrayList<String> departmentList, ArrayList<Entry> dataList) {
//        ArrayList<String> content = new ArrayList<String>();
//
//        content.add(0, "教育部");
//        content.add(1, "技术部");
//        content.add(2, "保障部");
//        content.add(3, "商务部");


//        ArrayList<Entry> data = new ArrayList<>();
//        float num0 = 22;
//        float num1 = 33;
//        float num2 = 11;
//        float num3 = 30;
//
//        data.add(new Entry(num0, 0));
//        data.add(new Entry(num1, 1));
//        data.add(new Entry(num2, 2));
//        data.add(new Entry(num3, 3));

        PieDataSet pieDataSet = new PieDataSet(dataList, "各部门到场人数");
        pieDataSet.setSliceSpace(1f); /**饼图间间隔*/


        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int i = 0; i < departmentList.size(); i++) {
            colors.add(Color.parseColor("#" + ColorUtil.getRandColorCode()));
        }

        pieDataSet.setColors(colors);  /**设置颜色*/


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px);

        PieData pieData = new PieData(departmentList, pieDataSet);
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
        mChart.setDescription("到场总人数:" + total + "人");   //饼图介绍,右下角
        mChart.setRotationAngle(90);

        mChart.setNoDataText("正在加载数据");
        mChart.setRotationEnabled(true);
        mChart.setDrawSliceText(true); //是否显示各模块在饼图名称
        mChart.setUsePercentValues(true); //


        mChart.setData(pieData);

        Legend legend = mChart.getLegend();
//        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//        legend.setFormSize(10f);

        legend.setEnabled(false);
        colors = legend.getColors();
        labels = legend.getLabels();

        customizeLegend();


        legend.setTextSize(14);
        legend.setXEntrySpace(2f);
        legend.setYEntrySpace(2f);
        legend.setWordWrapEnabled(true);


        mChart.animateXY(1000, 1000);


    }

    /**
     * 自定义legend，动态的生成布局
     */
    private void customizeLegend() {
        for (int i = 0; i < numberList.size(); i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.
                    LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120);
//            lp.weight = 1;//设置比重为1
            LinearLayout.LayoutParams tvP = new LinearLayout.
                    LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            tvP.weight = 1;//设置比重为1

            LinearLayout layout = new LinearLayout(this);//单个图例的布局
            layout.setOrientation(LinearLayout.HORIZONTAL);//水平排列
            layout.setGravity(Gravity.CENTER_VERTICAL);//垂直居中
            layout.setLayoutParams(lp);

            //添加color
            LinearLayout.LayoutParams colorLP = new LinearLayout.
                    LayoutParams(40, 40);
            colorLP.setMargins(0, 0, 20, 0);
            LinearLayout colorLayout = new LinearLayout(this);
            colorLayout.setLayoutParams(colorLP);
            colorLayout.setBackgroundColor(colors[i]);
            layout.addView(colorLayout);

            //添加label
            TextView labelTV = new TextView(this);
            labelTV.setText(labels[i] + "");
            labelTV.setGravity(Gravity.CENTER);
            labelTV.setLayoutParams(tvP);
            layout.addView(labelTV);

            //添加data
            TextView dataTV = new TextView(this);
            dataTV.setText(numberList.get(i) + "人");
            dataTV.setGravity(Gravity.CENTER);
            dataTV.setLayoutParams(tvP);
            layout.addView(dataTV);

            //添加百分比
            TextView percentTV = new TextView(this);
            percentTV.setText(percentList.get(i) + "%");
            percentTV.setGravity(Gravity.CENTER);
            percentTV.setLayoutParams(tvP);
            layout.addView(percentTV);

            llLegend.addView(layout);//legendLayout为外层布局即整个图例布局，是在xml文件中定义

        }
    }

    private void initview() {
        mChart = (PieChart) findViewById(R.id.chart);
        ivBackMeeting.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        btStaffListing.setOnClickListener(this);
    }

    private void initData() {
        percentList = new ArrayList<Float>();
        numberList = new ArrayList<Integer>();

        meetingID = (String) getIntent().getExtras().get("meetingID");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        meetingService = retrofit.create(MeetingService.class);

        meetingService.getSignInStaff(meetingID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignInStaffBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("错误:", e.toString());
                        ToastUtils.show(getApplicationContext(), "请求数据失败,请检查网络");
                        tvNull.setVisibility(View.VISIBLE);


                    }

                    @Override
                    public void onNext(SignInStaffBean signInStaffBean) {

                        Log.e("饼图,total数据是", signInStaffBean.getObj().getTotal() + "");
                        staffdata = signInStaffBean;

                        total = staffdata.getObj().getTotal();
                        list = staffdata.getObj().getList();

                        if (list.size() < 1) {
                            tvNull.setVisibility(View.VISIBLE);
                            chart.setVisibility(View.INVISIBLE);
                            return;
                        }

                        Log.e("饼图,list数据为", staffdata.toString());
                        ArrayList<String> departmentList = new ArrayList<>();
                        ArrayList<Entry> dataList = new ArrayList<Entry>();

                        for (int i = 0; i < list.size(); i++) {
                            if (i == 0 || !departmentList.contains(list.get(i).getDBD_DEPT_NAME())) {
                                departmentList.add(list.get(i).getDBD_DEPT_NAME());
                                int count = list.get(i).getCOUNT();
                                float percent = getPercent(total, count);//得到百分比
                                numberList.add(count);
                                percentList.add(percent);
                                dataList.add(new Entry(percent, i));

                            }

                        }

                        PieData pieData = getPie(departmentList, dataList);
                        showChart(mChart, pieData);
                    }
                });
    }

    private float getPercent(float total, float count) {

        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);

        if (total >= 1 && count >= 1) {

            String result = numberFormat.format(count / total * 100);
            float v = Float.parseFloat(result);
            return v;
        } else {
            return 0;
        }
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
//                Intent intent = new Intent(this, MeetingStaffListingActivity.class);
//                intent.putExtra("meetingIDa", meetingID);
//                startActivity(intent);

        }
    }
}
