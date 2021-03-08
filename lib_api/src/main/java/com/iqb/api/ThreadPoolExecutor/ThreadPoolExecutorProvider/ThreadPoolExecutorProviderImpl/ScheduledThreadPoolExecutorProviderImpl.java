package com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorProvider.ThreadPoolExecutorProviderImpl;

import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorMsgSender.IThreadSender;
import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorProvider.IThreadPoolExecutorProvider;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by Administrator on 2018/7/4.
 */

public class ScheduledThreadPoolExecutorProviderImpl implements IThreadPoolExecutorProvider {

    private ScheduledExecutorService scheduledExecutorService;

    public ScheduledThreadPoolExecutorProviderImpl() {
        createThreadPool();
    }

    /**
     * 是唯一一个有延迟执行和周期重复执行的线程池。
     * 它的核心线程池固定，非核心线程的数量没有限制，
     * 但是闲置时会立即会被回收。
     */

    @Override
    public void createThreadPool() {
        scheduledExecutorService = Executors.newScheduledThreadPool(5);
    }

    @Override
    public void addThread(IThreadSender iThreadSender) {
        scheduledExecutorService.submit(iThreadSender);
    }

    @Override
    public void addThread(Runnable iThreadSender) {
        scheduledExecutorService.submit(iThreadSender);
    }

    @Override
    public void shutdown() {
        scheduledExecutorService.shutdown();
    }
    /**
     * 延迟后启动，每/秒执行一次
     */
    public void scheduleAtFixedRate(IThreadSender iThreadSender,int initialDelay,int delay){

        scheduledExecutorService.scheduleAtFixedRate(iThreadSender,initialDelay,delay, TimeUnit.MILLISECONDS);
    }

    /**
     * 启动后第一次延迟/秒执行，后面延迟/秒执行
     */
    public void scheduleWithFixedDelay(IThreadSender iThreadSender,int initialDelay,int delay){
        scheduledExecutorService.scheduleWithFixedDelay(iThreadSender,initialDelay,delay, TimeUnit.MILLISECONDS);
    }
}
