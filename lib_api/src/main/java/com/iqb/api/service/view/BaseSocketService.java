package com.iqb.api.service.view;

import android.content.Intent;

import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.base.baseservice.view.BaseService;
import com.iqb.api.service.presenter.BaseSocketServicePresenter;

public class BaseSocketService extends BaseService<IBaseSocketServiceUI, BaseSocketServicePresenter> implements IBaseSocketServiceUI {
    @Override
    protected void initData() {
        getPresenter().connect();

    }

    @Override
    protected void initConfig(Intent intent) {
        String connectQuery = "clientId" + "=" + ApiApplication.getApp().getAppComponent().getDataManager().getSharePreferenceHelper().getUserID()
                + "&" + "auth" + "=" + ApiApplication.getApp().getAppComponent().getDataManager().getSharePreferenceHelper().getAccessToken()
                + "&" + "type" + "=" + 2;
        getPresenter().initConfig(connectQuery);
    }

    @Override
    public BaseSocketServicePresenter createPresenter() {
        return new BaseSocketServicePresenter(getServiceContext());
    }
}
