package com.iqb.been.socket;

import com.iqb.been.base.BaseSocketEntity;

public class SocketIdEntity extends BaseSocketEntity {
    private Object msgContent;
    private String sourceId;
    private String targetId;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Object getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(Object msgContent) {
        this.msgContent = msgContent;
    }
}
