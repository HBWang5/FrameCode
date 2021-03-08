package com.iqb.src.widget.tools;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;

/**
 * 描述：
 * - 弹出pop
 */
public class BackgroundUtils {
    /**
     * 设置添加屏幕的背景透明度  1,：全透明；0.5：半透明  0~1，取自己想到的透明度
     */
    public static void backgroundAlpha(Context mContext, float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        //0.0-1.0
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
        if (bgAlpha < 1f) {
            ((Activity) mContext).getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            ((Activity) mContext).getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
    }
}
