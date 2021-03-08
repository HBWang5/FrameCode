package com.iqb.player.mvp.mediacontroller.listener;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;


/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/2/25-10:34
 */
public class IQBMediaProOnTouchListener implements View.OnTouchListener {
    private IQBVideoControllerView mIIQBMediaController;


    public IQBMediaProOnTouchListener bindMediaController(IQBVideoControllerView IIQBMediaController) {
        mIIQBMediaController = IIQBMediaController;
        return this;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        SeekBar seekBar = (SeekBar) v;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mIIQBMediaController.pause();
                break;
            case MotionEvent.ACTION_MOVE:
                mIIQBMediaController.pause();
                break;
            case MotionEvent.ACTION_UP:
                mIIQBMediaController.seekTo(seekBar.getProgress());
                mIIQBMediaController.resume();
                break;
        }
        return false;
    }
}
