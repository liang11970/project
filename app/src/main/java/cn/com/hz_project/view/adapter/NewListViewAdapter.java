package cn.com.hz_project.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import cn.com.hz_project.model.bean.HttpResult;
import cn.com.hz_project.view.webveiw.WebViewBaseActivity;
import cn.com.projectdemos.R;

/**
 * ==================================
 * Created by wangl on 2016/7/16.时间14:50
 * <p>
 * 版本1.0
 * <p>
 * 描述
 * <p>
 * ================================
 */
public class NewListViewAdapter extends BaseAdapter {

    protected final static int CLICK_INDEX_ITEM = 0;
    // 关键字
    protected final static String BUNDLE_KEY_LIDATA = "lidata";
    private final Transformation mTransformation;
    private LayoutInflater mInflater = null;
    private Context mContext;
    // 记录Activity中接受消息的Handler
    private Handler mHandle;

    private  List<HttpResult.ObjBean> mlist;

    public NewListViewAdapter(Context context, List<HttpResult.ObjBean> list)
    {
        //根据context上下文加载布局
        this.mInflater = LayoutInflater.from(context);
        this.mContext=context;
        this.mlist=list;

        mTransformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(10)
//                .borderColor(Color.BLACK)
//                .borderWidthDp(3)
                .oval(false)
                .build();



    }
    public void onDateChange(List<HttpResult.ObjBean> mapList) {
        this.mlist = mapList;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mlist==null?0:mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        //如果缓存convertView为空，则需要创建View
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            //根据自定义的Item布局加载布局R
            convertView = mInflater.inflate(R.layout.item_new, null);
          viewHolder.iv_picasso=(ImageView) convertView.findViewById(R.id.iv_picasso);
          viewHolder.tv_title=(TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_time=(TextView)convertView.findViewById(R.id.tv_time);
            viewHolder.tv_content=(TextView)convertView.findViewById(R.id.tv_content);

            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder)convertView.getTag();


        }


        viewHolder.tv_title.setText(mlist.get(i).getNBD_TITLE());
        viewHolder.tv_time.setText(mlist.get(i).getTIME());
        viewHolder.tv_content.setText(mlist.get(i).getSUBSTR());


        Picasso.with(mContext).load(mlist.get(i).getNBD_PICTURE_URL()).fit().transform(mTransformation).into(viewHolder.iv_picasso);


        convertView.setOnClickListener(new OnItemChildClickListener(CLICK_INDEX_ITEM,i));

        return convertView;
    }


    //ViewHolder静态类
    private static class ViewHolder
    {
        public ImageView iv_picasso;
        public TextView tv_title;
        public TextView tv_time;
        public TextView tv_content;
//        public TextView week;


    }



    private class OnItemChildClickListener implements View.OnClickListener {
                // 点击类型索引，对应前面的CLICK_INDEX_xxx
        private int clickIndex;
                // 点击列表位置
        private int position;

        public OnItemChildClickListener(int clickIndex, int position) {
            this.clickIndex = clickIndex;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // 创建Message并填充数据，通过mHandle联系Activity接收处理
            mContext.startActivity(new Intent(mContext, WebViewBaseActivity.class));
        }

    }
}
