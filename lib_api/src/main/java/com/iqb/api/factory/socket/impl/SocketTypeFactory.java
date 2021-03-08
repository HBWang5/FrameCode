package com.iqb.api.factory.socket.impl;

import com.iqb.api.ThreadPoolExecutor.Proxy.ThreadPoolExecutorProxyImpl.ScheduledThreadPoolExecutorProxyImpl;
import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.factory.socket.specific.ITeacherReceiveFactory;
import com.iqb.api.factory.socket.specific.ITeacherSendFactory;
import com.iqb.api.factory.socket.specific.TeacherReceiveFactory;
import com.iqb.api.factory.socket.specific.TeacherSendFactory;
import com.iqb.api.thread.CheckConnThread;
import com.iqb.api.thread.HeartbeatThread;

import static io.socket.client.Socket.EVENT_CONNECT;

public class SocketTypeFactory implements ISocketTypeFactory {

    private static SocketTypeFactory mSocketTypeFactory;

    public static synchronized SocketTypeFactory getInstance() {
        if (mSocketTypeFactory == null) {
            mSocketTypeFactory = new SocketTypeFactory();
        }
        return mSocketTypeFactory;
    }

    @Override
    public void switchSocketTypeFactory(String eventType) {
        switch (eventType) {
            //连接成功发送心跳包
            case EVENT_CONNECT:
                ((ScheduledThreadPoolExecutorProxyImpl) ApiApplication.getApp().getAppComponent().getDataManager().getProduce()).scheduleAtFixedRate(new HeartbeatThread(), 0, 40000);
                ((ScheduledThreadPoolExecutorProxyImpl) ApiApplication.getApp().getAppComponent().getDataManager().getProduce()).scheduleAtFixedRate(new CheckConnThread(), 0, 10000);
            break;
        }
    }


    @Override
    public ITeacherReceiveFactory createTeacherReceiveFactory() {
        return new TeacherReceiveFactory();
    }

    @Override
    public ITeacherSendFactory createTeacherSendFactory() {
        return new TeacherSendFactory();
    }
}
