package com.iqb.navigation.presenter;


import androidx.lifecycle.LifecycleOwner;

import com.iqb.api.base.app.ApiApplication;
import com.iqb.navigation.contract.NavigationFrgContract;
import com.iqb.navigation.model.NavigationModelFrg;

import javax.inject.Inject;

public class NavigationPresenterFrg extends NavigationFrgContract.Presenter {

    private NavigationModelFrg navigationModelAct;

    /**
     *注入控制器
     */
    @Inject
    NavigationPresenterFrg(NavigationFrgContract.View view, LifecycleOwner lifecycleOwner) {
        super(view);
    }

    /**
     *绑定Model
     */
    @Override
    protected NavigationModelFrg bindModel() {
        navigationModelAct = new NavigationModelFrg(ApiApplication.getApp().getAppComponent().getDataManager());
        init();
        return navigationModelAct;
    }

    /**
     *初始化操作
     */
    @Override
    public void init() {
        navigationModelAct.init();
    }

}
