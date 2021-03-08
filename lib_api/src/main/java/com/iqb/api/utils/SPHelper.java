package com.iqb.api.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.iqb.api.BuildConfig;
import com.iqb.api.base.app.ApiApplication;


public class SPHelper {
    /**
     * 文件名
     */
    private static final String SP_CONFIG_NAME = "AppConfig";

    /**
     * 字段
     */
    private static final String SP_IS_LOGIN = "isLogin";
    private static final String SP_IS_FIRST = "isFirstOpen";
    private static final String SP_LAST_LOGIN_TIME = "lastLoginTime";
    private static final String SP_VERSION_NAME = "versionName";
    private static final String SP_ACCESS_TOKEN = "accessToken";
    private static final String SP_IMEI = "sp_imei";
    private static final String SP_USER_NAME = "SP_USER_NAME";
    private static final String SP_USER_ID = "SP_USER_ID";
    private static final String SP_UID = "SP_UID";
    private static final String SP_USER_BIRTHDAY = "SP_USER_Birthday";
    private static final String SP_USER_PASSWORD = "SP_USER_PASSWORD";
    private static final String SINATURE = "SINATURE";
    private static final String SP_NICK_USER_NAME = "SP_NICK_USER_NAME";
    private static final String SP_ICON = "SP_ICON";
    private static final String SP_WEEK_TIME = "SP_WEEK_TIME";

    private static final String SP_TEACHER_INTRODUCE = "SP_TEACHER_INTRODUCE";
    private static final String SP_TEACHER_SEX = "SP_TEACHER_SEX";
    private static final String SP_TEACHER_PHONE = "SP_TEACHER_PHONE";
    private static final String SP_TEACHER_DATA = "SP_TEACHER_DATA";

    private static final String SP_ROOM_ID = "SP_ROOM_ID";
    private static final String SP_ROOM_PASSWORD = "SP_ROOM_PASSWORD";
    private static final String SP_AGORA_UID = "SP_AGORA_UID";

    private static final String SP_SOUND_MODEL = "SP_SOUND_MODEL";

    private static final String COURSE_LEN = "COURSE_LEN";
    private static final String SP_IS_UP_DATA = "SP_IS_UPDATA";
    private static final String WIN_STATE = "WIN_STATE";
    private static final String DRAW_STATE = "DRAW_STATE";
    private static final String STAFF_STATE = "STAFF_STATE";

    /**
     * 是否是第一次打开应用
     * （包含更新后第一次打开应用）
     */
    public static boolean isFirstOpen() {
        String versionName = (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_VERSION_NAME, BuildConfig.VERSION_NAME);
        boolean isFirst = (boolean) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_IS_FIRST, true);
        if (isFirst) {
            SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_IS_FIRST, false);
        }
        if (!AppUtils.getVersion().equals(versionName)) {
            isFirst = true;
            SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_VERSION_NAME, BuildConfig.VERSION_NAME);
        }
        return isFirst;
    }

    /**
     * 用户是否已登录
     */
    public static boolean isLogin() {
        return (boolean) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_IS_LOGIN, false);
    }

    /**
     * 改变用户登录状态
     */
    public static void changeLogin(Context context, boolean isLogin) {
        SPUtils.put(context, SP_CONFIG_NAME, SP_IS_LOGIN, isLogin);
        if (isLogin) {
            SPUtils.put(context, SP_CONFIG_NAME, SP_LAST_LOGIN_TIME, System.currentTimeMillis());
        }
    }

    /**
     * 存储当前账号AccessToken
     */
    public static void saveAccessToken(String token) {
        Log.d("SPHelper", token);
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_ACCESS_TOKEN, token);
    }

    /**
     * 获取当前账号AccessToken
     */
    public static String getAccessToken() {
        if (isLogin()) {
            Log.d("SPHelper", (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_ACCESS_TOKEN, ""));
            return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_ACCESS_TOKEN, "");
        }
        return "";
    }


    /**
     * 获取imei（为防止无读取手机权限）
     */
    public static String getIMEI() {
        SharedPreferences sp = ApiApplication.getApplication().getSharedPreferences(SP_CONFIG_NAME,
                Context.MODE_PRIVATE);
        String imei = sp.getString(SP_IMEI, "");
        if (TextUtils.isEmpty(imei)) {
            imei = DeviceUtils.getIMEI(ApiApplication.getApplication());
            saveIMEI(imei);
        }
        return imei;
    }

    /**
     * 存储IMEI（存储不加密）
     */
    public static void saveIMEI(String imei) {
        SharedPreferences sp = ApiApplication.getApplication().getSharedPreferences(SP_CONFIG_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_IMEI, imei);
        editor.apply();
    }


    public static String getIMEI15Byte() {
        SharedPreferences sp = ApiApplication.getApplication().getSharedPreferences(SP_CONFIG_NAME,
                Context.MODE_PRIVATE);
        String imei = sp.getString(SP_IMEI, "");
        if (TextUtils.isEmpty(imei)) {
            imei = DeviceUtils.getIMEI(ApiApplication.getApplication());
            saveIMEI(imei);
        }
        if (imei.length() == 14) {
            imei = "0" + imei;
        } else if (imei.length() > 15) {
            imei = imei.substring(0, 15);
        } else if (imei.length() != 15) {
            imei = "000000000000000";
        }
        return imei;
    }

    public static String getPassword() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_USER_PASSWORD, "");
    }

    public static String getMobile() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_USER_NAME, "");
    }

    public static String getNickName() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_NICK_USER_NAME, "");
    }

    public static String getIcon() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_ICON, "");
    }

    public static String getSignature() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SINATURE, "");
    }

    public static String getUserId() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_USER_ID, "");
    }

    public static String getUserBirthday() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_USER_BIRTHDAY, "--");
    }

    public static void saveUserName(String num) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_USER_NAME, num);
    }

    public static void savePassword(String password) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_USER_PASSWORD, password);
    }

    public static void saveSignature(Object signature) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SINATURE, signature);
    }

    public static void saveNickName(String name) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_NICK_USER_NAME, name);
    }

    public static void saveIcon(String icon) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_ICON, icon);
    }

    public static void saveUserId(String userId) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_USER_ID, userId);
    }

    public static void saveBirthday(String birthday) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_USER_BIRTHDAY, birthday);
    }


    public static void clear() {
        SPUtils.clear(ApiApplication.getApplication(), SP_CONFIG_NAME);
    }

    public static void isNotFirstOpen() {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_IS_FIRST, false);
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_VERSION_NAME, BuildConfig.VERSION_NAME);
    }

    public static void saveWeekTime(String toJson) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_WEEK_TIME, toJson);
    }

    public static String getWeekTime() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_WEEK_TIME, "");
    }

    public static void saveIntroduce(String intro) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_TEACHER_INTRODUCE, intro);
    }

    public static void saveSex(String sex) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_TEACHER_SEX, sex);
    }

    public static void savePhone(String mobile) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_TEACHER_PHONE, mobile);
    }

    public static String getPhoneNum() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_TEACHER_PHONE, "--");
    }

    public static String getSex() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_TEACHER_SEX, "--");
    }

    public static String getIntroduce() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_TEACHER_INTRODUCE, "--");
    }

    public static void saveTeacherData(String toJson) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_TEACHER_DATA, toJson);
    }

    public static String getTeacherData() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_TEACHER_DATA, "");
    }

    public static void saveRoomId(String roomId) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_ROOM_ID, roomId);
    }

    public static String getRoomId() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_ROOM_ID, "");
    }

    public static void saveAgoraUid(int agoraUid) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_AGORA_UID, agoraUid);
    }

    public static int getAgoraUid() {
        return (int) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_AGORA_UID, 0);
    }

    public static void saveSoundModel(int b) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_SOUND_MODEL, b);
    }

    public static int getSoundModel() {
        return (int) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_SOUND_MODEL, 0);
    }

    public static void saveRoomPwd(String pwd) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_ROOM_PASSWORD, pwd);
    }

    public static String getRoomPwd() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_ROOM_PASSWORD, "");
    }

    public static void saveCourseLen(String courseLen) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, COURSE_LEN, courseLen);
    }

    public static String getCourseLen() {
        return (String) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, COURSE_LEN, "0");
    }

    public static void saveIsUpData(boolean b) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_IS_UP_DATA, b);
    }


    public static boolean getIsUpData() {
        return (boolean) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, SP_IS_UP_DATA, true);
    }

    public static void saveWinState(int i) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, WIN_STATE, i);
    }

    public static int getWinState() {
        return (int) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, WIN_STATE, 0);
    }

    public static void saveDrawState(int i) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, DRAW_STATE, i);
    }

    public static int getDrawState() {
        return (int) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, DRAW_STATE, 0);
    }

    public static void saveStaffState(int i) {
        SPUtils.put(ApiApplication.getApplication(), SP_CONFIG_NAME, STAFF_STATE, i);
    }

    public static int getStaffState() {
        return (int) SPUtils.get(ApiApplication.getApplication(), SP_CONFIG_NAME, STAFF_STATE, 0);
    }
}
