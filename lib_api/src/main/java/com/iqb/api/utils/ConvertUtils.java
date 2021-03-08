package com.iqb.api.utils;

import android.annotation.SuppressLint;
import android.content.Context;


import com.iqb.constants.UnitCons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * <pre>
 *     desc  : 转换相关工具类
 * </pre>
 */
public class ConvertUtils {

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 以unit为单位的内存大小转字节数
     *
     * @param memorySize 大小
     * @param unit       单位类型
     *                   <ul>
     *                   <li>{@link UnitCons.MemoryCons#BYTE}: 字节</li>
     *                   <li>{@link UnitCons.MemoryCons#KB}  : 千字节</li>
     *                   <li>{@link UnitCons.MemoryCons#MB}  : 兆</li>
     *                   <li>{@link UnitCons.MemoryCons#GB}  : GB</li>
     *                   </ul>
     * @return 字节数
     */
    public static long memorySize2Byte(long memorySize, @UnitCons.MemoryCons.MemoryUnit int unit) {
        if (memorySize < 0) {
            return -1;
        }
        long byteValue;
        switch (unit) {
            default:
            case UnitCons.MemoryCons.BYTE:
                byteValue = memorySize;
                break;
            case UnitCons.MemoryCons.KB:
                byteValue = memorySize * UnitCons.MemoryCons.KB;
                break;
            case UnitCons.MemoryCons.MB:
                byteValue = memorySize * UnitCons.MemoryCons.MB;
                break;
            case UnitCons.MemoryCons.GB:
                byteValue = memorySize * UnitCons.MemoryCons.GB;
                break;
        }
        return byteValue;
    }

    /**
     * 字节数转以unit为单位的内存大小
     *
     * @param byteNum 字节数
     * @param unit    单位类型
     *                <ul>
     *                <li>{@link UnitCons.MemoryCons#BYTE}: 字节</li>
     *                <li>{@link UnitCons.MemoryCons#KB}  : 千字节</li>
     *                <li>{@link UnitCons.MemoryCons#MB}  : 兆</li>
     *                <li>{@link UnitCons.MemoryCons#GB}  : GB</li>
     *                </ul>
     * @return 以unit为单位的size
     */
    public static double byte2MemorySize(long byteNum, @UnitCons.MemoryCons.MemoryUnit int unit) {
        if (byteNum < 0) {
            return -1;
        }
        double memorySize;
        switch (unit) {
            default:
            case UnitCons.MemoryCons.BYTE:
                memorySize = (double) byteNum;
                break;
            case UnitCons.MemoryCons.KB:
                memorySize = (double) byteNum / UnitCons.MemoryCons.KB;
                break;
            case UnitCons.MemoryCons.MB:
                memorySize = (double) byteNum / UnitCons.MemoryCons.MB;
                break;
            case UnitCons.MemoryCons.GB:
                memorySize = (double) byteNum / UnitCons.MemoryCons.GB;
                break;
        }
        return memorySize;
    }

    /**
     * 字节数转合适内存大小
     * <p>保留2位小数</p>
     *
     * @param byteNum 字节数
     * @return 合适内存大小
     */
    @SuppressLint("DefaultLocale")
    public static String byte2FitMemorySize(long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < UnitCons.MemoryCons.KB) {
            return String.format("%.1fB", byteNum + 0.005f);
        } else if (byteNum < UnitCons.MemoryCons.MB) {
            return String.format("%.1fKB", byteNum * 1f / UnitCons.MemoryCons.KB + 0.005);
        } else if (byteNum < UnitCons.MemoryCons.GB) {
            return String.format("%.1fMB", byteNum * 1f / UnitCons.MemoryCons.MB + 0.005);
        } else {
            return String.format("%.1fGB", byteNum * 1f / UnitCons.MemoryCons.GB + 0.005);
        }
    }

    /**
     * 以unit为单位的时间长度转毫秒时间戳
     *
     * @param timeSpan 毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link UnitCons.TimeCons#MSEC}: 毫秒</li>
     *                 <li>{@link UnitCons.TimeCons#SEC }: 秒</li>
     *                 <li>{@link UnitCons.TimeCons#MIN }: 分</li>
     *                 <li>{@link UnitCons.TimeCons#HOUR}: 小时</li>
     *                 <li>{@link UnitCons.TimeCons#DAY }: 天</li>
     *                 </ul>
     * @return 毫秒时间戳
     */
    public static long timeSpan2Millis(long timeSpan, @UnitCons.TimeCons.TimeUnit int unit) {
        long millis;
        switch (unit) {
            default:
            case UnitCons.TimeCons.MSEC:
                millis = timeSpan;
                break;
            case UnitCons.TimeCons.SEC:
                millis = timeSpan * UnitCons.TimeCons.SEC;
                break;
            case UnitCons.TimeCons.MIN:
                millis = timeSpan * UnitCons.TimeCons.MIN;
                break;
            case UnitCons.TimeCons.HOUR:
                millis = timeSpan * UnitCons.TimeCons.HOUR;
                break;
            case UnitCons.TimeCons.DAY:
                millis = timeSpan * UnitCons.TimeCons.DAY;
                break;
        }
        return millis;
    }

    /**
     * 毫秒时间戳转以unit为单位的时间长度
     *
     * @param millis 毫秒时间戳
     * @param unit   单位类型
     *               <ul>
     *               <li>{@link UnitCons.TimeCons#MSEC}: 毫秒</li>
     *               <li>{@link UnitCons.TimeCons#SEC }: 秒</li>
     *               <li>{@link UnitCons.TimeCons#MIN }: 分</li>
     *               <li>{@link UnitCons.TimeCons#HOUR}: 小时</li>
     *               <li>{@link UnitCons.TimeCons#DAY }: 天</li>
     *               </ul>
     * @return 以unit为单位的时间长度
     */
    public static long millis2TimeSpan(long millis, @UnitCons.TimeCons.TimeUnit int unit) {
        long timeSpan;
        switch (unit) {
            default:
            case UnitCons.TimeCons.MSEC:
                timeSpan = millis;
                break;
            case UnitCons.TimeCons.SEC:
                timeSpan = millis / UnitCons.TimeCons.SEC;
                break;
            case UnitCons.TimeCons.MIN:
                timeSpan = millis / UnitCons.TimeCons.MIN;
                break;
            case UnitCons.TimeCons.HOUR:
                timeSpan = millis / UnitCons.TimeCons.HOUR;
                break;
            case UnitCons.TimeCons.DAY:
                timeSpan = millis / UnitCons.TimeCons.DAY;
                break;
        }
        return timeSpan;
    }

    /**
     *日期格式字符串转换时间戳
     */
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getDayOfWeek(String dateTime) {
        Calendar cal = Calendar.getInstance();
        if (dateTime.equals("")) {
            cal.setTime(new Date(System.currentTimeMillis()));
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date;
            try {
                date = sdf.parse(dateTime);
            } catch (ParseException e) {
                date = null;
                e.printStackTrace();
            }
            if (date != null) {
                cal.setTime(new Date(date.getTime()));
            }
        }
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";

        }
        return "";
    }
    /**
     * 时间戳转日期
     */
    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }
    /**
     * 毫秒时间戳转合适时间长度
     *
     * @param millis    毫秒时间戳
     *                  <p>小于等于0，返回null</p>
     * @param precision 精度
     *                  <p>precision = 0，返回null</p>
     *                  <p>precision = 1，返回天</p>
     *                  <p>precision = 2，返回天和小时</p>
     *                  <p>precision = 3，返回天、小时和分钟</p>
     *                  <p>precision = 4，返回天、小时、分钟和秒</p>
     *                  <p>precision >= 5，返回天、小时、分钟、秒和毫秒</p>
     * @return 合适时间长度
     */
    @SuppressLint("DefaultLocale")
    public static String millis2FitTimeSpan(long millis, int precision) {
        if (millis <= 0 || precision <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String[] units = {"天", "小时", "分钟", "秒", "毫秒"};
        int[] unitLen = {86400000, 3600000, 60000, 1000, 1};
        precision = Math.min(precision, 5);
        for (int i = 0; i < precision; i++) {
            if (millis >= unitLen[i]) {
                long mode = millis / unitLen[i];
                millis -= mode * unitLen[i];
                sb.append(mode).append(units[i]);
            }
        }
        return sb.toString();
    }

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context 上下文
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param context 上下文
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param context 上下文
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}