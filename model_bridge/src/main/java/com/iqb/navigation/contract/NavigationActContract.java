package com.iqb.navigation.contract;

import com.iqb.navigation.base.contract.BaseNavigationActContract;

import java.util.List;

public interface NavigationActContract extends BaseNavigationActContract {
    abstract class View extends BaseNavigationActContract.View {
    }

    abstract class Presenter extends BaseNavigationActContract.Presenter<View> {

        public abstract void permissionDetection();

        protected Presenter(View view) {
            super(view);
        }

        public abstract void showPermissionsDialog(List<String> perms);

        public abstract void startLoginActivity();
    }
}
