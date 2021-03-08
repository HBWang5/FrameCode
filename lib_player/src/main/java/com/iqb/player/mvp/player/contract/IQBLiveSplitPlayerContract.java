package com.iqb.player.mvp.player.contract;

import android.content.Context;
import android.view.ViewGroup;

import com.iqb.player.mvp.base.IBaseContract;
import com.iqb.player.mvp.player.IIQBLivePlayer;
import com.iqb.player.mvp.player.IIQBLivePlayerCallBack;
import com.iqb.player.mvp.player.callback.IQBIRtcEngineEventHandler;

public interface IQBLiveSplitPlayerContract {
    abstract class IQBLiveSplitPlayerView<P extends IQBLiveSplitContractPresenter> extends IBaseContract.BasePlayerLiveView<P> implements IQBIRtcEngineEventHandler.ILiveCallback, IIQBLivePlayer {


        /**
         * 初始化
         */
        public abstract void initCreate();

        /**
         * 加入频道
         */
        public abstract void joinChannel(String token, String id, String teaAgoraUid);

        /**
         * 设置本地试图
         */
        public abstract void setupLocalVideo();

        /**
         * 设置远端试图
         */
        public abstract void setupRemoteVideo(int uid);

        /**
         * 离开频道
         */
        public abstract void leaveChannel();

        /**
         * 销毁
         */
        public abstract void destroy();

        /**
         * 删除远端视图
         */
        public abstract void removeRemoteVideo(int uid);

        /**
         * 展示view
         */
        public abstract void setDisplay(ViewGroup viewGroup);

        /**
         * 打开麦克风
         */
        public abstract void openMicrophone();

        /**
         * 关闭麦克风
         */
        public abstract void closeMicrophone();

        /**
         * 打开喇叭
         */
        public abstract void openLoudspeaker();

        /**
         * 关闭喇叭
         */
        public abstract void closeLoudspeaker();

        public abstract void setCallBack(IIQBLivePlayerCallBack iiqbLivePlayerCallBack);

        public abstract void setBigLive(int uid);

        public abstract void joinClass(String stuName, String agroaID);

        public abstract void resetLive();

        public abstract int getStuIndex(int currentStuAgoraId);
    }

    abstract class IQBLiveSplitContractPresenter<V extends IQBLiveSplitPlayerView> extends IBaseContract.BaseLivePresenter<V> {
    }

}
