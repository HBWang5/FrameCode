package com.iqb.player.mvp.mediacontroller.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.iqb.player.R;
import com.iqb.player.mvp.mediacontroller.listener.IQBVideoControllerOnClickListener;
import com.iqb.player.mvp.mediacontroller.contract.IQBVideoControllerContract;
import com.iqb.player.mvp.mediacontroller.constitute.impl.ConstituteVideoView;
import com.iqb.player.mvp.mediacontroller.presenter.IQBVideoControllerPresenter;
import com.iqb.player.mvp.mediagroup.BaseGroup;
import com.iqb.player.mvp.mediagroup.view.IQBMediaGroup;
import com.iqb.player.mvp.surfaceview.view.IQBMediaSurfaceView;
import com.iqb.player.threadpro.IMediaProCallBack;
import com.iqb.player.tools.BrightnessTools;
import com.iqb.src.widget.IQBPlayerVolumeBrightnessBar;
import com.iqb.src.widget.IQBPlayerImageView;

import static com.iqb.player.playerstatus.PlayerStatus.PLAYER_IS_FULL;

public class IQBVideoControllerView extends IQBVideoControllerContract.IQBVideoControllerRelativeView<IQBVideoControllerPresenter> {

    private IQBMediaGroup mediaGroup;
    private IMediaProCallBack iMediaProCallBack;
    private IQBPlayerVolumeBrightnessBar mBrightnessBar;
    private IQBPlayerVolumeBrightnessBar mVolumeBar;

    public IQBVideoControllerView(Context context) {
        super(context);
    }

    @Override
    protected IQBVideoControllerPresenter bindPresenter() {
        return null;
    }

    /**
     * 初始化配置
     */
    @Override
    public void initViewConfig() {
        BrightnessTools.getInstance().initConfig(getContext());
        //音量控制View
        mBrightnessBar = findViewById(R.id.pro_brightness);
        //亮度控制View
        mVolumeBar = findViewById(R.id.pro_volume);
        //横竖屏控制View
        IQBPlayerImageView iqbImageFullView = findViewById(R.id.player_full);
        IQBPlayerImageView iqbImagePauseView = findViewById(R.id.player_pause);
        iqbImagePauseView.setOnClickListener(new IQBVideoControllerOnClickListener().bindController(this));
        iqbImageFullView.setOnClickListener(new IQBVideoControllerOnClickListener().bindController(this));
        mVolumeBar.setPercent((int) ((float) BrightnessTools.getInstance().getStreamVolume() / (float) BrightnessTools.getInstance().getMaxVolume() * 100));
    }

    /**
     * 初始化控制组合组件
     */
    @Override
    public void initConstituteView(BaseGroup mediaGroup) {
        this.mediaGroup = (IQBMediaGroup)mediaGroup;
        ConstituteVideoView.getInstance().init(this)
                .addLeftColumnView()
                .addRightColumnView()
                .addBelowColumnView();
        initViewConfig();
    }

    /**
     * 播放视频
     */
    @Override
    public void playMedia(String url) {
        mediaGroup.getSurfaceView().getMediaPlayer().playMedia(url);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    @Override
    public void start() {
        //开始播放
        mediaGroup.getSurfaceView().getMediaPlayer().start();
        //绑定Pro管理器
        mediaGroup.getSurfaceView().getMediaPlayer().bindMediaProManager(iMediaProCallBack);
    }

    @Override
    public void pause() {
        mediaGroup.getSurfaceView().getMediaPlayer().pause();
    }

    @Override
    public void resume() {
        mediaGroup.getSurfaceView().getMediaPlayer().resume();
    }

    @Override
    public void prepare() {
        mediaGroup.getSurfaceView().getMediaPlayer().prepare();
    }

    @Override
    public int getCurrentPosition() {
        try {
            return (int) mediaGroup.getSurfaceView().getMediaPlayer().getCurrentPosition();
        } catch (Exception e) {
            return 0;
        }
    }


    @Override
    public void bindMediaProManager(IMediaProCallBack iMediaProCallBack) {
        this.iMediaProCallBack = iMediaProCallBack;
    }

    @Override
    public void seekTo(int progress) {
        mediaGroup.getSurfaceView().getMediaPlayer().seekTo(progress);
    }

    @Override
    public IQBMediaSurfaceView getSurfaceView() {
        return mediaGroup.getSurfaceView();
    }


    @Override
    public void setAppBrightness(int brightness) {
        mBrightnessBar.setPercent(brightness);
        ConstituteVideoView.getInstance().showLeft();
    }

    @Override
    public void setVolumeGesture(int volume) {
        mVolumeBar.setPercent(volume);
        ConstituteVideoView.getInstance().showRight();
    }

    @Override
    public void showUI() {
        ConstituteVideoView.getInstance().setVisibiLity();
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void playerFull(Activity context) {
        if (PLAYER_IS_FULL) {
            context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //切换为竖屏
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            PLAYER_IS_FULL = false;
            pullMeasureLayout();
        }
        //切换为横屏
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            PLAYER_IS_FULL = true;
            pullMeasureLayout();
        }
    }

    @Override
    public void pullMeasureLayout() {
        ViewGroup.LayoutParams layoutParams = mediaGroup.getLayoutParams();
        if (PLAYER_IS_FULL) {
            ((Activity) mediaGroup.getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        } else {
            ((Activity) mediaGroup.getContext()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //显示状态栏
            layoutParams.height = 600;
        }
        mediaGroup.setLayoutParams(layoutParams);
    }
}
