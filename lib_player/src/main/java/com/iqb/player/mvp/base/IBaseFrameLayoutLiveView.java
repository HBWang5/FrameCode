package com.iqb.player.mvp.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

public abstract class IBaseFrameLayoutLiveView<P extends IBaseLivePresenter> extends FrameLayout implements BaseLiveView {
    P basePresenter;

    @SuppressWarnings("unchecked")
    public IBaseFrameLayoutLiveView(@NonNull Context context) {
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

