package com.iqb.api.net.rx.manager;

import io.reactivex.disposables.Disposable;


/**
 * Describe：
 * - RxJavaAction管理接口
 *
 */
public interface RxActionManager<T> {
    /**
     * 添加
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除
     *
     * @param tags
     */
    void remove(T... tags);

    /**
     * 取消
     *
     * @param tags
     */
    void cancel(T... tags);

}