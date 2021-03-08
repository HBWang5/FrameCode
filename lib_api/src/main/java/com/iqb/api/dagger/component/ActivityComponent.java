package com.iqb.api.dagger.component;

import android.content.Context;


import androidx.lifecycle.LifecycleOwner;

import com.iqb.api.base.model.manager.DataManager;
import com.iqb.api.base.view.activity.BaseActivity;
import com.iqb.api.dagger.module.ActivityModule;
import com.iqb.api.dagger.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {

    BaseActivity actContext();

    Context appContext();

    LifecycleOwner lifecycleProvider();

    DataManager dataManager();

}
