package com.iqb.been.home;


import com.iqb.been.base.BaseEntity;


public class HomeStudentListEntity extends BaseEntity {

    /**
     * id : b5118cb6358047d4b316f7ec9d52e666
     * streamKey : 1187
     * teacherId : 944dca57c4334135bd6a5c95a4c51abe
     * studentId : 6f71175e2e594568a54b9115b6e7975a
     * createdAt : 1595821605277
     * lastClassAt : 1595821640023
     * classIcon :
     * student : {"id":"6f71175e2e594568a54b9115b6e7975a","agentId":"","password":"$2a$10$EVAdG0JV9.mGuVM3eSZeqe.F/y4NEd.NsL56SFQtcsa7hEjR9u4Ke","firstName":"","lastName":"","sex":"","company":"","position":"","mobile":"17621060987","createdAt":"1531106434063","lastActivityAt":"1597128195476","lastClassAt":"","icon":"/org-files/icon/20190328/1553754991811017319.png","role":1,"blockedAt":0,"modifiedAt":"1585301978401","datetime":"2019/03/22","email":"","coins":0,"nickname":"胖鱼","username":"","parentPwd":"$2a$10$17MZgqx4DwvDw106RM4YTunO1CYWfAvXkq2BICkmNZqubpddHgG6q","license":null,"birthday":"-28800000","wxUnionId":null,"parentActivityAt":"1597820747699","wxOpenId":null,"bundle":null,"uid":null}
     * teacher : null
     * online : 0
     * number : 0
     * status : false
     * liveId : null
     * teaAgoraUid : null
     * stuAgoraUid : 100002544
     * unreadFeedback : false
     * mine : false
     */

    private String id;
    private String streamKey;
    private String teacherId;
    private String studentId;
    private String createdAt;
    private String lastClassAt;
    private String classIcon;
    private StudentBean student;
    private Object teacher;
    private int online;
    private int number;
    private boolean status;
    private Object liveId;
    private Object teaAgoraUid;
    private int stuAgoraUid;
    private boolean unreadFeedback;
    private boolean mine;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreamKey() {
        return streamKey;
    }

    public void setStreamKey(String streamKey) {
        this.streamKey = streamKey;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastClassAt() {
        return lastClassAt;
    }

    public void setLastClassAt(String lastClassAt) {
        this.lastClassAt = lastClassAt;
    }

    public String getClassIcon() {
        return classIcon;
    }

    public void setClassIcon(String classIcon) {
        this.classIcon = classIcon;
    }

    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public Object getTeacher() {
        return teacher;
    }

    public void setTeacher(Object teacher) {
        this.teacher = teacher;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getLiveId() {
        return liveId;
    }

    public void setLiveId(Object liveId) {
        this.liveId = liveId;
    }

    public Object getTeaAgoraUid() {
        return teaAgoraUid;
    }

    public void setTeaAgoraUid(Object teaAgoraUid) {
        this.teaAgoraUid = teaAgoraUid;
    }

    public int getStuAgoraUid() {
        return stuAgoraUid;
    }

    public void setStuAgoraUid(int stuAgoraUid) {
        this.stuAgoraUid = stuAgoraUid;
    }

    public boolean isUnreadFeedback() {
        return unreadFeedback;
    }

    public void setUnreadFeedback(boolean unreadFeedback) {
        this.unreadFeedback = unreadFeedback;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public static class StudentBean {
        /**
         * id : 6f71175e2e594568a54b9115b6e7975a
         * agentId :
         * password : $2a$10$EVAdG0JV9.mGuVM3eSZeqe.F/y4NEd.NsL56SFQtcsa7hEjR9u4Ke
         * firstName :
         * lastName :
         * sex :
         * company :
         * position :
         * mobile : 17621060987
         * createdAt : 1531106434063
         * lastActivityAt : 1597128195476
         * lastClassAt :
         * icon : /org-files/icon/20190328/1553754991811017319.png
         * role : 1
         * blockedAt : 0
         * modifiedAt : 1585301978401
         * datetime : 2019/03/22
         * email :
         * coins : 0
         * nickname : 胖鱼
         * username :
         * parentPwd : $2a$10$17MZgqx4DwvDw106RM4YTunO1CYWfAvXkq2BICkmNZqubpddHgG6q
         * license : null
         * birthday : -28800000
         * wxUnionId : null
         * parentActivityAt : 1597820747699
         * wxOpenId : null
         * bundle : null
         * uid : null
         */

        private String id;
        private String agentId;
        private String password;
        private String firstName;
        private String lastName;
        private String sex;
        private String company;
        private String position;
        private String mobile;
        private String createdAt;
        private String lastActivityAt;
        private String lastClassAt;
        private String icon;
        private int role;
        private int blockedAt;
        private String modifiedAt;
        private String datetime;
        private String email;
        private int coins;
        private String nickname;
        private String username;
        private String parentPwd;
        private Object license;
        private String birthday;
        private Object wxUnionId;
        private String parentActivityAt;
        private Object wxOpenId;
        private Object bundle;
        private Object uid;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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

        public String getModifiedAt() {
            return modifiedAt;
        }

        public void setModifiedAt(String modifiedAt) {
            this.modifiedAt = modifiedAt;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getCoins() {
            return coins;
        }

        public void setCoins(int coins) {
            this.coins = coins;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getParentPwd() {
            return parentPwd;
        }

        public void setParentPwd(String parentPwd) {
            this.parentPwd = parentPwd;
        }

        public Object getLicense() {
            return license;
        }

        public void setLicense(Object license) {
            this.license = license;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public Object getWxUnionId() {
            return wxUnionId;
        }

        public void setWxUnionId(Object wxUnionId) {
            this.wxUnionId = wxUnionId;
        }

        public String getParentActivityAt() {
            return parentActivityAt;
        }

        public void setParentActivityAt(String parentActivityAt) {
            this.parentActivityAt = parentActivityAt;
        }

        public Object getWxOpenId() {
            return wxOpenId;
        }

        public void setWxOpenId(Object wxOpenId) {
            this.wxOpenId = wxOpenId;
        }

        public Object getBundle() {
            return bundle;
        }

        public void setBundle(Object bundle) {
            this.bundle = bundle;
        }

        public Object getUid() {
            return uid;
        }

        public void setUid(Object uid) {
            this.uid = uid;
        }
    }
}
