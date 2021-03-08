package com.iqb.been.player;

import com.iqb.been.base.BaseEntity;

public class HomeJoinChannelEntity extends BaseEntity {

    /**
     * liveId : 2020092709813084
     * teacherId : 72148ae7ade94b6d98923789af51fbce
     * time : 0
     * startAt : 1601169072259
     * endAt : 0
     * createdAt : 1601169072259
     * year : 2020
     * month : 9
     * day : 27
     * agentId : SH001
     * type : 2
     * valid : true
     * student : null
     * teacher : null
     * streamKey : null
     * slist : null
     */

    private String liveId;
    private String teacherId;
    private int time;
    private long startAt;
    private int endAt;
    private long createdAt;
    private int year;
    private int month;
    private int day;
    private String agentId;
    private int type;
    private boolean valid;
    private Object student;
    private Object teacher;
    private Object streamKey;
    private Object slist;

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getStartAt() {
        return startAt;
    }

    public void setStartAt(long startAt) {
        this.startAt = startAt;
    }

    public int getEndAt() {
        return endAt;
    }

    public void setEndAt(int endAt) {
        this.endAt = endAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Object getStudent() {
        return student;
    }

    public void setStudent(Object student) {
        this.student = student;
    }

    public Object getTeacher() {
        return teacher;
    }

    public void setTeacher(Object teacher) {
        this.teacher = teacher;
    }

    public Object getStreamKey() {
        return streamKey;
    }

    public void setStreamKey(Object streamKey) {
        this.streamKey = streamKey;
    }

    public Object getSlist() {
        return slist;
    }

    public void setSlist(Object slist) {
        this.slist = slist;
    }
}
