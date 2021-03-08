package com.iqb.api.ThreadPoolExecutor.Proxy.ThreadPoolExecutorProxyImpl;

import com.iqb.api.ThreadPoolExecutor.Proxy.IThreadPoolExecutorProxy;
import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorMsgSender.IThreadSender;
import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorProvider.IThreadPoolExecutorProvider;
import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorProvider.ThreadPoolExecutorProviderImpl.ScheduledThreadPoolExecutorProviderImpl;

/**
 * Created by Administrator on 2018/7/4.
 */

public class ScheduledThreadPoolExecutorProxyImpl implements IThreadPoolExecutorProxy {
    private IThreadPoolExecutorProvider threadPoolExecutorProvider;

    /**
     *  具体产品
     */
    public ScheduledThreadPoolExecutorProxyImpl() {
        createThreadPool();
    }

    @Override
    public void createThreadPool() {
        threadPoolExecutorProvider = new ScheduledThreadPoolExecutorProviderImpl();
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
    /**
     * 延迟后启动，每/秒执行一次
     */
    public void scheduleAtFixedRate(IThreadSender iThreadSender,int initialDelay,int delay){
        ((ScheduledThreadPoolExecutorProviderImpl)threadPoolExecutorProvider).scheduleAtFixedRate(iThreadSender,initialDelay,delay);
    }

    /**
     * 启动后第一次延迟/秒执行，后面延迟/秒执行
     */
    public void scheduleWithFixedDelay(IThreadSender iThreadSender,int initialDelay,int delay){
        ((ScheduledThreadPoolExecutorProviderImpl)threadPoolExecutorProvider).scheduleWithFixedDelay(iThreadSender,initialDelay,delay);
    }
}
