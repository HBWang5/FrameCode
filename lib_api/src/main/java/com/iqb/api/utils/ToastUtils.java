package com.iqb.api.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.iqb.api.R;
import com.iqb.api.base.app.ApiApplication;


public class ToastUtils {
    private static boolean isShow = true;
    private static Toast mToast = null;

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showShort(CharSequence message) {
        if (isShow) {
            showCustomToast(ApiApplication.getApplication(), message == null ? "" : message.toString(), Toast.LENGTH_SHORT);
        }
    }

    public static void showShort(@StringRes int resId) {
        showShort(getString(resId));
    }

    public static void showLong(CharSequence message) {
        if (isShow) {
            showCustomToast(ApiApplication.getApplication(), message.toString(), Toast.LENGTH_LONG);
        }
    }

    public static void showLong(@StringRes int resId) {
        showLong(getString(resId));
    }

    public static void show(CharSequence message, int duration) {
        if (isShow) {
            Toast.makeText(ApiApplication.getApplication(), message, duration).show();
//            showCustomToast(ApiApplication.getApplication(), message.toString(), duration);
        }
    }

    private static String getString(@StringRes int resId) {
        return ApiApplication.getApplication().getString(resId);
    }

    /**
     * 自定义toast
     *
     * @param context  application
     * @param message  显示内容
     * @param duration 显示时长
     */
    private static void showCustomToast(Context context, String message, int duration) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_toast, null);
        TextView tvToast = view.findViewById(R.id.tv_toast);
        tvToast.setText(message);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(duration);
        mToast.setView(view);
        mToast.show();
//        Toast toast = Toast.makeText(ApiApplication.getApplication(), message, duration);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
    }

    public static void setIsShow(boolean isShow) {
        ToastUtils.isShow = isShow;
    }
}

