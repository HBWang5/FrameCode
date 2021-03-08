package com.iqb.api.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.request.target.ViewTarget;
import com.iqb.api.R;
import com.iqb.api.dagger.component.AppComponent;
import com.iqb.api.dagger.component.DaggerAppComponent;
import com.iqb.api.dagger.module.AppModule;
import com.iqb.api.utils.ActivityHelper;


import me.jessyan.autosize.AutoSizeConfig;

public class ApiApplication extends Application {

    private AppComponent mAppComponent;
    private static ApiApplication mApplication;

    public static Context getApplication() {
        return mApplication;
    }

    public static ApiApplication getApp() {
        return mApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        initRouter();
        ViewTarget.setTagId(R.id.tag_glide);
        AutoSizeConfig.getInstance().setCustomFragment(true);
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        CrashHandler.getInstance(this);
    }

    private void initRouter() {
        ARouter.init(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            ActivityHelper.getInstance().addActivity(activity);
    }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
        }
    };

}
