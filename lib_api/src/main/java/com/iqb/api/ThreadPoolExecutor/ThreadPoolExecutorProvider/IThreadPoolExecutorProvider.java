package com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorProvider;

import com.iqb.api.ThreadPoolExecutor.ThreadPoolExecutorMsgSender.IThreadSender;

/**
 * Created by Administrator on 2018/6/28.
 */

public interface IThreadPoolExecutorProvider {
    void createThreadPool();
    void addThread(IThreadSender iThreadSender);
    void addThread(Runnable iThreadSender);
    void shutdown();
}
