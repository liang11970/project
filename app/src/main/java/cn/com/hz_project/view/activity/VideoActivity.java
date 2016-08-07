package cn.com.hz_project.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.logger.Logger;

import cn.com.hz_project.tools.url.Urls;
import cn.com.hz_project.tools.utils.ToastUtils;
import cn.com.hz_project.view.base.BaseActivity;
import cn.com.projectdemos.R;

public class VideoActivity extends BaseActivity implements EasyVideoCallback {

    private static final String TEST_URL = "http://116.228.202.122:8080/WsbxMobile/page/video/1469783954230.mp4";
    private EasyVideoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        player = (EasyVideoPlayer) findViewById(R.id.player);

        // Sets the callback to this Activity, since it inherits EasyVideoCallback
        assert player !=null;
        player.setCallback(this);

        // Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))

        Bundle extras = getIntent().getExtras();
        String id = extras.getString("url");

Logger.e(id);

        player.setSource(Uri.parse(TEST_URL));
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
		//test
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    public void onStarted(EasyVideoPlayer player) {
        player.setSubmitText("播放中...");
        Logger.e("onStarted");

    }

    @Override
    public void onPaused(EasyVideoPlayer player) {

        player.setSubmitText("暂停中");

        Logger.e("onPaused");
    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {

        Logger.e("onPreparing");

    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {

        player.setSubmitText("准备播放");
        Logger.e("onPrepared");
    }

    @Override
    public void onBuffering(int percent) {
        Logger.e("onBuffering",percent+"进度");
    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {
        Logger.e("onError");

//        new MaterialDialog.Builder(this).title(R.string.error).content(e.getMessage()).positiveText(android.R.string.ok).show();
        player.stop();
        player.setSubmitText("播放出错");
        ToastUtils.show(this,"播放出错");

    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {
        player.setSubmitText("播放完成");
        player.stop();
        Logger.e("onCompletion");
    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {
        Logger.e("onRetry");
    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {
        Logger.e("onSubmit");
        player.stop();
        player=null;
        this.finish();

    }
}
