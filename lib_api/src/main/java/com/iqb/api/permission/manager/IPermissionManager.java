package com.iqb.api.permission.manager;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/4/24-16:22
 */
public interface IPermissionManager {
    /**
     * 权限检测
     */
    boolean  jurisdictionDetection(Context context, @NonNull String... perms);
    /**
     * 权限申请
     */
    void jurisdictionApply(Context context, @NonNull String rationale,
                           int requestCode, @NonNull String... perms);

}
