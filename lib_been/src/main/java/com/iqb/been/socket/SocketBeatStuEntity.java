package com.iqb.been.socket;

import com.iqb.been.base.BaseSocketEntity;

public class SocketBeatStuEntity extends BaseSocketEntity {

    /**
     * msgType : heartbeat
     * targetId : student.id
     * sourceId : student.id
     * msgContent : 用户登陆成功，返回的authorization
     */

    private String targetId;
    private String sourceId;
    private String msgContent;


    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
