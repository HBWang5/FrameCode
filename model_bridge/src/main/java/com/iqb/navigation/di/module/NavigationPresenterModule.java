package com.iqb.navigation.di.module;

import com.iqb.navigation.contract.NavigationActContract;
import com.iqb.navigation.contract.NavigationFrgContract;
import com.iqb.navigation.presenter.NavigationPresenterAct;
import com.iqb.navigation.presenter.NavigationPresenterFrg;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationPresenterModule {

    @Binds
    abstract NavigationActContract.Presenter navigationPresenterAct(NavigationPresenterAct presenter);

    @Binds
    abstract NavigationFrgContract.Presenter navigationPresenterFrg(NavigationPresenterFrg presenter);

}
