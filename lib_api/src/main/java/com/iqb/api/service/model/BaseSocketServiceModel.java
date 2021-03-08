package com.iqb.api.service.model;

import android.content.Context;
import android.util.Log;

import com.iqb.api.base.baseservice.model.BaseServiceModel;
import com.iqb.api.factory.socket.impl.SocketTypeFactory;
import com.iqb.api.net.socket.AppSocket;
import com.iqb.api.net.socket.BaseSocket;
import com.iqb.been.socket.SocketEntity;

import java.util.Objects;

import io.socket.tools.GsonTools;
import com.iqb.api.BuildConfig;
import static com.iqb.constants.SocketEventConfig.SYSTEM_PUSH;

public class BaseSocketServiceModel extends BaseServiceModel {


    public BaseSocketServiceModel(Context context) {
        super(context);
    }

    /**
     * socket配置
     */
    public void initConfig(String connectQuery) {
        String replace = connectQuery.replace("+", "%2B").replace("/", "%2F");
        AppSocket.init(new BaseSocket.Builder(BuildConfig.BASE_SOCKET)
                .query(replace)
                .forceNew(true)
                .reconnection(true)
                .reconnectionAttempts(3)
                .reconnectionDelay(1000)
                .reconnectionDelayMax(5000)
                .timeout(100000)
                .setEmitterListener((key, args) -> {

                    if (SYSTEM_PUSH.equals(key)) {
                        Log.d("BaseSocketServiceModel", args[0].toString());
                        SocketEntity socketEntity = GsonTools.getInstance().fromJson(args[0].toString(), SocketEntity.class);
                        SocketTypeFactory.getInstance().createTeacherReceiveFactory().receiveMsg(socketEntity.getMsgType(), args[0].toString());
                    } else {
                        SocketTypeFactory.getInstance().switchSocketTypeFactory(key);
                    }

                })
        );

    }

    /**
     * Socket链接
     */
    public void connect() {
        Objects.requireNonNull(AppSocket.getInstance()).connect();
    }
}
