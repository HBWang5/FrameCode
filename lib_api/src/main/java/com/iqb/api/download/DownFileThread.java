package com.iqb.api.download;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 描述：
 * - 文件下载线程
 */
public class DownFileThread implements Runnable {
    private final static String TAG = "DownFileThread";
    /**
     * 传入的Handler,用于像Activity或service通知下载进度
     */
    private Handler mHandler;
    /**
     * 下载URL
     */
    private String urlStr;
    /**
     * 文件保存路径
     */
    private File apkFile;
    /**
     * 下载是否完成
     */
    private boolean isFinished;
    /**
     * 是否强制停止下载线程
     */
    private boolean interrupted = false;
    private InputStream iStream = null;
    private FileOutputStream fos = null;
    private BufferedInputStream bis = null;

    public DownFileThread(Handler handler, String urlStr, File targetFile) {
        Log.i(TAG, urlStr);
        this.mHandler = handler;
        this.urlStr = urlStr;
        this.apkFile = targetFile;
        isFinished = false;
    }

    /**
     * 获取下载APK文件
     *
     * @return apk file
     */
    public File getApkFile() {
        if (isFinished) {
            return apkFile;
        } else {
            return null;
        }
    }

    /**
     * @return 是否下载完成
     */
    public boolean isFinished() {
        return isFinished;
    }

    /**
     * 强行终止文件下载
     */
    public void interruptThread() {
        interrupted = true;
    }

    @Override
    public void run() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            try {
                HttpURLConnection conn = connection(urlStr);
                // 必须设置false，否则会自动redirect到Location的地址
                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK
                        || conn.getResponseCode() == HttpURLConnection.HTTP_PARTIAL) {
                    receiverStream(conn);
                } else if (conn.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
                    String location = conn.getHeaderField("Location");
                    HttpURLConnection reConn = connection(location);
                    receiverStream(reConn);
                } else {
                    Log.i(TAG, "请求失败:" + conn.getResponseCode());
                    mHandler.sendEmptyMessage(DownLoadNotification.DOWNLOAD_FAIL);
                }

            } catch (Exception e) {
                Log.i(TAG, "获取文件流失败");
                mHandler.sendEmptyMessage(DownLoadNotification.DOWNLOAD_FAIL);
            } finally {
                closeStream(iStream, bis, fos);
            }
        } else {
            Log.i(TAG, "外部存储卡不存在，下载失败！");
            mHandler.sendEmptyMessage(DownLoadNotification.DOWNLOAD_FAIL);
        }
    }

    private HttpURLConnection connection(String targetUrl) throws IOException {
        URL url = new URL(targetUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(20000);
        conn.setRequestProperty("Range", "bytes=0-");
        conn.setInstanceFollowRedirects(false);
        return conn;
    }

    private void receiverStream(HttpURLConnection conn) throws IOException {
        iStream = conn.getInputStream();
        fos = new FileOutputStream(apkFile);
        bis = new BufferedInputStream(iStream);
        byte[] buffer = new byte[1024];
        // 获取文件总长度
        int length = conn.getContentLength();
        //最大进度转化为100
        double rate = (double) 100 / length;
        int total = 0;
        //设置更新频率，频繁操作UI线程会导致系统奔溃
        int times = 0;
        Log.i("threadStatus", "开始下载");
        int len = 0;
        while (!interrupted && ((len = bis.read(buffer)) != -1)) {
            fos.write(buffer, 0, len);
            // 获取已经读取长度
            total += len;
            int p = (int) (total * rate);
            if (times >= 512 || p == 100) {
                /*这是防止频繁地更新通知，而导致系统变慢甚至崩溃。 非常重要。。。。。*/
                times = 0;
                Message msg = Message.obtain();
                msg.what = p;
                mHandler.sendMessage(msg);
            }
            times++;
        }
        if (total == length) {
            isFinished = true;
            mHandler.sendEmptyMessage(DownLoadNotification.DOWNLOAD_COMPLETE);
            Log.i(TAG, "下载完成结束");
        } else {
            Log.i(TAG, "强制中途结束");
            mHandler.sendEmptyMessage(DownLoadNotification.DOWNLOAD_CANCEL);
        }
    }

    private void closeStream(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (Exception e) {
                mHandler.sendEmptyMessage(DownLoadNotification.DOWNLOAD_FAIL);
            }
        }
    }

}
