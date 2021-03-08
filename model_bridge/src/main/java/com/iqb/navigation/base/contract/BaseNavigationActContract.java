package com.iqb.navigation.base.contract;

import com.iqb.navigation.base.presenter.BaseNavigationPresenter;
import com.iqb.navigation.base.view.BaseNavigationIQBActivity;

public interface BaseNavigationActContract {
    abstract class View extends BaseNavigationIQBActivity {
    }

    abstract class Presenter<T extends View> extends BaseNavigationPresenter<T> {

        protected Presenter(T view) {
            super(view);
        }
    }
}
