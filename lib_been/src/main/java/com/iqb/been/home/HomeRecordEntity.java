package com.iqb.been.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.iqb.been.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeRecordEntity extends BaseEntity implements Parcelable {

    /**
     * liveId : 2020090115520356
     * teacherId : 944dca57c4334135bd6a5c95a4c51abe
     * time : 8
     * startAt : 1598945417656
     * endAt : 1598945911642
     * createdAt : 1598945417656
     * year : 2020
     * month : 9
     * day : 1
     * agentId : SH001
     * type : 2
     * valid : true
     * student : null
     * teacher : null
     * streamKey : null
     * slist : [{"id":"fa88c08dcace4900b227f2611bab48ce","liveId":"2020090115520356","studentId":"bd164c53144145e18a677ddd60444159","startAt":1598945425394,"endAt":1598945911642,"teacher":null,"student":{"id":"bd164c53144145e18a677ddd60444159","agentId":"SH001","password":"$2a$10$AGYjEZaPqV3ZPwB8NJyWhOMozdm5uyu/JXH2LxZIStZTYxzaFIyNu","firstName":"","lastName":"","sex":"","company":"","position":"","mobile":"13901805167","createdAt":"1552895783829","lastActivityAt":"1598943905718","lastClassAt":"1568276510133","icon":"/iqb-files/icon/20200812/1597227481214010028.png","role":1,"blockedAt":0,"modifiedAt":"1597227481286","datetime":"2019/03/18","email":"","coins":0,"nickname":"真正的萱萱","username":"","parentPwd":"$2a$10$CPFAzpmetIGnr6vSot7FieVgNAATe6cusuR6lG8PwhatZJqo9scRW","license":null,"birthday":"2014-5-3","wxUnionId":"oHJD_vxe8VUWByABrjvgjRg5N52Y","parentActivityAt":"1598263525092","wxOpenId":"o_oh9wIHrIlA_msNYLPKipZIkU7s","bundle":null,"uid":null},"streamKey":null,"online":null}]
     */

    private String liveId;
    private String teacherId;
    private int time;
    private long startAt;
    private long endAt;
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
    private List<SlistBean> slist;

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

    public long getEndAt() {
        return endAt;
    }

    public void setEndAt(long endAt) {
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

    public List<SlistBean> getSlist() {
        return slist;
    }

    public void setSlist(List<SlistBean> slist) {
        this.slist = slist;
    }

    public static class SlistBean {
        /**
         * id : fa88c08dcace4900b227f2611bab48ce
         * liveId : 2020090115520356
         * studentId : bd164c53144145e18a677ddd60444159
         * startAt : 1598945425394
         * endAt : 1598945911642
         * teacher : null
         * student : {"id":"bd164c53144145e18a677ddd60444159","agentId":"SH001","password":"$2a$10$AGYjEZaPqV3ZPwB8NJyWhOMozdm5uyu/JXH2LxZIStZTYxzaFIyNu","firstName":"","lastName":"","sex":"","company":"","position":"","mobile":"13901805167","createdAt":"1552895783829","lastActivityAt":"1598943905718","lastClassAt":"1568276510133","icon":"/iqb-files/icon/20200812/1597227481214010028.png","role":1,"blockedAt":0,"modifiedAt":"1597227481286","datetime":"2019/03/18","email":"","coins":0,"nickname":"真正的萱萱","username":"","parentPwd":"$2a$10$CPFAzpmetIGnr6vSot7FieVgNAATe6cusuR6lG8PwhatZJqo9scRW","license":null,"birthday":"2014-5-3","wxUnionId":"oHJD_vxe8VUWByABrjvgjRg5N52Y","parentActivityAt":"1598263525092","wxOpenId":"o_oh9wIHrIlA_msNYLPKipZIkU7s","bundle":null,"uid":null}
         * streamKey : null
         * online : null
         */

        private String id;
        private String liveId;
        private String studentId;
        private long startAt;
        private long endAt;
        private Object teacher;
        private StudentBean student;
        private Object streamKey;
        private Object online;

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

        public long getStartAt() {
            return startAt;
        }

        public void setStartAt(long startAt) {
            this.startAt = startAt;
        }

        public long getEndAt() {
            return endAt;
        }

        public void setEndAt(long endAt) {
            this.endAt = endAt;
        }

        public Object getTeacher() {
            return teacher;
        }

        public void setTeacher(Object teacher) {
            this.teacher = teacher;
        }

        public StudentBean getStudent() {
            return student;
        }

        public void setStudent(StudentBean student) {
            this.student = student;
        }

        public Object getStreamKey() {
            return streamKey;
        }

        public void setStreamKey(Object streamKey) {
            this.streamKey = streamKey;
        }

        public Object getOnline() {
            return online;
        }

        public void setOnline(Object online) {
            this.online = online;
        }

        public static class StudentBean {
            /**
             * id : bd164c53144145e18a677ddd60444159
             * agentId : SH001
             * password : $2a$10$AGYjEZaPqV3ZPwB8NJyWhOMozdm5uyu/JXH2LxZIStZTYxzaFIyNu
             * firstName :
             * lastName :
             * sex :
             * company :
             * position :
             * mobile : 13901805167
             * createdAt : 1552895783829
             * lastActivityAt : 1598943905718
             * lastClassAt : 1568276510133
             * icon : /iqb-files/icon/20200812/1597227481214010028.png
             * role : 1
             * blockedAt : 0
             * modifiedAt : 1597227481286
             * datetime : 2019/03/18
             * email :
             * coins : 0
             * nickname : 真正的萱萱
             * username :
             * parentPwd : $2a$10$CPFAzpmetIGnr6vSot7FieVgNAATe6cusuR6lG8PwhatZJqo9scRW
             * license : null
             * birthday : 2014-5-3
             * wxUnionId : oHJD_vxe8VUWByABrjvgjRg5N52Y
             * parentActivityAt : 1598263525092
             * wxOpenId : o_oh9wIHrIlA_msNYLPKipZIkU7s
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
            private String wxUnionId;
            private String parentActivityAt;
            private String wxOpenId;
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

            public String getWxUnionId() {
                return wxUnionId;
            }

            public void setWxUnionId(String wxUnionId) {
                this.wxUnionId = wxUnionId;
            }

            public String getParentActivityAt() {
                return parentActivityAt;
            }

            public void setParentActivityAt(String parentActivityAt) {
                this.parentActivityAt = parentActivityAt;
            }

            public String getWxOpenId() {
                return wxOpenId;
            }

            public void setWxOpenId(String wxOpenId) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.liveId);
        dest.writeString(this.teacherId);
        dest.writeInt(this.time);
        dest.writeLong(this.startAt);
        dest.writeLong(this.endAt);
        dest.writeLong(this.createdAt);
        dest.writeInt(this.year);
        dest.writeInt(this.month);
        dest.writeInt(this.day);
        dest.writeString(this.agentId);
        dest.writeInt(this.type);
        dest.writeByte(this.valid ? (byte) 1 : (byte) 0);
        dest.writeParcelable((Parcelable) this.student, flags);
        dest.writeParcelable((Parcelable) this.teacher, flags);
        dest.writeParcelable((Parcelable) this.streamKey, flags);
        dest.writeList(this.slist);
    }

    public HomeRecordEntity() {
    }

    protected HomeRecordEntity(Parcel in) {
        this.liveId = in.readString();
        this.teacherId = in.readString();
        this.time = in.readInt();
        this.startAt = in.readLong();
        this.endAt = in.readLong();
        this.createdAt = in.readLong();
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
        this.agentId = in.readString();
        this.type = in.readInt();
        this.valid = in.readByte() != 0;
        this.student = in.readParcelable(Object.class.getClassLoader());
        this.teacher = in.readParcelable(Object.class.getClassLoader());
        this.streamKey = in.readParcelable(Object.class.getClassLoader());
        this.slist = new ArrayList<SlistBean>();
        in.readList(this.slist, SlistBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<HomeRecordEntity> CREATOR = new Parcelable.Creator<HomeRecordEntity>() {
        @Override
        public HomeRecordEntity createFromParcel(Parcel source) {
            return new HomeRecordEntity(source);
        }

        @Override
        public HomeRecordEntity[] newArray(int size) {
            return new HomeRecordEntity[size];
        }
    };
}
