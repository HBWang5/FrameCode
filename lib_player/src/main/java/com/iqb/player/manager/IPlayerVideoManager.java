package com.iqb.player.manager;

import android.view.ViewGroup;

import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/1/10-17:23
 * 播放器控制器管理类接口
 */
public interface IPlayerVideoManager {
    void bindViewForMediaPlayer(ViewGroup viewGroup);

    IQBVideoControllerView getMediaController();
}
