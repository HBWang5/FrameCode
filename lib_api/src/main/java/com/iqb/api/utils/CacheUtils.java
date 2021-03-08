package com.iqb.api.utils;

import android.os.Environment;

import com.iqb.api.base.app.ApiApplication;
import com.iqb.constants.AppConfig;

import java.io.File;


/**
 * 描述：
 * - app缓存管理
 */
public class CacheUtils {
    /**
     * 获取应用程序缓存根文件
     */
    public static File getAppCache() {
        File cacheFile;
        if (SDCardUtils.isSDCardEnable()) {
            cacheFile = new File(Environment.getExternalStorageDirectory(), ApiApplication.getApplication().getPackageName());
        } else {
            cacheFile = new File(Environment.getDataDirectory(),  ApiApplication.getApplication().getPackageName());
        }

        FileUtils.createOrExistsDir(cacheFile);
        return cacheFile;
    }


    /**
     * 下载文件目录
     */
    public static File getDownloadCache() {
        File downloadCacheDir = new File(getAppCache(), AppConfig.FILE_APP_DOWNLOAD);
        FileUtils.createOrExistsDir(downloadCacheDir);
        return downloadCacheDir;
    }


    /**
     * 拍照图片目录
     */
    public static File getCameraCache() {
        File imageCache = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), AppConfig.FILE_CAMERA_CACHE);
        FileUtils.createOrExistsDir(imageCache);
        return imageCache;
    }


    public static void clearCacheFile() {
        FileUtils.deleteDir(getAppCache());
    }


}
