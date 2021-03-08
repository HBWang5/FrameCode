package com.iqb.src.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iqb.src.R;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("AppCompatCustomView")
public class IQBPlayerMsgProgressBar extends TextView {
    private Paint mPaint;
    private int max;
    private int progress;
    private IProEndCallBack iProEndCallBack;

    @SuppressLint("Recycle")
    public IQBPlayerMsgProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        max = 100;
        progress = 0;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Point left_top = new Point(0, 0);
        Point right_bottom = new Point(getWidth(), getHeight());
        double rate = (double) (100 - progress) / (double) max;
        drawProgressBar(canvas, left_top, right_bottom, rate);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();//使得onDraw重绘
    }

    private void drawProgressBar(Canvas canvas, Point left_top, Point right_bottom, double rate) {
        int width = 1;
        int rad = 20;
        mPaint.setStyle(Paint.Style.FILL);
        int x_end = (int) (right_bottom.x * rate);
        RectF rectF2 = new RectF(left_top.x + width, left_top.y + width, x_end - width, right_bottom.y - width);
        canvas.drawRoundRect(rectF2, rad, rad, mPaint);
    }

    public void setColor(boolean isRed) {
        if (isRed) {
            mPaint.setColor(getResources().getColor(R.color.pro_msg_color));
        } else {
            mPaint.setColor(getResources().getColor(R.color.pro_msg_green_color));
        }
    }

    @Override
    public boolean isFocused() {
        return false;
    }

    public void show() {
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (progress == 100) {
                    if (iProEndCallBack != null) {
                        ((AppCompatActivity) getContext()).runOnUiThread(() -> iProEndCallBack.proEndCallBack(IQBPlayerMsgProgressBar.this));
                    }
                    this.cancel();
                    timer.cancel();
                }
                ((AppCompatActivity) IQBPlayerMsgProgressBar.this.getContext()).runOnUiThread(() -> setProgress(++progress));

            }
        };
        timer.schedule(timerTask, 0, 100);

    }

    public void setProEndCallBack(IProEndCallBack iProEndCallBack) {
        this.iProEndCallBack = iProEndCallBack;
    }

    public interface IProEndCallBack {
        void proEndCallBack(IQBPlayerMsgProgressBar iqbPlayerMsgProgressBar);
    }
}