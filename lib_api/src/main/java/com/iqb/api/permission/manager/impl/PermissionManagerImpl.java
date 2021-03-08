package com.iqb.api.permission.manager.impl;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.iqb.api.permission.manager.IPermissionManager;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/4/24-16:35
 */
public class PermissionManagerImpl implements IPermissionManager {
    @Override
    public boolean jurisdictionDetection(Context context, @NonNull String... perms) {
        return EasyPermissions.hasPermissions(context, perms);
    }

    @Override
    public void jurisdictionApply(@NonNull Context context, @NonNull String rationale, int requestCode, @NonNull String... perms) {
        EasyPermissions.requestPermissions((AppCompatActivity) context, "此功能需要相机、读写权限，否则无法正常使用",requestCode,perms);
    }


}
