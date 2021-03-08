package com.iqb.been.home;

import com.iqb.been.base.BaseEntity;

import java.util.List;

public class HomeGetStudentImgEntity extends BaseEntity {

    /**
     * id : 27610f5d0a48451592df2adeda0f11fe
     * liveId : 2020102114814110
     * studentId : 4ae700bb4574426c88bbbe29102378e4
     * picUrl : ["/org-files/livaAttachment/20201021/1603260984181097775.jpg"]
     * comment :
     * read : false
     * createdAt : 1603260984126
     * commentTime : 0
     * startAt : null
     * endAt : null
     * teacher : null
     * student : null
     * agent : null
     * feedback : null
     */

    private String id;
    private String liveId;
    private String studentId;
    private String comment;
    private boolean read;
    private String createdAt;
    private int commentTime;
    private Object startAt;
    private Object endAt;
    private Object teacher;
    private Object student;
    private Object agent;
    private Object feedback;
    private List<String> picUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(int commentTime) {
        this.commentTime = commentTime;
    }

    public Object getStartAt() {
        return startAt;
    }

    public void setStartAt(Object startAt) {
        this.startAt = startAt;
    }

    public Object getEndAt() {
        return endAt;
    }

    public void setEndAt(Object endAt) {
        this.endAt = endAt;
    }

    public Object getTeacher() {
        return teacher;
    }

    public void setTeacher(Object teacher) {
        this.teacher = teacher;
    }

    public Object getStudent() {
        return student;
    }

    public void setStudent(Object student) {
        this.student = student;
    }

    public Object getAgent() {
        return agent;
    }

    public void setAgent(Object agent) {
        this.agent = agent;
    }

    public Object getFeedback() {
        return feedback;
    }

    public void setFeedback(Object feedback) {
        this.feedback = feedback;
    }

    public List<String> getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(List<String> picUrl) {
        this.picUrl = picUrl;
    }
}
