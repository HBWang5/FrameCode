package com.iqb.api.download;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

/**
 * 描述：
 * -下载Notification类，既可用系统默认的通知布局，也可以用自定义的布局
 */
public class DownLoadNotification {
    public final static int DOWNLOAD_CANCEL = -3;
    public final static int DOWNLOAD_COMPLETE = -2;
    public final static int DOWNLOAD_FAIL = -1;
    private Context mContext;
    private NotificationManager manager;
    private NotificationCompat.Builder notifyBuilder;
    /**
     * 通知标题
     */
    private String titleStr;
    /**
     * 通知的唯一标示ID
     */
    private int notificationID;
    /**
     * 通知栏图标
     */
    private int iconID;
    private String channelId = "channel_001";

    /**
     * @param context Activity或Service上下文
     * @param id      通知的唯一标示ID
     */
    public DownLoadNotification(Context context, int id) {
        mContext = context;
        notificationID = id;
        /*实例化NotificationManager以获取系统服务*/
        this.manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 显示系统默认格式通知
     *
     * @param iconId     通知栏图标ID
     * @param titleText  通知栏标题
     * @param contentStr 通知栏内容
     */
    public void showDefaultNotification(int iconId, String titleText, String contentStr) {
        this.titleStr = titleText;
        this.iconID = iconId;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "name";
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(mChannel);
        }
        notifyBuilder = new NotificationCompat.Builder(mContext, channelId)
                /*设置small icon*/
                .setSmallIcon(iconId)
                .setTicker("开始下载...")
                /*设置title*/
                .setContentTitle(titleText)
                /*设为true,notification将无法通过左右滑动的方式清除*/
                .setOngoing(false)
                /*设置进度*/
                .setProgress(100, 0, false)
                /*设置详细文本*/
                .setContentText(contentStr);
        Notification notification = notifyBuilder.build();
        manager.notify(notificationID, notification);
    }


    /**
     * 更改自定义布局文件中的进度条的值
     *
     * @param progress 进度值(0~100)
     */
    public void changeProgressStatus(int progress) {
        if (progress == DOWNLOAD_FAIL) {
            notifyBuilder.setContentText("下载错误，稍后重试！");
            notifyBuilder.setOngoing(false);
            notifyBuilder.setAutoCancel(true);
        } else if (progress == DOWNLOAD_COMPLETE) {
            notifyBuilder.setContentText("下载完成，开始安装");
            notifyBuilder.setOngoing(false);
            notifyBuilder.setAutoCancel(true);

        } else {
            notifyBuilder.setContentText("当前进度(" + progress + "%)");
            notifyBuilder.setOngoing(true);
            notifyBuilder.setAutoCancel(false);
        }
        notifyBuilder.setProgress(100, progress, false);
        manager.notify(notificationID, notifyBuilder.build());
    }

    /**
     * 改变默认通知栏的通知内容
     */
    public void changeNotificationText(String content) {
        notifyBuilder = new NotificationCompat.Builder(mContext, channelId)
                /*设置small icon*/
                .setSmallIcon(iconID)
                /*设置title*/
                .setContentTitle(titleStr)
                /*设置详细文本*/
                .setContentText(content);


        // 设置setLatestEventInfo方法,如果不设置会App报错异常
        //  NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //注册此通知
        // 如果该NOTIFICATION_ID的通知已存在，会显示最新通知的相关信息 ，比如tickerText 等
        manager.notify(notificationID, notifyBuilder.build());
    }

    /**
     * 移除通知
     */
    public void removeNotification() {
        // 取消的只是当前Context的Notification
        manager.cancel(notificationID);
    }


}
