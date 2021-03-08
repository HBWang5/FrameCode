package com.iqb.api.dagger.component;

import android.content.Context;

import com.iqb.api.base.model.manager.DataManager;
import com.iqb.api.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getContext();
    DataManager getDataManager();
}
