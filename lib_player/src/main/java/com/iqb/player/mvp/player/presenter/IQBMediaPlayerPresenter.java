package com.iqb.player.mvp.player.presenter;

import com.iqb.player.mvp.base.IBaseModel;
import com.iqb.player.mvp.player.contract.IQBLivePlayerContract;
import com.iqb.player.mvp.player.contract.IQBVideoPlayerContract;
import com.iqb.player.mvp.player.view.IQBLivePlayer;
import com.iqb.player.mvp.player.view.IQBMediaPlayer;

public class IQBMediaPlayerPresenter extends IQBVideoPlayerContract.IQBVideoContractPresenter<IQBMediaPlayer> {
    @Override
    protected IBaseModel bindModel() {
        return null;
    }
}
