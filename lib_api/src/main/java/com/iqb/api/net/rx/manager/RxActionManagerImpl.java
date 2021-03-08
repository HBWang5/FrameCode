package com.iqb.api.net.rx.manager;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.ArrayMap;

import io.reactivex.disposables.Disposable;


/**
 * Describe：
 * - RxJavaAction管理实现类
 */
public class RxActionManagerImpl implements RxActionManager<Object> {

    /**
     * 处理,请求列表
     */
    private ArrayMap<Object, Disposable> mMaps;

    public static RxActionManagerImpl getInstance() {
        return SingleModeHolder.INSTANCE;
    }

    private static class SingleModeHolder {
        private static final RxActionManagerImpl INSTANCE = new RxActionManagerImpl();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private RxActionManagerImpl() {
        mMaps = new ArrayMap<>();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void add(Object tag, Disposable disposable) {
        mMaps.put(tag, disposable);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void remove(Object... tags) {
        for (Object tag : tags) {
            if (!mMaps.isEmpty()) {
                mMaps.remove(tag);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void cancel(Object... tags) {
        if (mMaps.isEmpty()) {
            return;
        }
        for (Object tag : tags) {
            Disposable disposable = mMaps.get(tag);
            if (disposable == null) {
                return;
            }
            if (!disposable.isDisposed()) {
                disposable.dispose();
            } else {
                //请求已取消
            }
            mMaps.remove(tag);
        }
    }

    /**
     * 判断是否取消了请求
     *
     * @param tag 请求标签
     * @return 请求是否取消
     */
    public boolean isDisposed(Object tag) {
        if (mMaps.isEmpty()) {
            return true;
        }
        Disposable disposable = mMaps.get(tag);
        if (disposable == null) {
            return true;
        } else {
            return disposable.isDisposed();
        }
    }
}
