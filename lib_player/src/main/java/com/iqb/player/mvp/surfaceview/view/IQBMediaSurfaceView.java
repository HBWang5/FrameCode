package com.iqb.player.mvp.surfaceview.view;

import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.mvp.player.IIQBMediaPlayer;
import com.iqb.player.mvp.player.proxy.IQBMediaPlayerProxy;
import com.iqb.player.mvp.surfaceview.listener.IQBOnGestureListener;
import com.iqb.player.mvp.surfaceview.listener.IQBSurfaceViewListener;
import com.iqb.player.mvp.mediacontroller.constitute.impl.ConstituteVideoView;
import com.iqb.player.mvp.surfaceview.contract.IQBMediaSurfaceContract;
import com.iqb.player.mvp.surfaceview.presenter.IQBMediaSurfaceViewPresenter;
import com.iqb.player.playerstatus.PlayerStatus;
import com.iqb.player.tools.BrightnessTools;

public class IQBMediaSurfaceView extends IQBMediaSurfaceContract.IQBMediaSurfaceRelativeView<IQBMediaSurfaceViewPresenter> {
    private IQBMediaPlayerProxy mIQBMediaPlayerProxy;
    private int brightness = 125;
    private long oldProgress;
    private long newProgress;
    private IQBVideoControllerView mIQBMediaController;
    private com.iqb.player.mvp.surfaceview.listener.IQBSurfaceViewListener IQBSurfaceViewListener;

    public IQBMediaSurfaceView(Context context) {
        super(context);
        init();
    }

    @Override
    protected IQBMediaSurfaceViewPresenter bindPresenter() {
        return new IQBMediaSurfaceViewPresenter();
    }

    /**
     * 初始化
     */
    private void init() {
        //设置手势监听
        GestureDetector mGestureDetector = new GestureDetector(new IQBOnGestureListener(this));
        IQBSurfaceViewListener = new IQBSurfaceViewListener(mGestureDetector);
        //设置点击滑动监听
        setOnTouchListener(IQBSurfaceViewListener);
        //取消长按，不然会影响滑动
        mGestureDetector.setIsLongpressEnabled(false);
        //设置app常亮
        BrightnessTools.getInstance().setOftenBrightness((Activity) getContext());

    }


    public void addCallback(IQBMediaPlayerProxy IQBMediaPlayerProxy) {
        this.mIQBMediaPlayerProxy = IQBMediaPlayerProxy;
        getHolder().addCallback(this);
    }

    @Override
    public IIQBMediaPlayer getMediaPlayer() {
        return mIQBMediaPlayerProxy;
    }

    /**
     * 同步进度
     */
    @Override
    public void setSyncProgress(long position) {
        if (PlayerStatus.PLAYER_GESTURE_STATUS == 1) {
            return;
        }
        oldProgress = position;
    }

    /**
     * 绑定控制器
     */
    @Override
    public void bindMediaController(IQBVideoControllerView mIQBMediaController) {
        this.mIQBMediaController = mIQBMediaController;
        IQBSurfaceViewListener.bindMediaController(mIQBMediaController);
    }

    /**
     * 显示
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIQBMediaPlayerProxy.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    /**
     * 亮度手势调节
     */
    @Override
    public void onBrightnessGesture(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (distanceY > 0) {
            BrightnessTools.getInstance().setAppBrightness(brightness > 255 ? 255 : brightness++, (Activity) getContext());
            mIQBMediaController.setAppBrightness(brightness > 255 ? 100 : (int) (brightness++ / 2.55));
        } else {
            BrightnessTools.getInstance().setAppBrightness(brightness < 0 ? 0 : brightness--, (Activity) getContext());
            mIQBMediaController.setAppBrightness(brightness < 0 ? 0 : (int) (brightness-- / 2.55));
        }

    }

    /**
     * 音量手势调节
     */
    @Override
    public void onVolumeGesture(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float e_one_y = e1.getY() ;
        float e_two_y = e2.getY() ;
        int value = getHeight() / BrightnessTools.getInstance().getMaxVolume();
        int volume = (int) ((e_one_y - e_two_y) / (value * 2));
        BrightnessTools.getInstance().setVolumeGesture(volume);
        mIQBMediaController.setVolumeGesture((int) ((float) BrightnessTools.getInstance().getStreamVolume() / (float) BrightnessTools.getInstance().getMaxVolume() * 100));
    }


    /**
     * 快进手势调节
     */
    @Override
    public void onFF_REWGesture(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float offset = e2.getX() - e1.getX();
        if (offset > 0) {
            newProgress = (long) (oldProgress + offset / getWidth() * getMediaPlayer().getDuration());
            if (newProgress > getMediaPlayer().getDuration()) {
                newProgress = getMediaPlayer().getDuration();
            }
        } else {
            newProgress = (long) (oldProgress + offset / getWidth() * getMediaPlayer().getDuration());
            if (newProgress < 0) {
                newProgress = 0;
            }
        }
        ConstituteVideoView.getInstance().showBelow();
        getMediaPlayer().seekTo(newProgress);

    }


    /**
     * 按下时重新更新配置
     */
    @Override
    public void resetConfig() {
        oldProgress = newProgress;
    }
}
