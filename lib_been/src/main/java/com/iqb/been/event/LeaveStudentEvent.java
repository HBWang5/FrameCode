package com.iqb.been.event;

public class LeaveStudentEvent {


    /**
     * sourceId : 6f71175e2e594568a54b9115b6e7975a
     * targetId : 944dca57c4334135bd6a5c95a4c51abe
     * msgType : leaveChannel
     * msgContent : 2020111715585157
     * createdAt : 1605599030853
     */

    private String sourceId;
    private String targetId;
    private String msgType;
    private String msgContent;
    private String createdAt;

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

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
