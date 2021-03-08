package com.iqb.src.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("AppCompatCustomView")
public class IQBPlayerScrollTextView extends TextView {
    private static final String TAG = "ScrollTextView";
    private String mText = "蒹葭苍苍，白露为霜。所谓伊人，在水一方。";
    private int mOffsetX = 0;
    private Rect mRect;
    private Timer mTimer;
    private TimerTask mTimerTask;
    /**
     * 速度，负数左移，正数右移。
     */
    private int mSpeed = -10;
    private static final int PFS = 24;

    public IQBPlayerScrollTextView(Context context) {
        this(context, null);
    }


    public IQBPlayerScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

//        initTimer();
    }

    private void initTimer() {
        if (mTimerTask != null || mTimerTask != null) {
            return;
        }
        mOffsetX = 0;
        mRect = new Rect();
        mTimer = new Timer();
        mTimerTask = new MyTimerTask();
        //更新帧率24
        mTimer.schedule(mTimerTask, 0, 1000 / PFS);
    }

    private class MyTimerTask extends TimerTask {
        @SuppressLint("NewApi")
        @Override
        public void run() {
            //如果View能容下所有文字，直接返回
            if (mRect.right < getWidth()) {
                return;
            }
            if (mOffsetX < -mRect.right - getPaddingEnd()) {
                //左移时的情况
                mOffsetX = getPaddingStart();
            } else if (mOffsetX > getPaddingStart()) {
                //右移时的情况
                mOffsetX = -mRect.right;
            }
            mOffsetX += mSpeed;
            postInvalidate();
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        initTimer();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //此处去掉了super.onDraw(Canvas canvas);
        mText = getText().toString();
        TextPaint textPaint = getPaint();
        textPaint.setColor(getCurrentTextColor());
        //获取文本区域大小，保存在mRect中。
        textPaint.getTextBounds(mText, 0, mText.length(), mRect);
        float mTextCenterVerticalToBaseLine =
                (-textPaint.ascent() + textPaint.descent()) / 2 - textPaint.descent();
        if (mRect.right < getWidth()) {
            canvas.drawText(mText, 0, getHeight() / 2 + mTextCenterVerticalToBaseLine, textPaint);
        } else {
            canvas.drawText(mText, mOffsetX, getHeight() / 2 + mTextCenterVerticalToBaseLine, textPaint);
        }
    }

    /**
     * 视图移除时销毁任务和定时器
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "killTimer");
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void setSpeed(int speed) {
        this.mSpeed = speed;
    }

}
