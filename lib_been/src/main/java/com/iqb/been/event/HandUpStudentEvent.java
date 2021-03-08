package com.iqb.been.event;

public class HandUpStudentEvent {

    /**
     * StudentName : 真正的萱萱
     * AgoraId : 100007721
     */
    private String StudentName;
    private String AgoraId;

    public HandUpStudentEvent(String studentName, String agoraId) {
        StudentName = studentName;
        AgoraId = agoraId;
    }


    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getAgoraId() {
        return AgoraId;
    }

    public void setAgoraId(String AgoraId) {
        this.AgoraId = AgoraId;
    }

}
