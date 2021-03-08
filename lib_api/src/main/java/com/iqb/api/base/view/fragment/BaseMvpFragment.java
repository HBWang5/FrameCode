package com.iqb.api.base.view.fragment;

import android.content.Context;

import androidx.annotation.NonNull;

import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.base.view.activity.BaseActivity;
import com.iqb.api.dagger.component.ActivityComponent;
import com.iqb.api.dagger.component.DaggerActivityComponent;
import com.iqb.api.dagger.module.ActivityModule;

public abstract class BaseMvpFragment extends BaseFragment {
    private ActivityComponent mActivityComponent;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    protected void initBeforeViewCreated() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(ApiApplication.getApp().getAppComponent())
                .activityModule(new ActivityModule(mActivity))
                .build();
        injectComponent();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    protected abstract void injectComponent();
}
