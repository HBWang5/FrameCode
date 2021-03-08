package com.iqb.player.mvp.plugin.view;

import android.content.Context;

import com.iqb.player.mvp.plugin.contract.IQBVideoPluginContract;
import com.iqb.player.mvp.plugin.presenter.VideoPluginPresenter;

public class VideoPluginView extends IQBVideoPluginContract.IQBVideoPluginRelativeView<VideoPluginPresenter> {
    public VideoPluginView(Context context) {
        super(context);
    }

    @Override
    protected VideoPluginPresenter bindPresenter() {
        return new VideoPluginPresenter();
    }
}
