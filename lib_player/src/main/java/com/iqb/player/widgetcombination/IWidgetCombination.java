package com.iqb.player.widgetcombination;

import android.app.Activity;
import android.view.ViewGroup;

import com.iqb.player.mvp.mediacontroller.IBaseIQBController;


/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/3/19-13:54
 */
public abstract class IWidgetCombination {
    public Activity activity;

    public abstract void bindBackgroundView();

    public abstract void bindPluginView();

    public abstract void bindMediaGroup(ViewGroup viewGroup);

    public abstract void bindSurfaceView();

    public abstract IBaseIQBController getControllerView();

    public abstract void initController();

    public void  initMediaPlayer(ViewGroup viewGroup){
        activity = (Activity) viewGroup.getContext();
        bindBackgroundView();
        initController();
        bindMediaGroup(viewGroup);
        bindSurfaceView();
        bindPluginView();
    }
}
