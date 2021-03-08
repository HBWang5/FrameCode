package com.iqb.src.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.iqb.src.R;


public class IQBLoadingPointView extends View {
    public static final int MESSAGE_ID = 0;
    //白色圆点
    private Paint mWhitePaint;
    //绿色圆点
    private Paint mGreenPaint;
    //半径
    private int mRadius = getResources().getDimensionPixelSize(R.dimen.x24);
    //下一个被选中的圆点的index
    private int mIndex;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            ++mIndex;
            if (mIndex == 5) {
                mIndex = 0;
            }
            postInvalidate();
        }
    };

    public IQBLoadingPointView(Context context) {
        this(context, null);
    }

    public IQBLoadingPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IQBLoadingPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context);
    }

    private void initParams(Context context) {
        mWhitePaint = new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setStyle(Paint.Style.FILL);
        mWhitePaint.setColor(ContextCompat.getColor(context, R.color.home_net_loading_one));

        mGreenPaint = new Paint();
        mGreenPaint.setAntiAlias(true);
        mGreenPaint.setStyle(Paint.Style.FILL);
        mGreenPaint.setColor(ContextCompat.getColor(context, R.color.home_net_loading_six));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < 5; i++) {

            canvas.drawCircle(getResources().getDimensionPixelSize(R.dimen.x158) + mRadius * i * 2 + getResources().getDimensionPixelSize(R.dimen.x20) * i, getHeight() / 2, mRadius, mWhitePaint);

        }
        //动态修改绿色圆点的位置
        canvas.drawCircle(getResources().getDimensionPixelSize(R.dimen.x158) + mRadius * mIndex * 2 + getResources().getDimensionPixelSize(R.dimen.x20) * mIndex, getHeight() / 2, mRadius, mGreenPaint);
        //发送消息不断绘制，以达到无限循环的效果
        mHandler.sendEmptyMessageDelayed(MESSAGE_ID, 200);
    }

    //停止动画
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(MESSAGE_ID);
        mHandler = null;
    }
}