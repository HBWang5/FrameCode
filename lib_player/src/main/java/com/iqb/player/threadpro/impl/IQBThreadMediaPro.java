package com.iqb.player.threadpro.impl;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Message;

import com.iqb.player.handler.IQBHandler;
import com.iqb.player.mvp.mediacontroller.listener.IQBMediaProOnTouchListener;
import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.playerstatus.PlayerStatus;
import com.iqb.player.threadpro.IMediaProCallBack;
import com.iqb.src.widget.IQBPlayerSeekBar;
import com.iqb.src.widget.IQBPlayerTextView;

import java.text.SimpleDateFormat;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/2/22-14:22
 */
public class IQBThreadMediaPro extends Thread implements IMediaProCallBack {
    private IQBPlayerSeekBar IQBPlayerSeekBar;
    private IQBVideoControllerView IIQBMediaController;
    private IQBPlayerTextView IQBPlayerTextView;
    private final SimpleDateFormat formatter;
    private final SimpleDateFormat formatter_min;

    @SuppressLint("HandlerLeak")
    private IQBHandler handler = new IQBHandler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            IQBPlayerTextView.setText((IQBPlayerSeekBar.getMax() > 216000 ? formatter.format(IQBPlayerSeekBar.getMax()) : formatter_min.format(IQBPlayerSeekBar.getMax()))
                    + "/" + (IQBPlayerSeekBar.getMax() > 216000 ? formatter.format(msg.what) : formatter_min.format(msg.what))
            );
        }
    };

    @SuppressLint("SimpleDateFormat")
    public IQBThreadMediaPro() {
        formatter = new SimpleDateFormat("HH:mm:ss");
        formatter_min = new SimpleDateFormat("mm:ss");
    }

    public void bind(IQBPlayerSeekBar IQBPlayerSeekBar, IQBPlayerTextView IQBPlayerTextView, IQBVideoControllerView IIQBMediaController) {
        this.IQBPlayerSeekBar = IQBPlayerSeekBar;
        this.IIQBMediaController = IIQBMediaController;
        this.IQBPlayerTextView = IQBPlayerTextView;
        handler.bindContext((Activity) IQBPlayerSeekBar.getContext());
    }

    @Override
    public void run() {
        super.run();
        while (IQBPlayerSeekBar.getProgress() <= IQBPlayerSeekBar.getMax()) {
            if (PlayerStatus.PLAYER_PRO_STATUS) {
                //获取音乐/视频当前播放的位置
                int position = IIQBMediaController.getCurrentPosition();
                IIQBMediaController.getSurfaceView().setSyncProgress(position);
                //设置进度
                IQBPlayerSeekBar.setProgress(position);
                if (position % 60 == 0) {
                    handler.sendEmptyMessage(position);
                }
            }
        }
    }

    /**
     * SeekBar控制初始化
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void setInitProConfig(int duration) {
        IQBPlayerSeekBar.setMax(duration);                                                               //设置进度条最大值
        IQBPlayerSeekBar.setOnTouchListener(new IQBMediaProOnTouchListener().bindMediaController(IIQBMediaController));//设置监听
    }

    @Override
    public void setBufferPercentage(int percent) {
        IQBPlayerSeekBar.setSecondaryProgress(IQBPlayerSeekBar.getMax() * percent / 100);
    }
}
