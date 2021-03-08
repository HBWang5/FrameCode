package com.iqb.api.thread;


import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorMsgSender.ThreadPoolExecutorSenderImpl.ThreadSenderBase;
import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.net.socket.AppSocket;
import com.iqb.api.utils.SPHelper;
import com.iqb.been.socket.SocketBeatStuEntity;

import java.util.Objects;

import static com.iqb.constants.SocketEventConfig.CLIENT_REPORT;
import static com.iqb.constants.SocketEventConfig.HEARTBEAT_MSG_TYPE_SYSTEM;

/**
 * 心跳包线程
 */
public class HeartbeatThread extends ThreadSenderBase {
    private final SocketBeatStuEntity socketBeatStuEntity;
    private static String TeacherId = ApiApplication.getApp().getAppComponent().getDataManager().getSharePreferenceHelper().getUserID();


    public HeartbeatThread() {
        super();
        socketBeatStuEntity = new SocketBeatStuEntity();
    }

    @Override
    public void run() {
        socketBeatStuEntity.setMsgContent(SPHelper.getAccessToken());
        socketBeatStuEntity.setSourceId(TeacherId);
        socketBeatStuEntity.setTargetId(TeacherId);
        socketBeatStuEntity.setMsgType(HEARTBEAT_MSG_TYPE_SYSTEM);
        Objects.requireNonNull(AppSocket.getInstance()).emit(CLIENT_REPORT, socketBeatStuEntity);
    }
}
