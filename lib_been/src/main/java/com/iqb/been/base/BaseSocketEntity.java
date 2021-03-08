package com.iqb.been.base;

import java.io.Serializable;

public class BaseSocketEntity implements Serializable {
    private String msgType;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
