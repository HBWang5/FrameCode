package com.iqb.constants;




/**
 * 公共参数的配置.
 *
 * @author Jacob.Wu
 */
public interface Config {
    /**
     * 是否联网
     */
    public static boolean IS_INTERNET_CONNECTED = false;

    public static final int SINGLE_THREAD_EXECUTOR = 0;
    public static final int SCHEDULED_THREAD_EXECUTOR = 1;
    public static final int SFIXED_THREAD_EXECUTOR = 2;
    public static final int CACHED_THREAD_EXECUTOR = 3;
}
