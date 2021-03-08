package com.iqb.api.dagger.helper;

import com.iqb.api.ThreadPoolExecutor.Factory.ThreadPoolExecutorFactoryImpl.ThreadPoolExecutorFactoryImpl;
import com.iqb.api.ThreadPoolExecutor.Proxy.IThreadPoolExecutorProxy;
import com.iqb.constants.Config;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ThreadPoolHelper {
    private IThreadPoolExecutorProxy produce;
    private ThreadPoolExecutorFactoryImpl threadPoolExecutorFactory;

    @Inject
    ThreadPoolHelper() {
    }


    public IThreadPoolExecutorProxy getProduce() {
        if (produce == null) {
            produce = getThreadPoolExecutorFactory().produce(Config.SCHEDULED_THREAD_EXECUTOR);
        }
        return produce;
    }

    private ThreadPoolExecutorFactoryImpl getThreadPoolExecutorFactory() {
        if (threadPoolExecutorFactory == null) {
            threadPoolExecutorFactory = new ThreadPoolExecutorFactoryImpl();
        }
        return threadPoolExecutorFactory;
    }
}
