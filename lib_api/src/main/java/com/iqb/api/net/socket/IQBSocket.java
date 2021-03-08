package com.iqb.api.net.socket;


import io.socket.client.Manager;
import io.socket.client.Socket;

public class IQBSocket extends Socket {
    public IQBSocket(Manager io, String nsp, Manager.Options opts) {
        super(io, nsp, opts);
    }


}
