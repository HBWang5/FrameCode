package com.iqb.navigation.model;


import androidx.lifecycle.LifecycleOwner;

import com.iqb.api.base.model.manager.DataManager;
import com.iqb.api.net.rx.observer.HttpRxObservable;
import com.iqb.api.net.rx.observer.HttpRxObserver;
import com.iqb.been.base.HttpResponseBean;
import com.iqb.been.login.LoginEntity;
import com.iqb.constants.VariableConfig;
import com.iqb.navigation.base.model.BaseNavigationModel;



public class NavigationModelAct extends BaseNavigationModel {

    public NavigationModelAct(DataManager mDataManager) {
        super(mDataManager);
        VariableConfig.LIVE_STATE = getSharePreferenceHelper().getSoundModel() == 1;
    }


    /**
     * 账号密码登录
     */
    public void login(LifecycleOwner lifecycleOwner, HttpRxObserver<HttpResponseBean<LoginEntity>> observer) {
    }

    /**
     * 自动登陆成功保存用户密码
     */
    public void saveUserData(HttpResponseBean<LoginEntity> response) {
    }
}
