package com.iqb.api.base.baseservice.presenter;


import com.iqb.api.base.baseservice.view.IServiceFrame;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public abstract class PresenterServiceCenter<V extends IServiceFrame> implements IPresenter<V> {
    private WeakReference<V> view;

    /**
     * 方便在后退时取消当前页面请求故增加此tag集合
     * 因实用性太差标记为已过时
     */
    @Deprecated
    protected List<String> requestTags;

    public PresenterServiceCenter() {
        requestTags = new ArrayList<>();
    }

    /**
     * 绑定View
     *
     */
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

    /**
     * 用于检查View是否为空对象
     */
    public boolean isAttachView() {
        return this.view != null && this.view.get() != null;
    }

    public V getView() {
        if (this.view != null) {
            return this.view.get();
        }
        return null;
    }

    public Object[] getRequestTags() {
        return requestTags.toArray();
    }
}
