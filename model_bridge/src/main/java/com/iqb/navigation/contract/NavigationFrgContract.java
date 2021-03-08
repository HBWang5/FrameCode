package com.iqb.navigation.contract;

import com.iqb.navigation.base.contract.BaseNavigationFrgContract;

public interface NavigationFrgContract extends BaseNavigationFrgContract {
    abstract class View extends BaseNavigationFrgContract.View {
    }

    abstract class Presenter extends BaseNavigationFrgContract.Presenter<View> {
        protected Presenter(View view) {
            super(view);
        }

        public abstract void init();
    }
}
