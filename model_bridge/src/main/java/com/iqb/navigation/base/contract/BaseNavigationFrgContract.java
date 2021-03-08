package com.iqb.navigation.base.contract;

import com.iqb.navigation.base.presenter.BaseNavigationPresenter;
import com.iqb.navigation.base.view.BaseNavigationIQBFragment;

public interface BaseNavigationFrgContract {
    abstract class View extends BaseNavigationIQBFragment {
    }

    abstract class Presenter<T extends BaseNavigationFrgContract.View> extends BaseNavigationPresenter<T> {

        protected Presenter(T view) {
            super(view);
        }
    }
}
