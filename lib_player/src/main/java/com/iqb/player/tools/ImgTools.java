package com.iqb.player.tools;

import android.widget.ImageView;

import java.lang.reflect.Field;

public class ImgTools {
    public static int getViewFieldValue(Object obj, String fieldName) {
        int value = 0;
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = field.getInt(obj);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                value = fieldValue;
            }
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }
}
