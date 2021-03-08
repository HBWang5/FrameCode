package com.iqb.player.mvp.player.view;

import android.app.Activity;
import android.content.Context;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.iqb.api.BuildConfig;
import com.iqb.api.utils.SPHelper;
import com.iqb.player.mvp.player.IIQBLivePlayerCallBack;
import com.iqb.player.mvp.player.callback.IQBIRtcEngineEventHandler;
import com.iqb.player.mvp.player.contract.IQBLivePlayerContract;
import com.iqb.player.mvp.player.presenter.IQBLivePlayerPresenter;


import java.util.ArrayList;

import io.agora.rtc.Constants;
import io.agora.rtc.RtcEngine;
import io.agora.rtc.video.BeautyOptions;
import io.agora.rtc.video.VideoCanvas;
import io.agora.rtc.video.VideoEncoderConfiguration;

import static com.iqb.constants.VariableConfig.AGORA_TOKEN;
import static com.iqb.constants.VariableConfig.STREAM_KEY;
import static com.iqb.constants.VariableConfig.TEA_AGORA_UID;
import static io.agora.rtc.Constants.VIDEO_MIRROR_MODE_ENABLED;
import static io.agora.rtc.Constants.VIDEO_STREAM_HIGH;
import static io.agora.rtc.video.BeautyOptions.LIGHTENING_CONTRAST_NORMAL;
import static io.agora.rtc.video.VideoEncoderConfiguration.COMPATIBLE_BITRATE;
import static io.agora.rtc.video.VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_24;
import static io.agora.rtc.video.VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT;


public class IQBLivePlayer extends IQBLivePlayerContract.IQBLivePlayerView<IQBLivePlayerPresenter> {
    private Context context;
    private RtcEngine mRtcEngine;
    private String teaAgoraUid;
    private SurfaceView mRemoteView;
    private final FrameLayout mLocalContainer;
    private final RelativeLayout mRemoteContainer;
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private boolean isOverdub = false;
    public IQBLivePlayer(Context context) {
        super();
        this.context = context;
        mLocalContainer = new FrameLayout(context);
        mRemoteContainer = new RelativeLayout(context);
    }


    @Override
    protected IQBLivePlayerPresenter bindPresenter() {
        return new IQBLivePlayerPresenter();
    }

    @Override
    public void initCreate() {
        try {

            mRtcEngine = RtcEngine.create(context, BuildConfig.AGORA_ID, new IQBIRtcEngineEventHandler((Activity) context, this));
            //设置频道场景
            //CHANNEL_PROFILE_COMMUNICATION(0)：（默认）通信场景。该场景适用于常见的一对一或群聊，频道中任何用户都可以自由说话。
            //CHANNEL_PROFILE_LIVE_BROADCASTING(1)：直播场景。该场景有主播和观众两种用户角色，可以通过 setClientRole 设置。 主播可以收发语音和视频，但观众只能收，不能发。
            //CHANNEL_PROFILE_GAME(2)：游戏场景。该场景默认使用低功耗低码率的编解码器，且频道内任何用户都可以自由发言，适用于游戏场景。Agora 不推荐使用该场景。
            mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_COMMUNICATION);
            //设置用户角色
            //CLIENT_ROLE_BROADCASTER 主播
            //CLIENT_ROLE_AUDIENCE 观众
//            mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
            //开/关视频双流模式
            mRtcEngine.enableDualStreamMode(true);
            // 配置一个 VideoEncoderConfiguration 实例，参数可参考下文中的 API 参考链接
            VideoEncoderConfiguration config = new VideoEncoderConfiguration(
                    // 视频分辨率。可以选择默认的几种分辨率选项，也可以自定义
                    new VideoEncoderConfiguration.VideoDimensions(640, 480),
                    // 视频编码帧率。可自定义。通常建议是 15 帧，不超过 30 帧
                    FRAME_RATE_FPS_24,
                    // 标准码率。也可以配置其它的码率值，但一般情况下推荐使用标准码率
                    //STANDARD_BITRATE = 0：（推荐）标准码率模式。该模式下，视频在通信和直播场景下的码率有所不同：
                    //通信场景下，码率与基准码率一致
                    //直播场景下，码率对照基准码率翻倍
                    //COMPATIBLE_BITRATE = -1：适配码率模式。该模式下，视频在通信和直播场景下的码率均与基准码率一致。直播下如果选择该模式，视频帧率可能会低于设置的值
                    COMPATIBLE_BITRATE,
                    // 方向模式
                    ORIENTATION_MODE_FIXED_PORTRAIT
            );
            mRtcEngine.setVideoEncoderConfiguration(config);
            //美颜
            mRtcEngine.setBeautyEffectOptions(true, new BeautyOptions(LIGHTENING_CONTRAST_NORMAL, 0.5F, 0.5F, 0.5F));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void joinChannel(String token, String id, String teaAgoraUid) {
        this.teaAgoraUid = teaAgoraUid;
        setupLocalVideo();
        mRtcEngine.joinChannel(token, id, "", Integer.parseInt(SPHelper.getUId()));
        arrayList.clear();
    }

    @Override
    public void setupLocalVideo() {
        // 启用视频模块。
        mRtcEngine.enableVideo();
        // 创建 SurfaceView 对象。
        SurfaceView mLocalView = RtcEngine.CreateRendererView(context);
        mLocalView.setZOrderMediaOverlay(true);
        // 设置本地视图。
        //RENDER_MODE_HIDDEN 优先保证视窗被填满。视频尺寸等比缩放，直至整个视窗被视频填满。如果视频长宽与显示窗口不同，多出的视频将被截掉。
        //RENDER_MODE_FIT 优先保证视频内容全部显示。视频尺寸等比缩放，直至视频窗口的一边与视窗边框对齐。如果视频长宽与显示窗口不同，视窗上未被填满的区域将被涂黑
        //RENDER_MODE_FILL 视频尺寸进行缩放和拉伸以充满显示视窗。
        VideoCanvas localVideoCanvas = new VideoCanvas(mLocalView, VideoCanvas.RENDER_MODE_FIT, Integer.parseInt(SPHelper.getUId()));
        mRtcEngine.setupLocalVideo(localVideoCanvas);
    }

    @Override
    public void leaveChannel() {
        mRtcEngine.leaveChannel();
    }

    @Override
    public void destroy() {
        leaveChannel();
        RtcEngine.destroy();
    }

    @Override
    public void removeRemoteVideo(int uid) {
        mRemoteContainer.removeAllViews();
        mRemoteView = null;
    }

    @Override
    public void setDisplay(ViewGroup viewGroup) {
        viewGroup.addView(mLocalContainer, new RelativeLayout.LayoutParams(500, 500));
        viewGroup.addView(mRemoteContainer, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    @Override
    public void openMicrophone() {
        mRtcEngine.adjustRecordingSignalVolume(100);
    }

    @Override
    public void closeMicrophone() {
        mRtcEngine.adjustRecordingSignalVolume(0);
    }

    @Override
    public void openLoudspeaker() {
        if (isOverdub) {
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            mRtcEngine.muteRemoteAudioStream(arrayList.get(i), false);
        }
    }

    @Override
    public void closeLoudspeaker() {
        if (isOverdub) {
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == Integer.parseInt(teaAgoraUid)) {
                mRtcEngine.muteRemoteAudioStream(arrayList.get(i), false);
                continue;
            }
            mRtcEngine.muteRemoteAudioStream(arrayList.get(i), true);
        }
    }


    @Override
    public void setCallBack(IIQBLivePlayerCallBack iiqbLivePlayerCallBack) {

    }

    @Override
    public void voiceCtl(boolean b) {
        isOverdub = true;
        if (!b) {
            for (int i = 0; i < arrayList.size(); i++) {
                mRtcEngine.muteRemoteAudioStream(arrayList.get(i), true);
            }
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i) == Integer.parseInt(teaAgoraUid)) {
                    mRtcEngine.muteRemoteAudioStream(arrayList.get(i), false);
                    continue;
                }
                mRtcEngine.muteRemoteAudioStream(arrayList.get(i), true);
            }
        }
    }

    @Override
    public void setupRemoteVideo(int uid) {
        if (uid != Integer.parseInt(teaAgoraUid)) {
            return;
        }
        if (mRemoteView == null) {
            // 创建一个 SurfaceView 对象。
            mRemoteView = RtcEngine.CreateRendererView(context);
            mRemoteContainer.addView(mRemoteView);

            //设置订阅的视频流类型
            //VIDEO_STREAM_HIGH 视频大流，即高分辨率高码率的视频流。
            //VIDEO_STREAM_LOW 视频小流，即低分辨率低码率的视频流。
            mRtcEngine.setRemoteVideoStreamType(uid, VIDEO_STREAM_HIGH);
            // 设置远端视图。
            mRtcEngine.setupRemoteVideo(new VideoCanvas(mRemoteView, VideoCanvas.RENDER_MODE_HIDDEN, uid, VIDEO_MIRROR_MODE_ENABLED));

        }
    }

    @Override
    public void onRemoteUserLeft(int uid) {
        if (uid == Integer.parseInt(teaAgoraUid)) {
            removeRemoteVideo(0);
        }
    }

    @Override
    public void add(int uid) {
        if (!arrayList.contains(uid)) {
            arrayList.add(uid);
        }
    }
}
