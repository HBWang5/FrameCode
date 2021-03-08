package com.iqb.api.utils;

import android.app.Dialog;


public class DialogUtils {

    public static void safeShowDialog(Dialog dialog) {
        try {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception exception) {
        }

    }

    public static void safeCloseDialog(Dialog dialog) {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        } catch (Exception exception) {
        }

    }
}
