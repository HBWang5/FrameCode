package com.iqb.been.login;

import com.iqb.been.base.BaseEntity;

public class LoginEntity extends BaseEntity {

    /**
     * isLive : false
     * agoraUid : 100008535
     * auth : T9fIiNwd6xUb27LiUEzzlpkH+n3S7YdKCW88l5pkHuohUBnUDNkFMsuIBcg/aip3/WN61IEFzNVstgagUK9di8sqvx+B/eIjiq93Cd6/rHNnTOdFWlny84r/uHysFlBS
     * activated : 1
     * scheduleTypes : {"1":"试听课","2":"陪练课"}
     * roomTypes : {"1":"一对一教室","2":"小班课教室"}
     * icon : /org-files/icon/20180926/1537926277756066872.jpg
     * nickname : 海彬老师
     * pwd : $2a$10$Ob419oFhCAzC3bAnUZ0fjuwgzDSf3Rk8Z9bDYu6kxggn8TWii8s8.
     * phoneNumber : 131****1536
     * uname : 13162721536
     * userId : 944dca57c4334135bd6a5c95a4c51abe
     */

    private boolean isLive;
    private int agoraUid;
    private String auth;
    private int activated;
    private ScheduleTypesBean scheduleTypes;
    private RoomTypesBean roomTypes;
    private String icon;
    private String nickname;
    private String pwd;
    private String phoneNumber;
    private String uname;
    private String userId;

    public boolean isIsLive() {
        return isLive;
    }

    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }

    public int getAgoraUid() {
        return agoraUid;
    }

    public void setAgoraUid(int agoraUid) {
        this.agoraUid = agoraUid;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getActivated() {
        return activated;
    }

    public void setActivated(int activated) {
        this.activated = activated;
    }

    public ScheduleTypesBean getScheduleTypes() {
        return scheduleTypes;
    }

    public void setScheduleTypes(ScheduleTypesBean scheduleTypes) {
        this.scheduleTypes = scheduleTypes;
    }

    public RoomTypesBean getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(RoomTypesBean roomTypes) {
        this.roomTypes = roomTypes;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static class ScheduleTypesBean {
        /**
         * 1 : 试听课
         * 2 : 陪练课
         */

        @com.google.gson.annotations.SerializedName("1")
        private String _$1;
        @com.google.gson.annotations.SerializedName("2")
        private String _$2;

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }
    }

    public static class RoomTypesBean {
        /**
         * 1 : 一对一教室
         * 2 : 小班课教室
         */

        @com.google.gson.annotations.SerializedName("1")
        private String _$1;
        @com.google.gson.annotations.SerializedName("2")
        private String _$2;

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }
    }
}
