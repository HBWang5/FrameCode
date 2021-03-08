package com.iqb.been.event;

public class JoinClassStudentEvent {

    /**
     * StudentName : 真正的萱萱
     * AgoraId : 100007721
     */
    private String StudentId;
    private String StudentName;
    private String AgoraId;

    public JoinClassStudentEvent(String studentName, String agoraId) {
        StudentName = studentName;
        AgoraId = agoraId;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
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

    @Override
    public String toString() {
        return "JoinClassStudentEvent{" +
                "StudentId='" + StudentId + '\'' +
                ", StudentName='" + StudentName + '\'' +
                ", AgoraId='" + AgoraId + '\'' +
                '}';
    }
}
