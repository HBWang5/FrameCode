package com.iqb.player.widgetcombination.impl;

import android.view.ViewGroup;

import com.iqb.player.mvp.mediacontroller.view.IQBMusicControllerView;
import com.iqb.player.widgetcombination.IWidgetCombination;

/**
 * ----------Dragon be here!----------/
 * Created by IQB on 2019/3/19-13:52
 */
public class WidgetMusicCombination extends IWidgetCombination {

    private IQBMusicControllerView mIQBMediaVideoController;

    public WidgetMusicCombination() {

    }

    @Override
    public void bindBackgroundView() {

    }

    @Override
    public void bindPluginView() {

    }

    @Override
    public void bindMediaGroup(ViewGroup viewGroup) {
        viewGroup.addView(mIQBMediaVideoController);
    }

    @Override
    public void bindSurfaceView() {

    }


    @Override
    public IQBMusicControllerView getControllerView() {
        return mIQBMediaVideoController;
    }

    @Override
    public void initController() {
        mIQBMediaVideoController = new IQBMusicControllerView(activity);
    }
}
