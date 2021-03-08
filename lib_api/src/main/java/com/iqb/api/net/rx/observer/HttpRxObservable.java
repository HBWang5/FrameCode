package com.iqb.api.net.rx.observer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.iqb.api.net.rx.exception.ExceptionFunc;
import com.iqb.api.net.rx.response.ResponseFunc;
import com.iqb.api.net.rx.exception.RetryWhenNetworkException;
import com.iqb.api.net.rx.TransformUtils;
import com.iqb.been.base.HttpResponseBean;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import io.reactivex.Observable;

public class HttpRxObservable {

    private HttpRxObservable() {

    }

    /**
     * 获取被监听者
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 无管理生命周期,容易导致内存溢出
     */
    public static <T extends HttpResponseBean> Observable<T> getObservable(Observable<T> apiObservable) {
        return apiObservable.map(new ResponseFunc<>())
                .onErrorResumeNext(new ExceptionFunc<>())
                .retryWhen(new RetryWhenNetworkException())
                .compose(TransformUtils.ioToMain());
    }

    /**
     * 获取被监听者LifecycleOwner
     * 备注:网络请求Observable构建
     * data:网络请求参数
     * <h1>补充说明</h1>
     * 传入lifecycleOwner手动管理生命周期,避免内存溢出
     * 备注:需要继承RxActivity,RxAppCompatActivity,RxFragmentActivity
     */
    public static <T extends HttpResponseBean> ObservableSubscribeProxy<T> getObservable(Observable<T> apiObservable,
                                                                                         LifecycleOwner lifecycleOwner) {
        return apiObservable
                .map(new ResponseFunc<>())
                .onErrorResumeNext(new ExceptionFunc<>())
                .retryWhen(new RetryWhenNetworkException())
                .compose(TransformUtils.ioToMain())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, Lifecycle.Event.ON_DESTROY)));
    }


    /**
     * 获取被监听者LifecycleOwner
     * untilEvent 取消请求的生命周期
     */
    public static <T extends HttpResponseBean> ObservableSubscribeProxy<T> getObservable(Observable<T> apiObservable,
                                                                                         LifecycleOwner lifecycleOwner,
                                                                                         Lifecycle.Event untilEvent) {
        return apiObservable
                .map(new ResponseFunc<>())
                .onErrorResumeNext(new ExceptionFunc<>())
                .retryWhen(new RetryWhenNetworkException())
                .compose(TransformUtils.ioToMain())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, untilEvent)));
    }


}
