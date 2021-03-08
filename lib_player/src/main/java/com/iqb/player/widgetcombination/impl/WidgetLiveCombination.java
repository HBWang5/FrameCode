package com.iqb.player.widgetcombination.impl;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.iqb.player.mvp.mediacontroller.view.IQBLiveControllerView;
import com.iqb.player.mvp.mediagroup.view.IQBLiveGroup;
import com.iqb.player.mvp.plugin.view.LivePluginView;
import com.iqb.player.widgetcombination.IWidgetCombination;

/**
 * ----------Dragon be here!----------/
 * Created by IQB on 2019/3/19-13:52
 */
public class WidgetLiveCombination extends IWidgetCombination {

    private IQBLiveGroup mLiveGroup;
    private IQBLiveControllerView mIQBLiveController;

    /**
     * 背景层（覆盖于控制层之上）
     */
    @Override
    public void bindBackgroundView() {

    }

    /**
     * 插件层试图（广告，弹幕等，覆盖于控制层之上）
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void bindPluginView() {
        LivePluginView livePlugin = new LivePluginView(activity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mLiveGroup.addView(livePlugin, layoutParams);
        livePlugin.setVisibility(View.GONE);
    }

    /**
     * 播放器层试图(最底层)
     */
    @Override
    public void bindMediaGroup(ViewGroup viewGroup) {
        mLiveGroup = new IQBLiveGroup(activity);
        viewGroup.addView(mLiveGroup);
    }

    /**
     * 绑定SurfaceView
     */
    @Override
    public void bindSurfaceView() {
        //绑定播放器视图
        mLiveGroup.bindLiveViewGroup();
        //控制层绑定播放器层
        mLiveGroup.bindIQBMediaController(getControllerView());
    }


    @Override
    public IQBLiveControllerView getControllerView() {
        return mIQBLiveController;
    }

    @Override
    public void initController() {
        mIQBLiveController = new IQBLiveControllerView(activity);
    }
}
