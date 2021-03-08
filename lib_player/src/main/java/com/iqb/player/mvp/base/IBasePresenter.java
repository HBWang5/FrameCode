package com.iqb.player.mvp.base;

public interface IBasePresenter<V extends BaseLiveView> {
    //绑定视图
    void attachView(V view);
    //接触绑定
    void detachView();
}
