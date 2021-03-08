package com.iqb.src.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by Administrator on 2018/1/30.
 */

@SuppressLint("AppCompatCustomView")
public class IQBPlayerTextView extends TextView {
    public IQBPlayerTextView(Context context) {
        super(context);
    }

    public IQBPlayerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IQBPlayerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
