package com.iqb.api.thread;


import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorMsgSender.ThreadPoolExecutorSenderImpl.ThreadSenderBase;
import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.net.socket.AppSocket;
import com.iqb.been.socket.SocketBeatStuEntity;

import java.util.Objects;

import static com.iqb.constants.SocketEventConfig.CHECK_CONNECT_MSG_TYPE_STUDENT;
import static com.iqb.constants.SocketEventConfig.CLIENT_REPORT;

/**
 * 检查连接状态线程
 */
public class CheckConnThread extends ThreadSenderBase {
    private final SocketBeatStuEntity socketBeatStuEntity;
    private static String TeacherId = ApiApplication.getApp().getAppComponent().getDataManager().getSharePreferenceHelper().getUserID();


    public CheckConnThread() {
        super();
        socketBeatStuEntity = new SocketBeatStuEntity();
    }

    @Override
    public void run() {
        socketBeatStuEntity.setMsgContent("{}");
        socketBeatStuEntity.setSourceId(TeacherId);
        socketBeatStuEntity.setTargetId(TeacherId);
        socketBeatStuEntity.setMsgType(CHECK_CONNECT_MSG_TYPE_STUDENT);
        Objects.requireNonNull(AppSocket.getInstance()).emit(CLIENT_REPORT, socketBeatStuEntity);
    }
}
