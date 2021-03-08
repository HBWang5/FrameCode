package com.iqb.player.tools;


public class SocketSendTools {
    private static SocketSendTools socketSendTools;

    public static synchronized SocketSendTools getInstance() {
        if (socketSendTools == null) {
            socketSendTools = new SocketSendTools();
        }
        return socketSendTools;

    }
}
