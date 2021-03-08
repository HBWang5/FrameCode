package com.iqb.been.home;

import com.iqb.been.base.BaseEntity;

public class HomeMsgEntity extends BaseEntity {


    /**
     * id : b0cad3f4272b4571bdbb768db94fde6a
     * title : 学生动态
     * text : 王海彬学生加入了教室
     * imageUrl :
     * status : false
     * tags :
     * transmissionContent : {"data":"6ff49db343214a3f8889c52c3686562e","tag":"OTR","text":"王海彬学生加入了教室","title":"学生动态","url":""}
     * commitTime : 1596532682334
     * pushContentType : 2
     */

    private String id;
    private String title;
    private String text;
    private String imageUrl;
    private boolean status;
    private String tags;
    private String transmissionContent;
    private String commitTime;
    private int pushContentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTransmissionContent() {
        return transmissionContent;
    }

    public void setTransmissionContent(String transmissionContent) {
        this.transmissionContent = transmissionContent;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public int getPushContentType() {
        return pushContentType;
    }

    public void setPushContentType(int pushContentType) {
        this.pushContentType = pushContentType;
    }
}
