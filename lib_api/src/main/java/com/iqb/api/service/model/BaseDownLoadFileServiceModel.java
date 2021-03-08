package com.iqb.api.service.model;

import android.content.Context;

import com.iqb.api.R;
import com.iqb.api.download.DownFileThread;
import com.iqb.api.download.DownLoadNotification;
import com.iqb.api.utils.CacheUtils;
import com.iqb.api.utils.FileUtils;
import com.iqb.api.utils.HandlerUtils;
import com.iqb.api.base.baseservice.model.BaseServiceModel;
import java.io.File;
import java.util.UUID;

public class BaseDownLoadFileServiceModel extends BaseServiceModel {
    private static final int NOTIFICATION_ID = UUID.randomUUID().hashCode();
    private DownLoadNotification downLoadNotification;
    private DownFileThread downFileThread;

    public BaseDownLoadFileServiceModel(Context context) {
        super(context);
    }

    public void startDownload(String fileName, String downLoadUrl, HandlerUtils.HandlerHolder downLoadFileServicePresenter) {

        downLoadNotification = new DownLoadNotification(getContext(), NOTIFICATION_ID);
        downLoadNotification.showDefaultNotification(R.drawable.ic_launcher, "琴伴", "正在下载");
        File targetFile = new File(CacheUtils.getDownloadCache(), fileName);
        FileUtils.createOrExistsFile(targetFile);
        downFileThread = new DownFileThread(downLoadFileServicePresenter, downLoadUrl, targetFile);
        new Thread(downFileThread).start();
    }

    public void changeProgressStatus(int downloadComplete) {
        downLoadNotification.changeProgressStatus(downloadComplete);
    }

    public File getApkFile() {
        return downFileThread.getApkFile();
    }

    public void removeNotification() {
        downLoadNotification.removeNotification();
    }

    public void changeNotificationText(String s) {
        downLoadNotification.changeNotificationText(s);
    }
}
