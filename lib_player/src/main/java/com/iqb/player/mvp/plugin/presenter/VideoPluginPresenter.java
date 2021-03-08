package com.iqb.player.mvp.plugin.presenter;

import com.iqb.player.mvp.base.IBaseModel;
import com.iqb.player.mvp.plugin.contract.IQBVideoPluginContract;
import com.iqb.player.mvp.plugin.view.VideoPluginView;

public class VideoPluginPresenter extends IQBVideoPluginContract.IQBVideoPluginContractPresenter<VideoPluginView> {
    @Override
    protected IBaseModel bindModel() {
        return null;
    }
}
