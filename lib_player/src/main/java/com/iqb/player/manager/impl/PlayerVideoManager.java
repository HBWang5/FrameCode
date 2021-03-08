package com.iqb.player.manager.impl;


import android.view.ViewGroup;

import com.iqb.player.manager.IPlayerVideoManager;
import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.widgetcombination.impl.WidgetVideoCombination;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/1/10-17:40
 * 播放器管理器（用于交互）
 */
public class PlayerVideoManager implements IPlayerVideoManager {

    private WidgetVideoCombination mWidgetVideoCombination;

    /**
     * 初始化播放器
     */
    @Override
    public void bindViewForMediaPlayer(ViewGroup viewGroup) {
        mWidgetVideoCombination = new WidgetVideoCombination();
        mWidgetVideoCombination.initMediaPlayer(viewGroup);
    }


    /**
     * 获取控制层对象
     */
    @Override
    public IQBVideoControllerView getMediaController() {
        return mWidgetVideoCombination.getControllerView();
    }


}
