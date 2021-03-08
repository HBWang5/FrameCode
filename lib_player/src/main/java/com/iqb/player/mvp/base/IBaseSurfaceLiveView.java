package com.iqb.player.mvp.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.iqb.player.mvp.surfaceview.listener.IIQBVideoGestureListener;
import com.iqb.player.mvp.surfaceview.ISurfaceViewCallBack;

public abstract class IBaseSurfaceLiveView<P extends IBaseLivePresenter> extends SurfaceView implements ISurfaceViewCallBack, IIQBVideoGestureListener, BaseLiveView {
    P basePresenter;

    @SuppressWarnings("unchecked")
    public IBaseSurfaceLiveView(@NonNull Context context) {
        super(context);
        this.basePresenter = bindPresenter();
        if (basePresenter != null) {
            basePresenter.attachView(this);
        }
    }


    protected abstract P bindPresenter();

    public P getPresenter() {
        return basePresenter;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        basePresenter.detachView();
        basePresenter = null;
    }
}

