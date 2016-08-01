package cn.com.hz_project.view.activity;

import android.net.Uri;
import android.os.Bundle;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.orhanobut.logger.Logger;

import cn.com.hz_project.view.base.BaseActivity;
import cn.com.projectdemos.R;

public class VideoActivity extends BaseActivity implements EasyVideoCallback {

    private static final String TEST_URL = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private EasyVideoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        player = (EasyVideoPlayer) findViewById(R.id.player);

        // Sets the callback to this Activity, since it inherits EasyVideoCallback
        player.setCallback(this);

        // Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))
        player.setSource(Uri.parse(TEST_URL));
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    public void onStarted(EasyVideoPlayer player) {
        Logger.e("onStarted");

    }

    @Override
    public void onPaused(EasyVideoPlayer player) {
        Logger.e("onPaused");
    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {
        Logger.e("onPreparing");
    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {
        Logger.e("onPrepared");
    }

    @Override
    public void onBuffering(int percent) {
        Logger.e("onBuffering");
    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {
        Logger.e("onError");
    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
        Logger.e("onCompletion");
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {
        Logger.e("onRetry");
    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {
        Logger.e("onSubmit");

    }
}
