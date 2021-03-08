package com.iqb.player.manager.impl;

import android.content.Intent;
import android.view.ViewGroup;

import com.iqb.player.manager.IPlayerMusicManager;
import com.iqb.player.mvp.mediacontroller.view.IQBMusicControllerView;
import com.iqb.player.servce.impl.MediaPlayerService;
import com.iqb.player.widgetcombination.impl.WidgetMusicCombination;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/3/15-17:50
 */
public class PlayerMusicManager implements IPlayerMusicManager {

    private WidgetMusicCombination mWidgetMusicCombination;

    /**
     * 初始化播放器
     */
    @Override
    public void bindViewForMediaPlayer(ViewGroup viewGroup) {
        mWidgetMusicCombination = new WidgetMusicCombination();
        mWidgetMusicCombination.initMediaPlayer(viewGroup);
        viewGroup.getContext().startService(new Intent(viewGroup.getContext(), MediaPlayerService.class));
    }


    /**
     * 获取控制层对象
     */
    @Override
    public IQBMusicControllerView getMediaController() {
        return mWidgetMusicCombination.getControllerView();
    }
}
