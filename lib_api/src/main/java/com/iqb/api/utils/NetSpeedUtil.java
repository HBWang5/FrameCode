package com.iqb.api.utils;


import android.net.TrafficStats;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static com.yalantis.ucrop.UCropFragment.TAG;

public class NetSpeedUtil {
    private static long lastTotalRxBytes = 0;
    private static long lastTotalTxBytes = 0;
    private static long lastTimeStamp = 0;

    public static String getNetSpeed(int uid) {
        long nowTotalRxBytes = getTotalRxBytes(uid);
        long nowTotalTxBytes = getTotalTxBytes(uid);
        long nowTimeStamp = System.currentTimeMillis();
        Log.d(TAG, "网络-x----速度-nowTotalTxBytes:" + nowTotalTxBytes + " 时间：" + (nowTimeStamp - lastTimeStamp) + "差量：" + (nowTotalTxBytes - lastTotalTxBytes) + " nowTotalTxBytes:" + nowTotalTxBytes + " lastTotalTxBytes:" + lastTotalTxBytes);
        long speedRx = ((nowTotalRxBytes - lastTotalRxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换
        long speedTx = ((nowTotalTxBytes - lastTotalTxBytes) * 1000 / (nowTimeStamp - lastTimeStamp));//毫秒转换
        lastTimeStamp = nowTimeStamp;
        lastTotalRxBytes = nowTotalRxBytes;
        lastTotalTxBytes = nowTotalTxBytes;
        Log.d(TAG, "网络-x-速度下行:" + (speedRx + " kB/s") + " 上行:" + speedTx + " kB/s");
        return speedRx + "";
//                + " kB/s";
    }


    /**
     * 下行 700kB/s 直播临界
     * 实际用到的流量
     *
     * @param uid getApplicationInfo().uid 当前线程
     * @return
     */
    public static long getTotalRxBytes(int uid) {
        return TrafficStats.getUidRxBytes(uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalRxBytes() / 1024);//转为KB
    }

    /**
     * 上行实际用到的流量
     *
     * @param uid getApplicationInfo().uid 当前线程
     * @return
     */
    public static long getTotalTxBytes(int uid) {
        return TrafficStats.getUidRxBytes(uid) == TrafficStats.UNSUPPORTED ? 0 : (TrafficStats.getTotalTxBytes() / 1024);//转为KB
    }

    private Long getTotalBytesManual(int localUid) {

        File dir = new File("/proc/uid_stat/");
        String[] children = dir.list();
        if (!Arrays.asList(children).contains(String.valueOf(localUid))) {
            return 0L;
        }
        File uidFileDir = new File("/proc/uid_stat/" + String.valueOf(localUid));
        File uidActualFileReceived = new File(uidFileDir, "tcp_rcv");
        File uidActualFileSent = new File(uidFileDir, "tcp_snd");

        String textReceived = "0";
        String textSent = "0";

        try {
            BufferedReader brReceived = new BufferedReader(new FileReader(uidActualFileReceived));
            BufferedReader brSent = new BufferedReader(new FileReader(uidActualFileSent));
            String receivedLine;
            String sentLine;

            if ((receivedLine = brReceived.readLine()) != null) {
                textReceived = receivedLine;
            }
            if ((sentLine = brSent.readLine()) != null) {
                textSent = sentLine;
            }

        } catch (IOException e) {

        }
        return Long.valueOf(textReceived).longValue() + Long.valueOf(textSent).longValue();
    }
}