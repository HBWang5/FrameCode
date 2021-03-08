package com.iqb.api.ThreadPoolExecutor.Factory;

import com.iqb.api.ThreadPoolExecutor.Proxy.IThreadPoolExecutorProxy;

/**
 * Created by Administrator on 2018/7/4.
 */

public interface IThreadPoolExecutorFactory {
    public IThreadPoolExecutorProxy produce(int type) throws Exception;
}
