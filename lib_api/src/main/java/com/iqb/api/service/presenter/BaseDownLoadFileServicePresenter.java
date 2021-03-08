package com.iqb.api.service.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.core.content.FileProvider;

import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.base.baseservice.presenter.BaseServicePresenter;
import com.iqb.api.download.DownLoadNotification;
import com.iqb.api.service.model.BaseDownLoadFileServiceModel;
import com.iqb.api.service.view.BaseDownLoadAPKService;
import com.iqb.api.utils.CacheUtils;
import com.iqb.api.utils.FileUtils;
import com.iqb.api.utils.HandlerUtils;
import com.iqb.constants.VariableConfig;

import java.io.File;


public class BaseDownLoadFileServicePresenter extends BaseServicePresenter<BaseDownLoadFileServiceModel, BaseDownLoadAPKService> implements HandlerUtils.OnReceiveMessageListener, IBaseDownLoadFileServicePresenter {
    public static final String INTENT_SAVE_NAME = "service.intent.save_name";
    public static final String INTENT_DOWNLOAD_URL = "service.intent.download_url";

    public BaseDownLoadFileServicePresenter(Context context) {
        super(context);
    }

    private String fileName;

    @Override
    public BaseDownLoadFileServiceModel bindModel() {
        return new BaseDownLoadFileServiceModel(getContext());
    }

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case DownLoadNotification.DOWNLOAD_COMPLETE:
                install(getModel().getApkFile());
                getModel().changeProgressStatus(DownLoadNotification.DOWNLOAD_COMPLETE);
                //停止服务
                getModel().removeNotification();
                getView().stopSelf();
                VariableConfig.IS_UP_DATA = true;
                break;
            case DownLoadNotification.DOWNLOAD_FAIL:
                //下载失败
                getModel().changeProgressStatus(DownLoadNotification.DOWNLOAD_FAIL);
                getModel().changeNotificationText("下载错误，请稍后重试。");
                FileUtils.deleteFile(new File(CacheUtils.getDownloadCache(), this.fileName));
                getView().stopSelf();
                break;
            case DownLoadNotification.DOWNLOAD_CANCEL:
                getModel().removeNotification();
                getView().stopSelf();
                break;
            default:  //下载中
                getModel().changeProgressStatus(msg.what);
        }
    }

    private void install(File file) {
        //兼容8.0
        boolean installAllowed;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            installAllowed = ApiApplication.getApplication().getPackageManager().canRequestPackageInstalls();
            if (installAllowed) {
                installApk(file);
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:" + ApiApplication.getApplication().getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ApiApplication.getApplication().startActivity(intent);
                installApk(file);
            }
        } else {
            installApk(file);
        }

    }

    //安装apk，兼容7.0
    private void installApk(File file) {
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签   setFlags要放在addFlags之前
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //版本在7.0以上是不能直接通过uri访问的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //参数1 上下文, 参数2 Provider主机地址和清单文件中保持一致   参数3 共享的文件
            Uri apkUri = FileProvider.getUriForFile(ApiApplication.getApplication(), ApiApplication.getApplication().getPackageName() + ".fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        }
        ApiApplication.getApplication().startActivity(intent);
    }

    @Override
    public void initDownLoadFile() {

    }

    @Override
    public void initConfig(Intent intent) {

        HandlerUtils.HandlerHolder updateHandler = new HandlerUtils.HandlerHolder(this);

        if (intent != null && intent.getExtras() != null) {
            String downLoadUrl = intent.getExtras().getString(INTENT_DOWNLOAD_URL);
            fileName = intent.getExtras().getString(INTENT_SAVE_NAME);
            if (TextUtils.isEmpty(fileName)) {
                fileName = "IQB_" + System.currentTimeMillis() + ".apk";
            }
            getModel().startDownload(fileName, downLoadUrl, updateHandler);
        }
    }

}
