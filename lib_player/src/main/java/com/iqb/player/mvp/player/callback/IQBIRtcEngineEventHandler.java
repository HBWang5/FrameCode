package com.iqb.player.mvp.player.callback;

import android.app.Activity;
import android.util.Log;

import io.agora.rtc.IRtcEngineEventHandler;


public class IQBIRtcEngineEventHandler extends IRtcEngineEventHandler {

    private Activity activity;

    private ILiveCallback liveCallback;

    public IQBIRtcEngineEventHandler(Activity activity, ILiveCallback liveCallback) {
        this.activity = activity;
        this.liveCallback = liveCallback;
    }

    @Override
    public void onRequestToken() {
        super.onRequestToken();
        Log.d("IQBIRtcEngineEventHandl", "onRequestToken");
    }

    @Override
    public void onLocalAudioStateChanged(int i, int i1) {
        super.onLocalAudioStateChanged(i, i1);
        Log.d("IQBIRtcEngineEventHandl", "onLocalAudioStateChanged");
    }

    /**
     * 注册 onJoinChannelSuccess 回调。
     * 本地用户成功加入频道时，会触发该回调。
     */
    @Override
    public void onJoinChannelSuccess(String channel, final int uid, int elapsed) {
        Log.d("IQBIRtcEngineEventHandl", "onJoinChannelSuccess");
    }

    @Override
    public void onUserJoined(int i, int i1) {
        super.onUserJoined(i, i1);
        Log.d("IQBIRtcEngineEventHandl", "onUserJoined");
        liveCallback.add(i);
    }


    //    @Override
//    public void onRemoteAudioStateChanged(int uid,
//                                          int state,
//                                          int reason,
//                                          int elapsed) {
//        super.onRemoteAudioStateChanged(uid, state, reason, elapsed);
////        if (state == REMOTE_VIDEO_STATE_STARTING) {
////
////        }
//    }

    /**
     * 设置远端视图
     * 注册 onFirstRemoteVideoDecoded 回调。
     * SDK 接收到第一帧远端视频并成功解码时，会触发该回调。
     * 可以在该回调中调用 setupRemoteVideo 方法设置远端视图。
     */
    @Override
    public void onFirstRemoteVideoDecoded(final int uid, int width, int height, int elapsed) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                liveCallback.setupRemoteVideo(uid);
            }
        });
    }

    @Override
    public void onRemoteVideoStateChanged(int uid, int state, int reason, int elapsed) {
        super.onRemoteVideoStateChanged(uid, state, reason, elapsed);
//        if (state == REMOTE_VIDEO_STATE_STARTING) {
//            liveCallback.refreshSmallVideo();
//        }


    }

    /**
     * 注册 onUserOffline 回调。
     * 远端用户离开频道或掉线时，会触发该回调。
     */
    @Override

    public void onUserOffline(final int uid, int reason) {
        Log.d("IQBIRtcEngineEventHandl", "onUserOffline:" + uid);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                liveCallback.onRemoteUserLeft(uid);
            }
        });
    }

    public interface ILiveCallback {

        /**
         * 设置远端试图
         */
        void setupRemoteVideo(int uid);

        /**
         * 删除远端试图
         */
        void onRemoteUserLeft(int uid);

        void add(int i);

        default void refreshSmallVideo(){

        }

    }
}
