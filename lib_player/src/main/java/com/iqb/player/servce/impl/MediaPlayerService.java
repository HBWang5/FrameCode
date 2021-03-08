package com.iqb.player.servce.impl;


import com.iqb.player.mvp.player.IIQBMediaPlayer;
import com.iqb.player.mvp.player.proxy.IQBMediaPlayerProxy;
import com.iqb.player.servce.IQBBaseService;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/1/10-17:28
 */
public class MediaPlayerService extends IQBBaseService {

    private IQBMediaPlayerProxy mIQBMediaPlayerProxy;

    @Override
    protected void initMediaPlayer() {
        mIQBMediaPlayerProxy = new IQBMediaPlayerProxy();
        mIQBMediaPlayerProxy.prepare();
    }

    @Override
    public IIQBMediaPlayer getMediaPlayer() {
        return mIQBMediaPlayerProxy;
    }

}
