package com.iqb.api.base.baseservice.view;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.iqb.api.base.baseservice.presenter.PresenterServiceCenter;


public abstract class BaseService<V extends IBaseService, P extends PresenterServiceCenter> extends FrameService<P, V> {
    @Nullable
    @Override
    public final IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public final void onCreate() {
        super.onCreate();
    }

    @Override
    public final int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            initConfig(intent);
            initData();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public final void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

    }

    protected abstract void initData();


    protected abstract void initConfig(Intent intent);
}
