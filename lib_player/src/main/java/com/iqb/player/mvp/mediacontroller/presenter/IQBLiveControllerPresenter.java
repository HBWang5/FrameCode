package com.iqb.player.mvp.mediacontroller.presenter;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import com.iqb.api.base.view.activity.BaseActivity;
import com.iqb.api.net.rx.TransformUtils;
import com.iqb.been.event.LiveForPlayerDestroy;
import com.iqb.been.event.LiveForPlayerHandUp;
import com.iqb.been.event.LiveForPlayerRaiseHand;
import com.iqb.been.event.LiveForPlayerVoiceModel;
import com.iqb.been.event.LiveViewCloseLoudspeaker;
import com.iqb.been.event.LiveViewCloseMicrophone;
import com.iqb.been.event.LiveViewLeaveChannel;
import com.iqb.been.event.LiveViewOpenLoudspeaker;
import com.iqb.been.event.LiveViewOpenMicrophone;
import com.iqb.been.event.LiveViewOpenPrepareImg;
import com.iqb.player.R;
import com.iqb.player.mvp.base.IBaseModel;
import com.iqb.player.mvp.mediacontroller.contract.IQBLiveControllerContract;
import com.iqb.player.mvp.mediacontroller.view.IQBLiveControllerView;
import com.iqb.player.mvp.player.IIQBLivePlayerCallBack;
import com.iqb.player.tools.TimeTools;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class IQBLiveControllerPresenter extends IQBLiveControllerContract.IQBLiveControllerContractLivePresenter<IQBLiveControllerView> implements IIQBLivePlayerCallBack {

    private Disposable subscribe;
    private boolean isHandsUp = true;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timerHandsUp();
        }
    };
    public IQBLiveControllerPresenter() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected IBaseModel bindModel() {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerVoiceModel(LiveForPlayerVoiceModel liveForPlayerVoiceModel) {
        getView().setPlayerVoiceModel(liveForPlayerVoiceModel.isVoiceCtl());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerDestroy(LiveForPlayerDestroy liveForPlayerDestroy) {
        destroy();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void liveForPlayerRaiseHand(LiveForPlayerRaiseHand liveForPlayerRaiseHand) {
        getView().setPlayerRaiseHand();
    }

    @Override
    public void checkScore() {
        EventBus.getDefault().post(new LiveViewOpenPrepareImg());
    }

    private void destroy() {
        TimeTools.getInstance().cancel();
    }

    @Override
    public void handsUp() {
        if (isHandsUp) {

            getView().addMsg(getView().getContext().getString(R.string.live_player_hand_up_tv));
            EventBus.getDefault().post(new LiveForPlayerHandUp());
            isHandsUp = false;
            handler.sendEmptyMessageDelayed(0,30000);
        } else {
            getView().addMsg(getView().getContext().getString(R.string.live_player_hand_up_error_tv));
        }
    }

    private void timerHandsUp() {
        isHandsUp = true;
    }

    @Override
    public void openCloseLoudspeaker(Boolean tag) {
        if (tag) {
            EventBus.getDefault().post(new LiveViewOpenLoudspeaker());
        } else {
            EventBus.getDefault().post(new LiveViewCloseLoudspeaker());
        }
    }

    @Override
    public void openCloseMicrophone(Boolean tag) {
        if (tag) {
            EventBus.getDefault().post(new LiveViewOpenMicrophone());
        } else {
            EventBus.getDefault().post(new LiveViewCloseMicrophone());
        }
    }

    @Override
    public void leaveChannel() {
        EventBus.getDefault().post(new LiveViewLeaveChannel());
    }


    @Override
    public void detachView() {
        super.detachView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void notifyMsg(int lastItemPosition) {
        getView().notifyMsg(lastItemPosition);
    }

}
