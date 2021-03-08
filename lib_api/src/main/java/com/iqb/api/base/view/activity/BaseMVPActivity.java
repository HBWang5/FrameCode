package com.iqb.api.base.view.activity;

import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.dagger.component.ActivityComponent;
import com.iqb.api.dagger.component.DaggerActivityComponent;
import com.iqb.api.dagger.module.ActivityModule;

/**
 * 中间层activity可用基础拓展
 */
public abstract class BaseMVPActivity extends BaseActivity  {
    ActivityComponent mActivityComponent;

    @Override
    protected void initBeforeViewCreated() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(ApiApplication.getApp().getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
        injectComponent();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    protected abstract void injectComponent();
}
