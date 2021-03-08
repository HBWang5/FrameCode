package com.iqb.constants;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Describe：
 * - 单位相关的常量
 *
 */
public interface UnitCons {

    class MemoryCons {
        /******************** 存储相关常量 ********************/

        public static final int BYTE = 1;
        /**
         * KB与Byte的倍数
         */
        public static final int KB = 1024;
        /**
         * MB与Byte的倍数
         */
        public static final int MB = 1048576;
        /**
         * GB与Byte的倍数
         */
        public static final int GB = 1073741824;

        @IntDef({BYTE, KB, MB, GB})
        @Retention(RetentionPolicy.SOURCE)
        public @interface MemoryUnit {
        }
    }

    class TimeCons {

        /******************** 时间相关常量 ********************/
        public static final int MSEC = 1;
        /**
         * 秒与毫秒的倍数
         */
        public static final int SEC = 1000;
        /**
         * 分与毫秒的倍数
         */
        public static final int MIN = 60000;
        /**
         * 时与毫秒的倍数
         */
        public static final int HOUR = 3600000;
        /**
         * 天与毫秒的倍数
         */
        public static final int DAY = 86400000;

        @IntDef({MSEC, SEC, MIN, HOUR, DAY})
        @Retention(RetentionPolicy.SOURCE)
        public @interface TimeUnit {
        }
    }


}
