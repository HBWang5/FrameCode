package com.iqb.player.mvp.mediacontroller.view;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceHolder;

import com.iqb.player.mvp.mediacontroller.contract.IQBMusicControllerContract;
import com.iqb.player.mvp.mediacontroller.presenter.IQBMusicControllerPresenter;
import com.iqb.player.mvp.mediagroup.BaseGroup;
import com.iqb.player.mvp.surfaceview.view.IQBMediaSurfaceView;
import com.iqb.player.servce.impl.MediaPlayerService;
import com.iqb.player.threadpro.IMediaProCallBack;

public class IQBMusicControllerView extends IQBMusicControllerContract.IQBMusicControllerRelativeView<IQBMusicControllerPresenter> {
    private IMediaProCallBack iMediaProCallBack;

    public IQBMusicControllerView(Context context) {
        super(context);
    }

    @Override
    protected IQBMusicControllerPresenter bindPresenter() {
        return null;
    }


    @Override
    public void playMedia(String url) {

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
        MediaPlayerService.getMediaPlayerService().getMediaPlayer().bindMediaProManager(iMediaProCallBack);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void prepare() {

    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void bindMediaProManager(IMediaProCallBack iMediaProCallBack) {
        this.iMediaProCallBack = iMediaProCallBack;
    }

    @Override
    public void seekTo(int progress) {

    }

    @Override
    public IQBMediaSurfaceView getSurfaceView() {
        return null;
    }

    @Override
    public void setAppBrightness(int brightness) {

    }

    @Override
    public void setVolumeGesture(int volume) {

    }

    @Override
    public void showUI() {

    }

    @Override
    public void playerFull(Activity context) {

    }

    @Override
    public void pullMeasureLayout() {

    }

    @Override
    public void initConstituteView(BaseGroup liveGroup) {

    }

    @Override
    public void initViewConfig() {

    }
}
