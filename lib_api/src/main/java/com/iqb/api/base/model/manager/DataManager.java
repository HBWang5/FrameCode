package com.iqb.api.base.model.manager;

import android.content.Context;

import com.iqb.api.ThreadPoolExecutor.Proxy.IThreadPoolExecutorProxy;
import com.iqb.api.dagger.helper.DataBaseHelper;
import com.iqb.api.dagger.helper.HttpHelper;
import com.iqb.api.dagger.helper.SharePreferenceHelper;
import com.iqb.api.dagger.helper.ThreadPoolHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * 通用数据管理类
 */
@Singleton
public class DataManager {
    private HttpHelper mHttpHelper;

    private SharePreferenceHelper mSharePreferenceHelper;

    private DataBaseHelper mDataBaseHelper;

    private ThreadPoolHelper threadPoolHelper;

    private Context mContext;

    @Inject
    public DataManager(ThreadPoolHelper threadPoolHelper, HttpHelper mHttpHelper, SharePreferenceHelper mSharePreferenceHelper, DataBaseHelper mDataBaseHelper, Context mContext) {
        this.threadPoolHelper = threadPoolHelper;
        this.mHttpHelper = mHttpHelper;
        this.mSharePreferenceHelper = mSharePreferenceHelper;
        this.mDataBaseHelper = mDataBaseHelper;
        this.mContext = mContext;
    }

    public <T> T getService(Class<T> serviceClass) {
        return mHttpHelper.createApi(serviceClass);
    }

    public SharePreferenceHelper getSharePreferenceHelper() {
        return mSharePreferenceHelper;
    }

    public IThreadPoolExecutorProxy getProduce() {
        return threadPoolHelper.getProduce();
    }

    public Context getContext() {
        return mContext;
    }
}
