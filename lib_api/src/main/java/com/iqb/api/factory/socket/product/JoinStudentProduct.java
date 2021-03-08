package com.iqb.api.factory.socket.product;

import com.iqb.api.net.socket.AppSocket;
import com.iqb.been.socket.SocketEntity;

import static com.iqb.constants.SocketEventConfig.CLIENT_REPORT;
import static com.iqb.constants.SocketEventConfig.JOIN_CLASS_MSG_TYPE_TEACHER;

public class JoinStudentProduct implements IBaseProduct {
    private String data;
    @Override
    public void execute() {
        SocketEntity socketEntity = new SocketEntity();
        socketEntity.setMsgContent(data);
        socketEntity.setMsgType(JOIN_CLASS_MSG_TYPE_TEACHER);
        AppSocket.getInstance().emit(CLIENT_REPORT, socketEntity);
    }

    @Override
    public void initConfig(String data) {
        this.data = data;
    }
}
