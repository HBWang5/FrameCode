package com.iqb.api.base.model.manager;

import android.content.Context;

import com.iqb.api.ThreadPoolExecutor.Proxy.IThreadPoolExecutorProxy;
import com.iqb.api.dagger.helper.SharePreferenceHelper;

/**
 * DataManager的包装基类, 供各module继承
 */
public abstract class BaseDataManager {
    private DataManager mDataManager;

    public BaseDataManager(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    protected SharePreferenceHelper getSharePreferenceHelper() {
        return mDataManager.getSharePreferenceHelper();
    }

    public <S> S getService(Class<S> serviceClass) {
        return mDataManager.getService(serviceClass);
    }

    public Context getContext() {
        return mDataManager.getContext();
    }

    public IThreadPoolExecutorProxy getThreadPoolManager() {
        return mDataManager.getProduce();
    }
}
