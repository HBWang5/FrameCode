package com.iqb.src.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2018/1/30.
 */

@SuppressLint("AppCompatCustomView")
public class IQBTextView extends TextView {
    public IQBTextView(Context context) {
        super(context);
    }

    public IQBTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IQBTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(String text) {
        if (TextUtils.isEmpty(text)) {
            super.setText("--");
        } else {
            super.setText(text);
        }
    }
}
