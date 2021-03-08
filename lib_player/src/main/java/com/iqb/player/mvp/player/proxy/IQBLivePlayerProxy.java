package com.iqb.player.mvp.player.proxy;


import android.content.Context;
import android.view.ViewGroup;


import com.iqb.player.mvp.player.IIQBLivePlayer;
import com.iqb.player.mvp.player.IIQBLivePlayerCallBack;
import com.iqb.player.mvp.player.view.IQBLivePlayer;



/**
 * ----------Dragon be here!----------/
 * Created by IQB on 2019/2/12-15:03
 * 直播代理
 */
public class IQBLivePlayerProxy implements IIQBLivePlayer {
    private IIQBLivePlayer mIQBLivePlayer;
    public IQBLivePlayerProxy(Context context) {
        mIQBLivePlayer = new IQBLivePlayer(context);
        initCreate();
    }

    @Override
    public void initCreate() {
        mIQBLivePlayer.initCreate();
    }

    @Override
    public void joinChannel(String token, String id, String teaAgoraUid) {
        setupLocalVideo();
        mIQBLivePlayer.joinChannel(token, id, teaAgoraUid);
    }

    @Override
    public void setupLocalVideo() {
        mIQBLivePlayer.setupLocalVideo();
    }

    @Override
    public void setupRemoteVideo(int uid) {
        mIQBLivePlayer.setupRemoteVideo(uid);
    }

    @Override
    public void leaveChannel() {

    }

    @Override
    public void destroy() {
        mIQBLivePlayer.destroy();
    }

    @Override
    public void removeRemoteVideo(int uid) {
    }

    @Override
    public void setDisplay(ViewGroup viewGroup) {
        mIQBLivePlayer.setDisplay(viewGroup);
    }

    @Override
    public void openMicrophone() {
        mIQBLivePlayer.openMicrophone();
    }

    @Override
    public void closeMicrophone() {
        mIQBLivePlayer.closeMicrophone();
    }

    @Override
    public void openLoudspeaker() {
        mIQBLivePlayer.openLoudspeaker();
    }

    @Override
    public void closeLoudspeaker() {
        mIQBLivePlayer.closeLoudspeaker();
    }

    @Override
    public void setVisible(boolean b) {
        mIQBLivePlayer.setVisible(b);
    }

    @Override
    public void handUp(String id) {
        mIQBLivePlayer.handUp(id);
    }


    @Override
    public void visibleBigWin(Object tag) {
        mIQBLivePlayer.visibleBigWin(tag);
    }

    @Override
    public void setCallBack(IIQBLivePlayerCallBack iiqbLivePlayerCallBack) {
        mIQBLivePlayer.setCallBack(iiqbLivePlayerCallBack);
    }

    @Override
    public void voiceCtl(boolean b) {
        mIQBLivePlayer.voiceCtl(b);
    }


    @Override
    public void setBigLive(int uid) {
        mIQBLivePlayer.setBigLive(uid);
    }

    @Override
    public void resetLive() {
        mIQBLivePlayer.resetLive();
    }

    @Override
    public int getStuIndex(int currentStuAgoraId) {
        return mIQBLivePlayer.getStuIndex(currentStuAgoraId);
    }
}
