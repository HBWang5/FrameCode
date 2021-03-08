package com.iqb.src.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/**
 * Created by Administrator on 2018/1/30.
 */

@SuppressLint("AppCompatCustomView")
public class IQBImageButton extends ImageButton {
    public IQBImageButton(Context context) {
        super(context);
    }

    public IQBImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IQBImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
