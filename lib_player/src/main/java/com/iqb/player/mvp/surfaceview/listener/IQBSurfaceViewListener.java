package com.iqb.player.mvp.surfaceview.listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.playerstatus.PlayerStatus;

/**
 * ----------Dragon be here!----------/
 * Created by IQB on 2019/2/25-17:18
 */
public class IQBSurfaceViewListener implements View.OnTouchListener {
    private GestureDetector mGestureDetector;
    private IQBVideoControllerView mIQBMediaController;
    public IQBSurfaceViewListener(GestureDetector mGestureDetector) {
        this.mGestureDetector = mGestureDetector;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (PlayerStatus.PLAYER_GESTURE_STATUS == 0) {
                    mIQBMediaController.showUI();
                }
                PlayerStatus.PLAYER_GESTURE_STATUS = 0;
                break;
        }
        return mGestureDetector.onTouchEvent(event);
    }


    public void bindMediaController(IQBVideoControllerView mIQBMediaController) {
        this.mIQBMediaController = mIQBMediaController;
    }
}
