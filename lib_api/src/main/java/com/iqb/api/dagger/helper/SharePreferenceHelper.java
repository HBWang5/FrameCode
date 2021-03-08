package com.iqb.api.dagger.helper;


import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.utils.SPHelper;
import com.iqb.been.user.TeacherData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SharePreferenceHelper {
    @Inject
    SharePreferenceHelper() {
    }


    public void init() {
    }

    public boolean isFirstOpen() {
        return SPHelper.isFirstOpen();
    }

    public void saveUserName(String mobile) {
        SPHelper.saveUserName(mobile);
    }

    public void savePassword(String password) {
        SPHelper.savePassword(password);
    }

    public void saveNickName(String nickname) {
        SPHelper.saveNickName(nickname);
    }

    public void saveIcon(String icon) {
        SPHelper.saveIcon(icon);
    }

    public void saveUserId(String userId) {
        SPHelper.saveUserId(userId);
    }

    public void saveAccessToken(String auth) {
        SPHelper.saveAccessToken(auth);
    }

    public String getAccessToken() {
        return SPHelper.getAccessToken();
    }

    public void changeLogin(boolean b) {
        SPHelper.changeLogin(ApiApplication.getApplication(), b);
    }

    public String getUserName() {
        return SPHelper.getMobile();
    }

    public String getPassword() {
        return SPHelper.getPassword();
    }

    public String getUserIcon() {
        return SPHelper.getIcon();
    }

    public void saveWeekTime(String toJson) {
        SPHelper.saveWeekTime(toJson);
    }

    public String getWeekTime() {
        return SPHelper.getWeekTime();
    }

    public String getUserID() {
        return SPHelper.getUserId();
    }

    public String getPhoneNum() {
        return SPHelper.getPhoneNum();
    }

    public String getSex() {
        return SPHelper.getSex();
    }

    public String getBirthday() {
        return SPHelper.getUserBirthday();
    }

    public String getIntroduce() {
        return SPHelper.getIntroduce();
    }

    public void saveBirthday(String birthday) {
        SPHelper.saveBirthday(birthday);
    }

    public void saveIntroduce(String intro) {
        SPHelper.saveIntroduce(intro);
    }

    public void saveSex(String sex) {
        SPHelper.saveSex(sex);
    }

    public void savePhone(String mobile) {
        SPHelper.savePhone(mobile);
    }

    public String getNickName() {
        return SPHelper.getNickName();
    }

    public void saveTeacherData(String toJson) {
        SPHelper.saveTeacherData(toJson);
    }

    public String getTeacherData() {
        return SPHelper.getTeacherData();
    }

    public void saveRoomId(String roomId) {
        SPHelper.saveRoomId(roomId);
    }

    public String getRoomId() {
        return SPHelper.getRoomId();
    }

    public void saveAgoraUid(int agoraUid) {
        SPHelper.saveAgoraUid(agoraUid);
    }

    public int getAgoraUid() {
        return SPHelper.getAgoraUid();
    }

    public void saveSoundModel(int b) {
        SPHelper.saveSoundModel(b);
    }

    public int getSoundModel() {
        return SPHelper.getSoundModel();
    }

    public void saveRoomPwd(String pwd) {
        SPHelper.saveRoomPwd(pwd);
    }

    public String getRoomPwd() {
        return SPHelper.getRoomPwd();
    }

    public void saveCourseLen(String courseLen) {
        SPHelper.saveCourseLen(courseLen);
    }

    public String getCourseLen() {
        return SPHelper.getCourseLen();
    }


    public void clearUserData() {
        SPHelper.clear();
        SPHelper.isNotFirstOpen();
    }

    public void saveIsUpData(boolean b) {
        SPHelper.saveIsUpData(b);
    }

    public boolean getIsUpData() {
        return SPHelper.getIsUpData();
    }

    public void saveWinState(int i) {
        SPHelper.saveWinState(i);
    }

    public int getWinState() {
        return SPHelper.getWinState();
    }

    public void saveDrawState(int i) {
        SPHelper.saveDrawState(i);
    }

    public int getDrawState() {
        return SPHelper.getDrawState();
    }

    public void saveStaffState(int i) {
        SPHelper.saveStaffState(i);
    }

    public int getStaffState() {
        return SPHelper.getStaffState();
    }
}
