package com.iqb.navigation.di.module;

import com.iqb.navigation.contract.NavigationActContract;
import com.iqb.navigation.base.view.BaseNavigationView;
import com.iqb.navigation.contract.NavigationFrgContract;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationViewModule {
    private BaseNavigationView baseNavigationUI;

    public NavigationViewModule(BaseNavigationView baseNavigationUI) {
        this.baseNavigationUI = baseNavigationUI;
    }

    @Provides
    NavigationActContract.View providerNavigationActView() {
        return (NavigationActContract.View) baseNavigationUI;
    }

    @Provides
    NavigationFrgContract.View providerNavigationFrgView() {
        return (NavigationFrgContract.View) baseNavigationUI;
    }
}
