package com.iqb.api.dagger.module;


import androidx.lifecycle.LifecycleOwner;

import com.iqb.api.base.view.activity.BaseActivity;
import com.iqb.api.base.view.fragment.BaseFragment;
import com.iqb.api.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * 拥有Activity生命周期的单例从这里提供
 */
@Module
public class ActivityModule {
    private BaseActivity baseActivity;

    public ActivityModule(BaseActivity activity) {
        baseActivity = activity;
    }

    @ActivityScope
    @Provides
    LifecycleOwner provideLifecycleProvider() {
        return baseActivity;
    }

    @ActivityScope
    @Provides
    BaseActivity provideActivity() {
        return baseActivity;
    }
}
