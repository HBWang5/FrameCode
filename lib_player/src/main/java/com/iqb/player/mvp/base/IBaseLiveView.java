package com.iqb.player.mvp.base;

import android.annotation.SuppressLint;

public abstract class IBaseLiveView<P extends IBaseLivePresenter> implements BaseLiveView {
    private P basePresenter;

    @SuppressWarnings("unchecked")
    IBaseLiveView() {
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
    protected void onDetachedFromWindow() {
        basePresenter.detachView();
        basePresenter = null;
    }
}
