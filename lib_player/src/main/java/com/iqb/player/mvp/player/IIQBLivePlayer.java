package com.iqb.player.mvp.player;

import android.view.ViewGroup;

public interface IIQBLivePlayer {

    /**
     * 初始化
     */
    void initCreate();

    /**
     * 加入频道
     */
    void joinChannel(String token, String id, String teaAgoraUid);

    /**
     * 设置本地试图
     */
    void setupLocalVideo();

    /**
     * 设置远端试图
     */
    void setupRemoteVideo(int uid);

    /**
     * 离开频道
     */
    void leaveChannel();

    /**
     * 销毁
     */
    void destroy();

    /**
     * 删除远端视图
     */
    void removeRemoteVideo(int uid);

    /**
     * 展示view
     */
    void setDisplay(ViewGroup viewGroup);

    /**
     * 打开麦克风
     */
    void openMicrophone();

    /**
     * 关闭麦克风
     */
    void closeMicrophone();

    /**
     * 打开喇叭
     */
    void openLoudspeaker();

    /**
     * 关闭喇叭
     */
    void closeLoudspeaker();

    default void setVisible(boolean b) {
    }

    default void handUp(String id) {

    }



    default void visibleBigWin(Object tag) {

    }

    void setCallBack(IIQBLivePlayerCallBack iiqbLivePlayerCallBack);

    void voiceCtl(boolean b);

    default void setBigLive(int uid) {

    }

    default void resetLive(){

    }

    default int getStuIndex(int currentStuAgoraId){
        return 0;
    }
}
