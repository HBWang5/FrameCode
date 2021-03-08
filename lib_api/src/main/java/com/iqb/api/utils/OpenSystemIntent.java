package com.iqb.api.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.List;

public class OpenSystemIntent {
    //调用系统相册-选择图片
    public static final int REQ_CODE_ALBUM = 0x11;
    public static final int REQ_CODE_CAMERA = 0x12;
    private static Uri uri;

    public static void takePhoto(Activity context) {


        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePhotoIntent.resolveActivity(context.getPackageManager()) != null) {
            String fileName = "head" + System.currentTimeMillis() + ".jpg";
            File takeImageFile = new File(CacheUtils.getCameraCache(), fileName);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", takeImageFile);
                //加入uri权限 要不三星手机不能拍照
                List<ResolveInfo> resInfoList = context.getPackageManager()
                        .queryIntentActivities(takePhotoIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo : resInfoList) {
                    String packageName = resolveInfo.activityInfo.packageName;
                    context.grantUriPermission(packageName, uri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
            } else {
                uri = Uri.fromFile(takeImageFile);
            }
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            context.startActivityForResult(takePhotoIntent, REQ_CODE_CAMERA);
        }
    }

    public static Uri getUri() {
        return uri;
    }

    /**
     * 使用相册
     */
    public static void usePhoto(Activity context) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        context.startActivityForResult(intent, REQ_CODE_ALBUM);
    }





}
