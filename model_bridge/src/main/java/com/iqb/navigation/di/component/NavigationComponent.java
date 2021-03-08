package com.iqb.navigation.di.component;

import com.iqb.api.base.model.manager.DataManager;
import com.iqb.api.dagger.component.ActivityComponent;
import com.iqb.api.dagger.scope.BusinessScope;
import com.iqb.navigation.di.module.NavigationPresenterModule;
import com.iqb.navigation.di.module.NavigationViewModule;
import com.iqb.navigation.view.activity.NavigationActivity;
import com.iqb.navigation.view.fragment.NavigationFragment;

import dagger.Component;

@BusinessScope
@Component(
        dependencies = ActivityComponent.class,
        modules = {NavigationViewModule.class, NavigationPresenterModule.class}
)
public interface NavigationComponent {

    DataManager dataManager();

    void inject(NavigationActivity view);

    void inject(NavigationFragment view);
}

