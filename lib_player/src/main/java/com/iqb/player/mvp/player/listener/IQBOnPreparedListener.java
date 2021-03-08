package com.iqb.player.mvp.player.listener;


import com.iqb.player.mvp.player.IIQBMediaPlayer;
import com.iqb.player.mvp.player.view.IQBMediaPlayer;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * ----------Dragon be here!----------/
 * Created by IQB on 2019/3/1-16:18
 */
public class IQBOnPreparedListener implements IMediaPlayer.OnPreparedListener{

    private IQBMediaPlayer mIQBMediaPlayer;

    public IQBOnPreparedListener(IQBMediaPlayer mIQBMideoPlayer) {
        this.mIQBMediaPlayer = mIQBMideoPlayer;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {

        mIQBMediaPlayer.onPrepared();
    }
}
