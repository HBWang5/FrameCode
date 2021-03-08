package com.iqb.been.home;

import com.iqb.been.base.BaseEntity;

import java.util.List;

public class HomeClassListEntity extends BaseEntity {

    private List<ScheduleBean> schedule;

    public List<ScheduleBean> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleBean> schedule) {
        this.schedule = schedule;
    }

    public static class ScheduleBean {
        /**
         * id : a27a4654258140a0a4c5025cd571037f
         * scheduleName : 一对多小班课
         * studentId : 4ae700bb4574426c88bbbe29102378e4
         * studentNickname : 王海彬
         * studentMobile : 13162721536
         * studentIcon : /iqb-files/icon/20200811/1597143199871072025.png
         * teacherId : 944dca57c4334135bd6a5c95a4c51abe
         * teacherNickname : 海彬老师
         * teacherMobile : 13162721536
         * teacherIcon : /org-files/icon/20180926/1537926277756066872.jpg
         * scheduleTime : 1599130800000
         * createAt : 1599126639365
         * agentId : SH001
         * repeatFlag : 1dae87ff81114b94af38f4402a0927a8
         * checkIn : 0
         * courseType : 1
         */

        private String id;
        private String scheduleName;
        private String studentId;
        private String studentNickname;
        private String studentMobile;
        private String studentIcon;
        private String teacherId;
        private String teacherNickname;
        private String teacherMobile;
        private String teacherIcon;
        private String scheduleTime;
        private String createAt;
        private String agentId;
        private String repeatFlag;
        private int checkIn;
        private int courseType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getScheduleName() {
            return scheduleName;
        }

        public void setScheduleName(String scheduleName) {
            this.scheduleName = scheduleName;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getStudentNickname() {
            return studentNickname;
        }

        public void setStudentNickname(String studentNickname) {
            this.studentNickname = studentNickname;
        }

        public String getStudentMobile() {
            return studentMobile;
        }

        public void setStudentMobile(String studentMobile) {
            this.studentMobile = studentMobile;
        }

        public String getStudentIcon() {
            return studentIcon;
        }

        public void setStudentIcon(String studentIcon) {
            this.studentIcon = studentIcon;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }

        public String getTeacherNickname() {
            return teacherNickname;
        }

        public void setTeacherNickname(String teacherNickname) {
            this.teacherNickname = teacherNickname;
        }

        public String getTeacherMobile() {
            return teacherMobile;
        }

        public void setTeacherMobile(String teacherMobile) {
            this.teacherMobile = teacherMobile;
        }

        public String getTeacherIcon() {
            return teacherIcon;
        }

        public void setTeacherIcon(String teacherIcon) {
            this.teacherIcon = teacherIcon;
        }

        public String getScheduleTime() {
            return scheduleTime;
        }

        public void setScheduleTime(String scheduleTime) {
            this.scheduleTime = scheduleTime;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }

        public String getAgentId() {
            return agentId;
        }

        public void setAgentId(String agentId) {
            this.agentId = agentId;
        }

        public String getRepeatFlag() {
            return repeatFlag;
        }

        public void setRepeatFlag(String repeatFlag) {
            this.repeatFlag = repeatFlag;
        }

        public int getCheckIn() {
            return checkIn;
        }

        public void setCheckIn(int checkIn) {
            this.checkIn = checkIn;
        }

        public int getCourseType() {
            return courseType;
        }

        public void setCourseType(int courseType) {
            this.courseType = courseType;
        }
    }
}
