package com.iqb.player.widgetcombination.impl;


import android.app.Activity;
import android.content.res.Configuration;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.mvp.mediagroup.view.IQBMediaGroup;
import com.iqb.player.widgetcombination.IWidgetCombination;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/2/13-13:59
 * 初始化层级控制（组合）
 */
public class WidgetVideoCombination extends IWidgetCombination {

    private IQBVideoControllerView mIQBMediaVideoController;
    private IQBMediaGroup mMediaGroup;


    /**
     * 背景层（覆盖于控制层之上）
     */
    @Override
    public void bindBackgroundView() {
    }

    /**
     * 插件层试图（广告，弹幕等，覆盖于控制层之上）
     */
    @Override
    public void bindPluginView() {

    }

    /**
     * 播放器层试图(最底层)
     */
    @Override
    public void bindMediaGroup(ViewGroup viewGroup) {
        mMediaGroup = new IQBMediaGroup(activity);
        //获取设置的配置信息
        Configuration mConfiguration = activity.getResources().getConfiguration();
        //获取屏幕方向
        int ori = mConfiguration.orientation;
        ViewGroup.LayoutParams layoutParams = ori == Configuration.ORIENTATION_LANDSCAPE ? new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) : new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        viewGroup.addView(mMediaGroup, layoutParams);
        if (ori == Configuration.ORIENTATION_LANDSCAPE) {
            ((Activity) mMediaGroup.getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            ((Activity) mMediaGroup.getContext()).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

    }



    /**
     * 绑定SurfaceView
     */
    @Override
    public void bindSurfaceView() {
        //绑定播放器视图
        mMediaGroup.bindSurfaceView();
        //控制层绑定播放器层
        mMediaGroup.bindIQBMediaController(mIQBMediaVideoController);
    }



    /**
     * 获取控制层
     */
    @Override
    public IQBVideoControllerView getControllerView() {
        return mIQBMediaVideoController;
    }

    @Override
    public void initController() {
        mIQBMediaVideoController = new IQBVideoControllerView(activity);
    }
}
