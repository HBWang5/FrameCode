package com.iqb.player.mvp.mediacontroller.constitute.impl;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import com.iqb.player.mvp.mediacontroller.view.IQBVideoControllerView;
import com.iqb.player.threadpro.impl.IQBThreadMediaPro;
import com.iqb.player.mvp.mediacontroller.constitute.IConstituteView;
import com.iqb.src.widget.IQBPlayerSeekBar;
import com.iqb.src.widget.IQBPlayerImageView;
import com.iqb.src.widget.IQBPlayerTextView;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/3/19-16:31
 */
public class ConstituteMusicView implements IConstituteView {

    @SuppressLint("StaticFieldLeak")
    private static ConstituteMusicView mConstituteMusicView;
    private ViewGroup viewGroup;


    public static synchronized ConstituteMusicView getInstance() {
        if (mConstituteMusicView == null) {
            mConstituteMusicView = new ConstituteMusicView();
        }
        return mConstituteMusicView;
    }

    @Override
    public IConstituteView addBelowColumnView() {
        IQBPlayerSeekBar mIQBPlayerSeekBar = new IQBPlayerSeekBar(viewGroup.getContext());
        viewGroup.addView(mIQBPlayerSeekBar);
        IQBPlayerTextView mIQBPlayerTextView = new IQBPlayerTextView(viewGroup.getContext());
        viewGroup.addView(mIQBPlayerTextView);

        IQBThreadMediaPro mIQBThreadMediaPro = new IQBThreadMediaPro();
        mIQBThreadMediaPro.bind(mIQBPlayerSeekBar, mIQBPlayerTextView, (IQBVideoControllerView) viewGroup);
        ((IQBVideoControllerView) viewGroup).bindMediaProManager(mIQBThreadMediaPro);
        mIQBThreadMediaPro.start();

        return this;
    }

    @Override
    public IConstituteView addTopColumnView() {
        IQBPlayerTextView mIQBPlayerTextView = new IQBPlayerTextView(viewGroup.getContext());
        viewGroup.addView(mIQBPlayerTextView);
        return this;
    }

    @Override
    public IConstituteView addLeftColumnView() {
        IQBPlayerImageView mIQBPlayerImageView = new IQBPlayerImageView(viewGroup.getContext());
        viewGroup.addView(mIQBPlayerImageView);
        return this;
    }

    @Override
    public IConstituteView addRightColumnView() {
        IQBPlayerImageView mIQBPlayerImageView = new IQBPlayerImageView(viewGroup.getContext());
        viewGroup.addView(mIQBPlayerImageView);
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

    @Override
    public IConstituteView init(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        return this;
    }

}
