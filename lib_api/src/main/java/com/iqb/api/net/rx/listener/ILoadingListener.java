package com.iqb.api.net.rx.listener;

public interface ILoadingListener {
    /**
     * 显示默认loading
     */
    default void showLoading() {

    }


    /**
     * 显示loading
     *
     * @param message 消息内容
     */
    default void showLoading(String message) {

    }

    /**
     * 取消loading
     */
    default void cancelLoading() {

    }

    /**
     * 隐藏loading
     */
    default void dismissLoading() {

    }
}
