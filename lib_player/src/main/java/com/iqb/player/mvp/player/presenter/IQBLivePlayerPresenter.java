package com.iqb.player.mvp.player.presenter;

import com.iqb.been.event.LiveForPlayerAgreeJoinChannel;
import com.iqb.been.event.LiveForPlayerDestroy;
import com.iqb.been.event.LiveForPlayerVoiceModel;
import com.iqb.been.event.LiveViewCloseLoudspeaker;
import com.iqb.been.event.LiveViewCloseMicrophone;
import com.iqb.been.event.LiveViewOpenLoudspeaker;
import com.iqb.been.event.LiveViewOpenMicrophone;
import com.iqb.player.mvp.base.IBaseModel;
import com.iqb.player.mvp.player.contract.IQBLivePlayerContract;
import com.iqb.player.mvp.player.view.IQBLivePlayer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.iqb.constants.VariableConfig.AGORA_TOKEN;
import static com.iqb.constants.VariableConfig.STREAM_KEY;
import static com.iqb.constants.VariableConfig.TEA_AGORA_UID;

public class IQBLivePlayerPresenter extends IQBLivePlayerContract.IQBLiveContractPresenter<IQBLivePlayer> {
    @Override
    protected IBaseModel bindModel() {
        return null;
    }

    public IQBLivePlayerPresenter() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveViewOpenLoudspeaker(LiveViewOpenLoudspeaker liveViewOpenLoudspeaker) {
        getView().openLoudspeaker();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveViewCloseLoudspeaker(LiveViewCloseLoudspeaker liveViewCloseLoudspeaker) {
        getView().closeLoudspeaker();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveViewOpenMicrophone(LiveViewOpenMicrophone liveViewOpenMicrophone) {
        getView().openMicrophone();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveViewCloseMicrophone(LiveViewCloseMicrophone liveViewCloseMicrophone) {
        getView().closeMicrophone();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerJoinChannel(LiveForPlayerAgreeJoinChannel liveForPlayerJoinChannel) {
        getView().joinChannel(AGORA_TOKEN, STREAM_KEY, TEA_AGORA_UID);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerVoiceModel(LiveForPlayerVoiceModel liveForPlayerVoiceModel) {
        getView().voiceCtl(liveForPlayerVoiceModel.isVoiceCtl());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerDestroy(LiveForPlayerDestroy liveForPlayerDestroy) {
        getView().destroy();
    }

    @Override
    public void detachView() {
        super.detachView();
        EventBus.getDefault().unregister(this);
    }
}
