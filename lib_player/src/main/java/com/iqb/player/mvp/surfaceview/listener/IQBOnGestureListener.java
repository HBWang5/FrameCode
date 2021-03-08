package com.iqb.player.mvp.surfaceview.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.iqb.player.mvp.surfaceview.view.IQBMediaSurfaceView;
import com.iqb.player.playerstatus.PlayerStatus;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/2/25-15:51
 */
public class IQBOnGestureListener extends GestureDetector.SimpleOnGestureListener {
    private IIQBVideoGestureListener mVideoGestureListener;
    private IQBMediaSurfaceView IQBSurfaceView;
    private int offsetX = 1;

    public IQBOnGestureListener(IQBMediaSurfaceView IQBSurfaceView) {
        this.IQBSurfaceView = IQBSurfaceView;
        mVideoGestureListener = IQBSurfaceView;
    }


    @Override
    public boolean onDown(MotionEvent e) {
        //每次按下都重置为NONE
        mVideoGestureListener.resetConfig();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        switch (PlayerStatus.PLAYER_GESTURE_STATUS) {
            case 0:
                //offset是让快进快退不要那么敏感的值
                if (Math.abs(distanceX) - Math.abs(distanceY) > offsetX) {
                    PlayerStatus.PLAYER_GESTURE_STATUS = 1;
                } else {
                    if (e1.getX() < IQBSurfaceView.getWidth() / 2) {
                        PlayerStatus.PLAYER_GESTURE_STATUS = 2;
                    } else {
                        PlayerStatus.PLAYER_GESTURE_STATUS = 3;
                    }
                }
                break;

            case 1:
                if (mVideoGestureListener != null) {
                    mVideoGestureListener.onFF_REWGesture(e1, e2, distanceX, distanceY);
                }
                break;
            case 2:
                if (mVideoGestureListener != null) {
                    mVideoGestureListener.onBrightnessGesture(e1, e2, distanceX, distanceY);
                }
                break;
            case 3:
                if (mVideoGestureListener != null) {
                    mVideoGestureListener.onVolumeGesture(e1, e2, distanceX, distanceY);
                }
                break;
        }
        return true;
    }

}
