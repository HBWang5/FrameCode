package com.iqb.src.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class IQBPlayerMaxHeightScrollView extends ScrollView {

    private int mMaxHeight;

    public IQBPlayerMaxHeightScrollView(Context context) {
        super(context);
    }

    public IQBPlayerMaxHeightScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IQBPlayerMaxHeightScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void setMaxHeight(int mMaxHeight) {
        this.mMaxHeight = mMaxHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMaxHeight > 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
