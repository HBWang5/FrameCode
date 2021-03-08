package com.iqb.api.net.socket;

import java.util.HashMap;
import java.util.Map;

import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static com.iqb.constants.SocketEventConfig.CLIENT_REPORT;
import static com.iqb.constants.SocketEventConfig.SYSTEM_PUSH;

class EmitterEvent {


    private Map<String, Emitter.Listener> emitterEventMap = new HashMap<>();

    EmitterEvent() {
        emitterEventMap.put(Manager.EVENT_TRANSPORT, null);
        emitterEventMap.put(Socket.EVENT_CONNECT_ERROR, null);
        emitterEventMap.put(Socket.EVENT_CONNECT_TIMEOUT, null);
        emitterEventMap.put(Socket.EVENT_CONNECT, null);
        emitterEventMap.put(Socket.EVENT_CONNECTING, null);
        emitterEventMap.put(Socket.EVENT_DISCONNECT, null);
        emitterEventMap.put(Socket.EVENT_ERROR, null);
        emitterEventMap.put(Socket.EVENT_RECONNECT, null);
        emitterEventMap.put(Socket.EVENT_RECONNECT_ATTEMPT, null);
        emitterEventMap.put(Socket.EVENT_RECONNECT_ERROR, null);
        emitterEventMap.put(Socket.EVENT_RECONNECT_FAILED, null);
        emitterEventMap.put(Socket.EVENT_RECONNECTING, null);

        emitterEventMap.put(CLIENT_REPORT, null);
        emitterEventMap.put(SYSTEM_PUSH, null);

    }

    void onEmitterEvent(Socket socket, final IEmitterListener emitterListener) {
        for (Map.Entry<String, Emitter.Listener> stringListenerEntry : emitterEventMap.entrySet()) {
            final String event = stringListenerEntry.getKey();
            Emitter.Listener listener;
            listener = args -> emitterListener.emitterListenerResult(event, args);
            emitterEventMap.put(event, listener);
            socket.on(event, listener);

        }
    }

    void offEmitterEvent(Socket socket) {
        for (Map.Entry<String, Emitter.Listener> stringListenerEntry : emitterEventMap.entrySet()) {
            String event = stringListenerEntry.getKey();
            Emitter.Listener el = stringListenerEntry.getValue();
            socket.off(event, el);
        }
    }
}
