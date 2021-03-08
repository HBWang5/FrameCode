package com.iqb.api.ThreadPoolExecutor.Factory.ThreadPoolExecutorFactoryImpl;

import com.iqb.api.ThreadPoolExecutor.Factory.IThreadPoolExecutorFactory;
import com.iqb.api.ThreadPoolExecutor.Proxy.IThreadPoolExecutorProxy;
import com.iqb.api.ThreadPoolExecutor.Proxy.ThreadPoolExecutorProxyImpl.CachedThreadPoolExecutorProxyImpl;
import com.iqb.api.ThreadPoolExecutor.Proxy.ThreadPoolExecutorProxyImpl.FixedThreadPoolExecutorProxyImpl;
import com.iqb.api.ThreadPoolExecutor.Proxy.ThreadPoolExecutorProxyImpl.ScheduledThreadPoolExecutorProxyImpl;
import com.iqb.api.ThreadPoolExecutor.Proxy.ThreadPoolExecutorProxyImpl.SingleThreadExecutorProxyImpl;
import com.iqb.constants.Config;

/**
 * Created by Administrator on 2018/7/4.
 */

public class ThreadPoolExecutorFactoryImpl implements IThreadPoolExecutorFactory {
    /**
     * 具体工厂
     */
    @Override
    public IThreadPoolExecutorProxy produce(int type) {
        switch (type) {
            case Config.SINGLE_THREAD_EXECUTOR:
                return new SingleThreadExecutorProxyImpl();
            case Config.SCHEDULED_THREAD_EXECUTOR:
                return new ScheduledThreadPoolExecutorProxyImpl();
            case Config.SFIXED_THREAD_EXECUTOR:
                return new FixedThreadPoolExecutorProxyImpl();
            case Config.CACHED_THREAD_EXECUTOR:
                return new CachedThreadPoolExecutorProxyImpl();
            default:
                try {
                    throw new Exception("未找到该线程池");
                } catch (Exception e) {
                    return null;
                }
        }

    }
}
