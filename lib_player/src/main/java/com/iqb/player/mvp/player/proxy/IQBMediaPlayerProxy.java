package com.iqb.player.mvp.player.proxy;

import android.view.SurfaceHolder;

import com.iqb.player.mvp.player.listener.IIQBMediaPlayerListener;
import com.iqb.player.mvp.player.IIQBMediaPlayer;
import com.iqb.player.mvp.player.view.IQBMediaPlayer;
import com.iqb.player.threadpro.IMediaProCallBack;


/**
 * ----------Dragon be here!----------/
 * Created by IQB on 2019/2/12-15:03
 * 播放器代理
 */
public class IQBMediaPlayerProxy implements IIQBMediaPlayer {
    private IQBMediaPlayer mIQBMediaPlayer;


    public IQBMediaPlayerProxy() {
        mIQBMediaPlayer = new IQBMediaPlayer(null);
    }

    @Override
    public void start() {
        mIQBMediaPlayer.start();
    }

    @Override
    public void pause() {
        mIQBMediaPlayer.pause();
    }


    @Override
    public void resume() {
        mIQBMediaPlayer.resume();
    }


    @Override
    public void onDestroy() {
        mIQBMediaPlayer.onDestroy();
    }

    @Override
    public void savePlayMediaState() {
        mIQBMediaPlayer.savePlayMediaState();
    }

    @Override
    public void clearPlayMediaState() {
        mIQBMediaPlayer.clearPlayMediaState();
    }


    @Override
    public void seekTo(long pos) {
        mIQBMediaPlayer.seekTo(pos);
    }

    @Override
    public void setBufferPercentage(int percent) {
        mIQBMediaPlayer.setBufferPercentage(percent);
    }


    @Override
    public int getBufferPercentage() {
        return mIQBMediaPlayer.getBufferPercentage();
    }


    @Override
    public void removeMediaPlayerListener(IIQBMediaPlayerListener listener) {
        mIQBMediaPlayer.removeMediaPlayerListener(listener);
    }

    @Override
    public void addMediaPlayerListener(IIQBMediaPlayerListener listener) {
        mIQBMediaPlayer.addMediaPlayerListener(listener);
    }

    @Override
    public long getDuration() {
        return mIQBMediaPlayer.getDuration();
    }

    @Override
    public long getCurrentPosition() {
        return mIQBMediaPlayer.getCurrentPosition();
    }

    @Override
    public void playMedia(String url) {
        mIQBMediaPlayer.playMedia(url);
    }

    @Override
    public void bindMediaProManager(IMediaProCallBack iMediaProCallBack) {
        mIQBMediaPlayer.bindMediaProManager(iMediaProCallBack);
    }

    @Override
    public void onPrepared() {
        mIQBMediaPlayer.onPrepared();
    }

    @Override
    public void prepare() {
        mIQBMediaPlayer.prepare();
    }

    @Override
    public void setDisplay(SurfaceHolder display) {
        mIQBMediaPlayer.setDisplay(display);
    }
}
