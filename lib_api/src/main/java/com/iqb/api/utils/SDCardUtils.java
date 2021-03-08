package com.iqb.api.utils;

import android.os.Environment;

/**
 * Describe：
 * -
 *
 */
public class SDCardUtils {

    /**
     * 判断SD卡是否可用
     *
     * @return true : 可用<br>false : 不可用
     */
    public static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
