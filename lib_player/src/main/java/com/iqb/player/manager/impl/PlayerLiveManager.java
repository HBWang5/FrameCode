package com.iqb.player.manager.impl;

import android.view.ViewGroup;

import com.iqb.player.manager.IPlayerLiveManager;
import com.iqb.player.mvp.mediacontroller.view.IQBLiveControllerView;
import com.iqb.player.widgetcombination.impl.WidgetLiveCombination;

public class PlayerLiveManager implements IPlayerLiveManager {

    private WidgetLiveCombination mWidgetLiveCombination;

    /**
     * 初始化
     */
    @Override
    public void bindViewForLivePlayer(ViewGroup viewGroup) {
        mWidgetLiveCombination = new WidgetLiveCombination();
        mWidgetLiveCombination.initMediaPlayer(viewGroup);
    }

    /**
     * 控制层代理对象
     */
    @Override
    public IQBLiveControllerView getMediaController() {
        return mWidgetLiveCombination.getControllerView();
    }
}
