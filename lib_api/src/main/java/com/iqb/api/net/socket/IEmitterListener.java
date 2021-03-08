package com.iqb.api.net.socket;

public interface IEmitterListener {

    /**
     * 监听结果
     *
     * @param key
     * @param args
     */
    void emitterListenerResult(String key, Object... args);

//    /**
//     * 请求结果
//     *
//     * @param key
//     * @param args
//     */
//    void requestSocketResult(String key, Object... args);

}