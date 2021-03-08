package com.iqb.src.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.iqb.src.R;
import com.iqb.src.tools.CommonUtils;


@SuppressLint("ViewConstructor")
public class IQBPlayerPictureDrawView extends View {
    private Bitmap cacheBitmap;
    public Canvas cacheCanvas;
    public Paint paint;
    private Paint BitmapPaint;
    public Path path;
    private int height;
    private int width;
    public float pX;
    public float pY;

    private int paintColor = Color.RED;
    private static Paint.Style paintStyle = Paint.Style.STROKE;
    private static int paintWidth = 7;
    private Paint mEraserPaint;
    private static boolean isPen = true;
    private int time = 0;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (time == 10) {
                time = 0;
                path.reset();
                invalidate();
                handler.removeCallbacksAndMessages(null);
                return;
            }
            time++;
            path.quadTo(pX, pY, pX, pY);
            invalidate();
            handler.sendEmptyMessageDelayed(0, 200);
        }
    };

    public IQBPlayerPictureDrawView(Context context) {
        super(context);
        initWin(context);
        init();
    }

    private void updatePaint() {
        paint.setColor(paintColor);
        paint.setStyle(paintStyle);
        paint.setStrokeWidth(paintWidth);

    }

    private void initWin(Context context) {
        width = CommonUtils.getScreenWidth(context);
        height = CommonUtils.getScreenHeight(context);
    }


    public IQBPlayerPictureDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IQBPlayerPictureDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

    }


    private float getPositionX(float X) {
        return X * width;
    }

    private float getPositionY(float Y) {
        return Y * height;
    }

    private void init() {
        paintWidth = (int) getContext().getResources().getDimension(R.dimen.x5);
        cacheBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas(cacheBitmap);
        cacheCanvas.setDrawFilter(new PaintFlagsDrawFilter(0,
                Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        path = new Path();
        paint.setAntiAlias(true);
        paint.setDither(true);
        BitmapPaint = new Paint();
        updatePaint();
    }


    public void drawCtlTypeBegin(float x, float y) {
        path.reset();
        invalidate();
        handler.removeCallbacksAndMessages(null);
        path.moveTo(getPositionX(x), getPositionY(y));
        pX = getPositionX(x);
        pY = getPositionY(y);
        invalidate();
    }


    public void drawCtlTypeMove(float x, float y) {
        path.quadTo(pX, pY, getPositionX(x), getPositionY(y));
        pX = getPositionX(x);
        pY = getPositionY(y);
        invalidate();
    }


    public void pptDrawCtlTypeEnd() {
        handler.sendEmptyMessage(0);
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        BitmapPaint = new Paint();
        canvas.drawBitmap(cacheBitmap, 0, 0, BitmapPaint);

        if (isPen) {
            cacheCanvas.drawPath(path, paint);
        } else {
            if (mEraserPaint == null) {
                setStyle(ERASER);
            }
            cacheCanvas.drawPath(path, mEraserPaint);
        }
    }


    public void setColor(int color) {
        paintColor = color;
        updatePaint();
    }

    public int getPaintColor() {
        return paintColor;
    }

    public void setPaintWidth(int width) {
        paintWidth = width;
        updatePaint();
    }

    public static int getPaintWidth() {
        return paintWidth;
    }

    public static final int PEN = 1;
    public static final int ERASER = 2;

    public boolean isIsPen() {
        return isPen;
    }

    public void setStyle(int style) {
        if (path == null) {
            return;
        }
        switch (style) {
            case PEN:
                isPen = true;
                paint.setAntiAlias(true);
                paint.setDither(true);
                updatePaint();
                break;
            case ERASER:
                isPen = false;
                //这个属性是设置paint为橡皮擦重中之重
                //这是重点
                //下面这句代码是橡皮擦设置的重点
                //橡皮擦
                if (mEraserPaint != null) {
                    return;
                }
                mEraserPaint = new Paint();
                mEraserPaint.setAlpha(0);
                //这个属性是设置paint为橡皮擦重中之重
                //这是重点
                //下面这句代码是橡皮擦设置的重点
                mEraserPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                //上面这句代码是橡皮擦设置的重点（重要的事是不是一定要说三遍）
                mEraserPaint.setAntiAlias(true);
                mEraserPaint.setDither(true);
                mEraserPaint.setStyle(Paint.Style.STROKE);
                mEraserPaint.setStrokeJoin(Paint.Join.ROUND);
                mEraserPaint.setStrokeWidth(getContext().getResources().getDimension(R.dimen.x40));

                break;
        }
    }


    public void drawCtlTypeClear() {
        if (cacheCanvas == null) {
            return;
        }
        path.reset();
        invalidate();
        handler.removeCallbacksAndMessages(null);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        cacheCanvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        invalidate();
    }


}