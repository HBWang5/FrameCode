package com.iqb.navigation.base.view;


import androidx.annotation.CallSuper;

import com.iqb.api.base.view.fragment.BaseIQBFragment;

public abstract class BaseNavigationIQBFragment extends BaseIQBFragment implements BaseNavigationView {
    @CallSuper
    @Override
    protected void initBeforeViewCreated() {
        super.initBeforeViewCreated();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        BaseNavigationView.super.onDestroy();
        super.onDestroy();
    }

    @Override
    public void injectComponent() {
        BaseNavigationView.super.injectComponent();
    }
}
