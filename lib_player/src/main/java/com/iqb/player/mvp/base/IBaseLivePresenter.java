package com.iqb.player.mvp.base;

import java.lang.ref.WeakReference;

public abstract class IBaseLivePresenter<V extends BaseLiveView> implements IBasePresenter<V>{
    private WeakReference<V> view;
    private IBaseModel model;


    IBaseLivePresenter() {
        model = bindModel();
    }

    public V getView() {
        if (this.view != null) {
            return this.view.get();
        }
        return null;
    }

    public IBaseModel getModel() {
        return model;
    }

    protected abstract IBaseModel bindModel();

    @Override
    public void attachView(V view) {
        this.view = new WeakReference<>(view);
    }

    /**
     * 解除绑定
     */
    @Override
    public void detachView() {
        this.view = null;
    }


}
