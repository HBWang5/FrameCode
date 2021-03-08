package com.iqb.api.base.baseservice.presenter;


import com.iqb.api.base.baseservice.view.IServiceFrame;

/**
 * Created by Administrator on 2018/1/3.
 */

public interface IPresenter<V extends IServiceFrame> {
    //绑定视图
     void attachView(V view);
    //接触绑定
     void detachView();
}
