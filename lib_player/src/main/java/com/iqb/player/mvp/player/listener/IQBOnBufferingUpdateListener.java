package com.iqb.player.mvp.player.listener;



import com.iqb.player.mvp.player.IIQBMediaPlayer;
import com.iqb.player.mvp.player.view.IQBMediaPlayer;

import tv.danmaku.ijk.media.player.IMediaPlayer;


/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/3/1-15:54
 * 缓冲更新监听回调
 */
public class IQBOnBufferingUpdateListener implements IMediaPlayer.OnBufferingUpdateListener {
    private IQBMediaPlayer mIIQBMediaPlayer;

    public IQBOnBufferingUpdateListener(IQBMediaPlayer mIIQBMediaPlayer) {
        this.mIIQBMediaPlayer = mIIQBMediaPlayer;
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int percent) {
        mIIQBMediaPlayer.setBufferPercentage(percent);
    }
}
