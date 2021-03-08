package com.iqb.api.net.socket;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;

import io.socket.tools.GsonTools;


public class AppSocket extends BaseSocket {
    private static volatile AppSocket INSTANCE = null;

    public static AppSocket getInstance() {
        if (INSTANCE == null) {
//            throw new NullPointerException("must first call the build() method");
            return null;
        }
        return INSTANCE;
    }

    @Override
    public void close() {
        super.close();
        INSTANCE = null;
    }

    public static AppSocket init(Builder builder) {
        return new AppSocket(builder);
    }

    private AppSocket(Builder builder) {
        super(builder);
        INSTANCE = this;
    }

//    public void addUser(String username) {
//        mSocket.emit("", username);
//    }

    @SuppressLint("Assert")
    public void emit(String event, Object obj) {
        Log.d("SocketServicePresenter", event + ":ChatMessage" + GsonTools.getInstance().toJson(obj));
        if (mSocket == null) {
            Log.d("SocketServicePresenter", "mSocket.null():" + mSocket.connected());
        }
//        Log.d("AppSocket", com.iqb.been.event.replace("_", "%5F"));

//        Log.d("SocketServicePresenter", "mSocket.connected():" + mSocket.connected());

        mSocket.emit(event, GsonTools.getInstance().toJson(obj));
//        mSocket.emit(com.iqb.been.event, obj);
    }
}
