package com.iqb.been.user;

import com.iqb.been.base.BaseEntity;

public class TeacherData extends BaseEntity {

    /**
     * id : 944dca57c4334135bd6a5c95a4c51abe
     * agentId : SH001
     * sex :
     * mobile : 13162721536
     * pwd : $2a$10$2f6DUZQb0pfPKFYV9vfegODKqZmHRlhlZ9ikNuu7ef3mb0qT1jyVe
     * birthday :
     * intro :
     * wechat :
     * city :
     * nickname : 海彬老师
     * identity :
     * icon : /org-files/icon/20180926/1537926277756066872.jpg
     * attach :
     * type : DEFAULT
     * role : 20
     * blockedAt : 0
     * activated : 2
     * createdAt : 1568269149101
     * modifiedAt : 1598599333016
     * lastActivityAt : 1599621473875
     * lastClassAt : 1598945417658
     * summary :
     * name :
     * todayLessonNum : 0
     * totalLessonNum : 0
     * courseUnitPrice : 30.0
     * initialPwd : 298427
     * mark : 1
     * disabledAt :
     * agentName : 智凌琴行
     * uid : null
     */

    private String id;
    private String agentId;
    private String sex;
    private String mobile;
    private String pwd;
    private String birthday;
    private String intro;
    private String wechat;
    private String city;
    private String nickname;
    private String identity;
    private String icon;
    private String attach;
    private String type;
    private int role;
    private int blockedAt;
    private int activated;
    private int courseLen;
    private String createdAt;
    private String modifiedAt;
    private String lastActivityAt;
    private String lastClassAt;
    private String summary;
    private String name;
    private int todayLessonNum;
    private int totalLessonNum;
    private double courseUnitPrice;
    private String initialPwd;
    private int mark;
    private String disabledAt;
    private String agentName;
    private Object uid;
    private int spltScrMode;
    private int audioMixMode;
    private int scoreDispMode;

    public int getAudioMixMode() {
        return audioMixMode;
    }

    public void setAudioMixMode(int audioMixMode) {
        this.audioMixMode = audioMixMode;
    }

    public int getScoreDispMode() {
        return scoreDispMode;
    }

    public void setScoreDispMode(int scoreDispMode) {
        this.scoreDispMode = scoreDispMode;
    }

    public int getSpltScrMode() {
        return spltScrMode;
    }

    public void setSpltScrMode(int spltScrMode) {
        this.spltScrMode = spltScrMode;
    }

    public int getCourseLen() {
        return courseLen;
    }

    public void setCourseLen(int courseLen) {
        this.courseLen = courseLen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getBlockedAt() {
        return blockedAt;
    }

    public void setBlockedAt(int blockedAt) {
        this.blockedAt = blockedAt;
    }

    public int getActivated() {
        return activated;
    }

    public void setActivated(int activated) {
        this.activated = activated;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(String lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    public String getLastClassAt() {
        return lastClassAt;
    }

    public void setLastClassAt(String lastClassAt) {
        this.lastClassAt = lastClassAt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTodayLessonNum() {
        return todayLessonNum;
    }

    public void setTodayLessonNum(int todayLessonNum) {
        this.todayLessonNum = todayLessonNum;
    }

    public int getTotalLessonNum() {
        return totalLessonNum;
    }

    public void setTotalLessonNum(int totalLessonNum) {
        this.totalLessonNum = totalLessonNum;
    }

    public double getCourseUnitPrice() {
        return courseUnitPrice;
    }

    public void setCourseUnitPrice(double courseUnitPrice) {
        this.courseUnitPrice = courseUnitPrice;
    }

    public String getInitialPwd() {
        return initialPwd;
    }

    public void setInitialPwd(String initialPwd) {
        this.initialPwd = initialPwd;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getDisabledAt() {
        return disabledAt;
    }

    public void setDisabledAt(String disabledAt) {
        this.disabledAt = disabledAt;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Object getUid() {
        return uid;
    }

    public void setUid(Object uid) {
        this.uid = uid;
    }
}
