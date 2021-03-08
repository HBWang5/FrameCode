package com.iqb.api.base.baseservice.view;

import android.app.Service;

import com.iqb.api.base.baseservice.presenter.PresenterServiceCenter;


/**
 * Created by Administrator on 2018/1/3.
 */

public abstract class FrameService<P extends PresenterServiceCenter, V extends IBaseService> extends Service {

    private P presenter;


    @Override
    public void onCreate() {
        super.onCreate();
        if (this.presenter == null) {
            this.presenter = createPresenter();//  创建控制器
        }
        if (this.presenter != null) {
            this.presenter.attachView((V) this);    //绑定
        }
    }

    public P getPresenter() {
        if (this.presenter == null) {
            this.presenter = createPresenter();
        }
        return presenter;
    }


    public abstract P createPresenter();


    public FrameService getServiceContext() {
        return this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.detachView();   //解除绑定
            this.presenter = null;
        }
    }
}
