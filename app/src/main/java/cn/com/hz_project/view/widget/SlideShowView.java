package cn.com.hz_project.view.widget;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import butterknife.OnClick;
import cn.com.hz_project.view.activity.TwoActivity;
import cn.com.projectdemos.R;


public class SlideShowView extends FrameLayout implements View.OnClickListener{
	
	private ImageLoader imageLoader = ImageLoader.getInstance();

    private final static int IMAGE_COUNT = 5;
    private final static int TIME_INTERVAL = 5;
    private final static boolean isAutoPlay = true;
    
    private String[] imageUrls;
    private List<ImageView> imageViewsList;
    private List<View> dotViewsList;
    
    private ViewPager viewPager;
    private int currentItem  = 0;
    private ScheduledExecutorService scheduledExecutorService;
    
    private Context context;
    
    //Handler
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            viewPager.setCurrentItem(currentItem);
        }
        
    };
    
    public SlideShowView(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }
    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;

		initImageLoader(context);
		
        initData();
        if(isAutoPlay){
            startPlay();
        }
        
    }

    private void startPlay(){
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
    }

    private void stopPlay(){
        scheduledExecutorService.shutdown();
    }

    private void initData(){
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<View>();

        new GetListTask().execute("");
    }

    private void initUI(Context context){
    	if(imageUrls == null || imageUrls.length == 0)
    		return;
    	
        LayoutInflater.from(context).inflate(R.layout.layout_slideshow, this, true);
        
        LinearLayout dotLayout = (LinearLayout)findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();
        

        for (int i = 0; i < imageUrls.length; i++) {
        	ImageView view =  new ImageView(context);
        	view.setTag(imageUrls[i]);
        	if(i==0)
        		view.setBackgroundResource(R.mipmap.appmain_subject);
        	view.setScaleType(ScaleType.FIT_XY);
        	imageViewsList.add(view);
        	
        	ImageView dotView =  new ImageView(context);
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        	params.leftMargin = 4;
			params.rightMargin = 4;
			dotLayout.addView(dotView, params);
        	dotViewsList.add(dotView);
		}
        
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setFocusable(true);
        
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), TwoActivity.class);
                        getContext().startActivity(intent);
    }


    private class MyPagerAdapter  extends PagerAdapter{

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            //((ViewPag.er)container).removeView((View)object);
            ((ViewPager)container).removeView(imageViewsList.get(position));
        }

        @Override
        public Object instantiateItem(View container, int position) {
        	ImageView imageView = imageViewsList.get(position);

			imageLoader.displayImage(imageView.getTag() + "", imageView);
        	
            ((ViewPager)container).addView(imageViewsList.get(position));
            return imageViewsList.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
            
        }
        
    }

    private class MyPageChangeListener implements OnPageChangeListener{

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
            case 1:
                isAutoPlay = false;
                break;
            case 2:
                isAutoPlay = true;
                break;
            case 0:
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                    viewPager.setCurrentItem(0);
                }
                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1);
                }
                break;
        }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            
            currentItem = pos;
            for(int i=0;i < dotViewsList.size();i++){
                if(i == pos){
                    ((View)dotViewsList.get(pos)).setBackgroundResource(R.mipmap.dot_focus);
                }else {
                    ((View)dotViewsList.get(i)).setBackgroundResource(R.mipmap.dot_blur);
                }
            }
        }
        
    }
    

    private class SlideShowTask implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (viewPager) {
                currentItem = (currentItem+1)%imageViewsList.size();
                handler.obtainMessage().sendToTarget();
            }
        }
        
    }
    

    private void destoryBitmaps() {

        for (int i = 0; i < IMAGE_COUNT; i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                drawable.setCallback(null);
            }
        }
    }
 


	class GetListTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected Boolean doInBackground(String... params) {
			try {

				
				imageUrls = new String[]{
						"http://pic25.nipic.com/20121112/5955207_224247025000_2.jpg",
						"http://img5.imgtn.bdimg.com/it/u=1864751896,666907639&fm=21&gp=0.jpg",
						"http://pic17.nipic.com/20111109/7674259_144501034328_2.jpg",
						"http://pic14.nipic.com/20110615/1347158_233357498344_2.jpg"
				};
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			if (result) {
		        initUI(context);
			}
		}
	}
	
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs()
				.build();
		ImageLoader.getInstance().init(config);
	}
}