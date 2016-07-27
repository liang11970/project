package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import cn.com.hz_project.model.server.PreferencesService;
import cn.com.projectdemos.R;

public class MainActivity extends Activity {
    private Handler mHandler = new Handler();
    private ImageView iv_welcome;
    Animation alphaAnim;
    private int alpha = 255;
    private int b = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mai);


        SharedPreferences preferences=getSharedPreferences("logintag", Context.MODE_PRIVATE);
        Boolean tag=preferences.getBoolean("tag", false);

        if(tag){
            startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
            finish();
        }else {
            iv_welcome=(ImageView) findViewById(R.id.iv_welcome);

            alphaAnim = new AlphaAnimation(0.1f,1.0f);
            alphaAnim.setDuration(3000);
            alphaAnim.setFillBefore(true);
            iv_welcome.startAnimation(alphaAnim);

            iv_welcome.setAlpha(alpha);
            new Thread(new Runnable() {
                public void run() {
                    while (b < 2) {
                        try {
                            if (b == 0) {
                                Thread.sleep(500);
                                b = 1;
                            } else {
                                Thread.sleep(100);
                            }

                            updateApp();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();

            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    iv_welcome.setAlpha(alpha);
                    iv_welcome.invalidate();
                }
            };
        }



    }
    public void updateApp() {
        alpha -= 11;
        //避免出现白屏
        if (alpha <= 30) {
            b = 2;
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            this.finish();
            //查询需要很多内存开销，提前回收一些
            System.gc();
        }

        mHandler.sendMessage(mHandler.obtainMessage());
    }
}
