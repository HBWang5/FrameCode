package com.iqb.player.mvp.mediacontroller.constitute.impl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iqb.player.R;
import com.iqb.player.handler.IQBHandler;
import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.playerstatus.PlayerStatus;
import com.iqb.player.threadpro.impl.IQBThreadMediaPro;
import com.iqb.player.mvp.mediacontroller.constitute.IConstituteView;
import com.iqb.src.widget.IQBPlayerSeekBar;
import com.iqb.src.widget.IQBPlayerImageView;
import com.iqb.src.widget.IQBPlayerLinearLayout;
import com.iqb.src.widget.IQBPlayerTextView;
import com.iqb.src.widget.IQBPlayerVolumeBrightnessBar;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/2/13-17:51
 * 控制层组件整合
 */
public class ConstituteVideoView implements IConstituteView {


    @SuppressLint("StaticFieldLeak")
    private static ConstituteVideoView mConstituteVideoView;
    private ViewGroup mViewGroup;
    private IQBPlayerLinearLayout mIQBPlayerLinearLayout;
    private IQBPlayerVolumeBrightnessBar mBrightnessBar;
    private IQBPlayerVolumeBrightnessBar mVolumeBar;


    @SuppressLint("HandlerLeak")
    private IQBHandler mIQBHandler = new IQBHandler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setVisibiLityAll(false);
        }
    };

    public void setVisibiLity() {
        if (PlayerStatus.PLAYER_CONTROLLER_VIEW_VISIBLE) {
            setVisibiLityAll(false);
        } else {
            setVisibiLityAll(true);
        }
        mIQBHandler.removeMessages(0);
        mIQBHandler.sendEmptyMessageDelayed(0, 5000);
    }

    private void setVisibiLityAll(boolean isVisibiLity) {
        PlayerStatus.PLAYER_CONTROLLER_VIEW_VISIBLE = isVisibiLity;
        mIQBPlayerLinearLayout.setVisibility(isVisibiLity ? View.VISIBLE : View.GONE);
        mVolumeBar.setVisibility(isVisibiLity ? View.VISIBLE : View.GONE);
        mBrightnessBar.setVisibility(isVisibiLity ? View.VISIBLE : View.GONE);

    }

    private ConstituteVideoView() {
    }

    public static synchronized ConstituteVideoView getInstance() {
        if (mConstituteVideoView == null) {
            mConstituteVideoView = new ConstituteVideoView();
        }
        return mConstituteVideoView;
    }

    @Override
    public IConstituteView init(ViewGroup viewGroup) {
        mIQBHandler.bindContext((Activity) viewGroup.getContext());
        this.mViewGroup = viewGroup;
        return this;
    }


    @Override
    public IConstituteView addBelowColumnView() {


        mIQBPlayerLinearLayout = new IQBPlayerLinearLayout(mViewGroup.getContext());
        mIQBPlayerLinearLayout.setWeightSum(11);
        RelativeLayout.LayoutParams hbwangLinearLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        hbwangLinearLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        hbwangLinearLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        hbwangLinearLayoutParams.setMargins(0, 0, 0, 10);
        mViewGroup.addView(mIQBPlayerLinearLayout, hbwangLinearLayoutParams);


        IQBPlayerImageView hbwangImagePauseView = new IQBPlayerImageView(mViewGroup.getContext());
        hbwangImagePauseView.setId(R.id.player_pause);
        mIQBPlayerLinearLayout.addView(hbwangImagePauseView);
        hbwangImagePauseView.setImageResource(R.drawable.live_pause_icon);
        LinearLayout.LayoutParams hbwangImagePauseViewParams = new LinearLayout.LayoutParams(0, 50, 0.5f);
        hbwangImagePauseViewParams.leftMargin = 10;
        hbwangImagePauseViewParams.gravity = Gravity.CENTER;
        hbwangImagePauseView.setLayoutParams(hbwangImagePauseViewParams);

        IQBPlayerSeekBar mIQBPlayerSeekBar = new IQBPlayerSeekBar(mViewGroup.getContext());
        mIQBPlayerLinearLayout.addView(mIQBPlayerSeekBar);
        LinearLayout.LayoutParams hbwangSeekBarParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 9.0f);
        mIQBPlayerSeekBar.setLayoutParams(hbwangSeekBarParams);

        IQBPlayerTextView mIQBPlayerTextView = new IQBPlayerTextView(mViewGroup.getContext());
        mIQBPlayerTextView.setTextSize(5);
        mIQBPlayerLinearLayout.addView(mIQBPlayerTextView);
        mIQBPlayerTextView.setTextColor(Color.WHITE);
        LinearLayout.LayoutParams hbwangTextViewParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        hbwangTextViewParams.gravity = Gravity.CENTER;
        mIQBPlayerTextView.setLayoutParams(hbwangTextViewParams);

        IQBPlayerImageView IQBPlayerImageViewFull = new IQBPlayerImageView(mViewGroup.getContext());
        IQBPlayerImageViewFull.setId(R.id.player_full);
        mIQBPlayerLinearLayout.addView(IQBPlayerImageViewFull);
        IQBPlayerImageViewFull.setImageResource(R.drawable.live_full_icon);
        LinearLayout.LayoutParams hbwangImageViewFullParams = new LinearLayout.LayoutParams(0, 50, 0.5f);
        hbwangImageViewFullParams.rightMargin = 10;
        hbwangImageViewFullParams.gravity = Gravity.CENTER;
        IQBPlayerImageViewFull.setLayoutParams(hbwangImageViewFullParams);

        IQBThreadMediaPro IQBThreadMediaPro = new IQBThreadMediaPro();
        IQBThreadMediaPro.bind(mIQBPlayerSeekBar, mIQBPlayerTextView, (IQBVideoControllerView) mViewGroup);
        ((IQBVideoControllerView) mViewGroup).bindMediaProManager(IQBThreadMediaPro);
        IQBThreadMediaPro.start();

        mIQBHandler.sendEmptyMessageDelayed(0, 5000);

        return this;
    }

    @Override
    public IConstituteView addTopColumnView() {
        return this;
    }

    @Override
    public IConstituteView addLeftColumnView() {

        mVolumeBar = new IQBPlayerVolumeBrightnessBar(mViewGroup.getContext()).setStyle(0);
        RelativeLayout.LayoutParams volumeBrightnessBarParams = new RelativeLayout.LayoutParams(100, 300);
        volumeBrightnessBarParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        volumeBrightnessBarParams.addRule(RelativeLayout.CENTER_VERTICAL);
        volumeBrightnessBarParams.setMargins(10, 10, 50, 50);
        mVolumeBar.setId(R.id.pro_volume);
        mViewGroup.addView(mVolumeBar, volumeBrightnessBarParams);
        return this;
    }

    @Override
    public IConstituteView addRightColumnView() {
        mBrightnessBar = new IQBPlayerVolumeBrightnessBar(mViewGroup.getContext()).setStyle(1);
        RelativeLayout.LayoutParams volumeBrightnessBarParams = new RelativeLayout.LayoutParams(100, 300);
        volumeBrightnessBarParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        volumeBrightnessBarParams.addRule(RelativeLayout.CENTER_VERTICAL);
        volumeBrightnessBarParams.setMargins(50, 10, 10, 50);
        mBrightnessBar.setId(R.id.pro_brightness);
        mViewGroup.addView(mBrightnessBar, volumeBrightnessBarParams);
        return this;
    }

    @Override
    public IConstituteView addLoadingView() {
        return this;
    }

    @Override
    public IConstituteView addBarrageView() {
        return this;
    }

    @Override
    public IConstituteView addAdvertisingView() {
        return this;
    }

    @Override
    public IConstituteView addNavigationView() {
        return this;
    }

    public void showLeft() {
        mBrightnessBar.setVisibility(View.VISIBLE);
        PlayerStatus.PLAYER_CONTROLLER_VIEW_VISIBLE = true;
        mIQBHandler.removeMessages(0);
        mIQBHandler.sendEmptyMessageDelayed(0, 5000);
    }

    public void showRight() {
        mVolumeBar.setVisibility(View.VISIBLE);
        PlayerStatus.PLAYER_CONTROLLER_VIEW_VISIBLE = true;
        mIQBHandler.removeMessages(0);
        mIQBHandler.sendEmptyMessageDelayed(0, 5000);
    }

    public void showBelow() {
        mIQBPlayerLinearLayout.setVisibility(View.VISIBLE);
        PlayerStatus.PLAYER_CONTROLLER_VIEW_VISIBLE = true;
        mIQBHandler.removeMessages(0);
        mIQBHandler.sendEmptyMessageDelayed(0, 5000);
    }
}
