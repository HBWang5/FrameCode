package com.iqb.api.ThreadPoolExecutor.Proxy.ThreadPoolExecutorProxyImpl;

import com.iqb.api.ThreadPoolExecutor.Proxy.IThreadPoolExecutorProxy;
import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorMsgSender.IThreadSender;
import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorProvider.IThreadPoolExecutorProvider;
import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorProvider.ThreadPoolExecutorProviderImpl.FixedThreadPoolExecutorProviderImpl;

/**
 * Created by Administrator on 2018/7/4.
 */

public class FixedThreadPoolExecutorProxyImpl implements IThreadPoolExecutorProxy {
    private IThreadPoolExecutorProvider threadPoolExecutorProvider;

    /**
     *  具体产品
     */
    public FixedThreadPoolExecutorProxyImpl() {
        createThreadPool();
    }

    @Override
    public void createThreadPool() {
        threadPoolExecutorProvider = new FixedThreadPoolExecutorProviderImpl();
    }

    @Override
    public void addThread(IThreadSender iThreadSender) {
        threadPoolExecutorProvider.addThread(iThreadSender);
    }

    @Override
    public void addThread(Runnable iThreadSender) {
        threadPoolExecutorProvider.addThread(iThreadSender);
    }

    @Override
    public void shutdown() {
        threadPoolExecutorProvider.shutdown();
    }

}
