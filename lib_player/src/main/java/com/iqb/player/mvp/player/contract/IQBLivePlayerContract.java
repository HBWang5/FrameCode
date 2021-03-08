package com.iqb.player.mvp.player.contract;


import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.player.IIQBLivePlayer;
import com.iqb.player.mvp.player.callback.IQBIRtcEngineEventHandler;

public interface IQBLivePlayerContract {
    abstract class IQBLivePlayerView<P extends IQBLiveContractPresenter> extends IBaseContract.BasePlayerLiveView<P> implements IQBIRtcEngineEventHandler.ILiveCallback, IIQBLivePlayer {

    }

    abstract class IQBLiveContractPresenter<V extends IQBLivePlayerView> extends IBaseContract.BaseLivePresenter<V> {
    }

}
