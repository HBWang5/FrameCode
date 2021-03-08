package com.iqb.api.service.view;

import android.content.Intent;

import com.iqb.api.base.baseservice.view.BaseService;
import com.iqb.api.service.presenter.BaseDownLoadFileServicePresenter;


public class BaseDownLoadAPKService extends BaseService<IBaseDownLoadFileService, BaseDownLoadFileServicePresenter> implements IBaseDownLoadFileService {
    @Override
    protected void initData() {
        getPresenter().initDownLoadFile();
    }

    @Override
    protected void initConfig(Intent intent) {
        getPresenter().initConfig(intent);
    }

    @Override
    public BaseDownLoadFileServicePresenter createPresenter() {
        return new BaseDownLoadFileServicePresenter(this);
    }

}
