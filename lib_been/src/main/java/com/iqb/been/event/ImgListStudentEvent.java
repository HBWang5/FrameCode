package com.iqb.been.event;

public class ImgListStudentEvent {

    /**
     * drawCtlType : drawCtlType
     */
    private String sourceId;
    private String targetId;
//    private String drawCtlType;

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

//    public String getDrawCtlType() {
//        return drawCtlType;
//    }
//
//    public void setDrawCtlType(String drawCtlType) {
//        this.drawCtlType = drawCtlType;
//    }
}
