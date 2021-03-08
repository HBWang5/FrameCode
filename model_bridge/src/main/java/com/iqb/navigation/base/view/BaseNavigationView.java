package com.iqb.navigation.base.view;


import android.app.Activity;

import androidx.annotation.CallSuper;
import androidx.annotation.StringRes;

import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.base.ui.BaseView;
import com.iqb.api.dagger.component.ActivityComponent;
import com.iqb.navigation.di.component.DaggerNavigationComponent;
import com.iqb.navigation.di.component.NavigationComponent;
import com.iqb.navigation.di.module.NavigationViewModule;
import com.iqb.navigation.base.presenter.BaseNavigationPresenter;

public interface BaseNavigationView extends BaseView {

    Activity getActivity();

    default void showToast(String content) {
    }

    default void showToast(@StringRes int id) {
        showToast(ApiApplication.getApp().getString(id));
    }

    String getString(int id);

    BaseNavigationPresenter getPresenter();

    default void injectComponent(NavigationComponent component) {
    }

    @CallSuper
    default void injectComponent() {
        NavigationComponent component = DaggerNavigationComponent.builder()
                .activityComponent(getActivityComponent())
                .navigationViewModule(new NavigationViewModule(this))
                .build();

        injectComponent(component);
    }

    ActivityComponent getActivityComponent();

    @CallSuper
    default void onDestroy() {
        BaseNavigationPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.destroy();
        }
    }
}
