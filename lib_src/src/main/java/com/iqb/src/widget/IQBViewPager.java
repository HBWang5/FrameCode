package com.iqb.src.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class IQBViewPager extends ViewPager {
    public IQBViewPager(@NonNull Context context) {
        super(context);
    }

    public IQBViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
