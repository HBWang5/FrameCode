package com.iqb.api.net.rx.observer;

import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.iqb.api.net.http.exception.ApiException;
import com.iqb.api.net.http.exception.FactoryException;
import com.iqb.api.net.http.exception.ServerException;
import com.iqb.api.net.rx.listener.ILoadingListener;
import com.iqb.api.net.rx.manager.RxActionManagerImpl;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * 描述：
 * -  适用Retrofit网络请求Observer(监听者)
 * 备注:
 * 1.重写onSubscribe，添加请求标识
 * 2.重写onError，封装错误/异常处理，移除请求
 * 3.重写onNext，移除请求
 * 4.重写cancel，取消请求
 */
public abstract class HttpRxObserver<T> implements Observer<T> {

    /**
     * 请求标识
     */
    private String mTag;

    /**
     * 是否显示loading
     */
    private boolean isShowProgress;

    private ILoadingListener loadingListener;


    /**
     * @param tag      请求唯一标识，便于销毁Disposable
     * @param listener loading状态监听
     */
    protected HttpRxObserver(@NonNull String tag, ILoadingListener listener) {
        this(tag, true, listener);
    }

    /**
     * @param tag            请求唯一标识，便于销毁Disposable
     * @param isShowProgress 是否显示加载框
     * @param listener       loading状态监听
     */
    private HttpRxObserver(String tag, boolean isShowProgress, ILoadingListener listener) {
        this.mTag = tag;
        this.isShowProgress = isShowProgress;
        this.loadingListener = listener;
    }


    @Override
    public void onError(Throwable e) {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().remove(mTag);
        }
        if (isShowProgress && loadingListener != null) {
            loadingListener.dismissLoading();
        }
        if (e instanceof ApiException) {
            onFail((Exception) e);
        } else if (e instanceof ServerException) {
            onFail((ServerException) e);
        } else {
            onFail(FactoryException.analysisException(e));
        }
    }

    @Override
    public void onComplete() {
        if (isShowProgress && loadingListener != null) {
            loadingListener.dismissLoading();
        }
    }

    @Override
    public void onNext(@NonNull T t) {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().remove(mTag);
        }
        onSuccess(t);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManagerImpl.getInstance().add(mTag, d);
        }
        if (isShowProgress && loadingListener != null) {
            loadingListener.showLoading();
        }
    }

    /**
     * 是否已经处理
     */
    public boolean isDisposed() {
        return TextUtils.isEmpty(mTag) || RxActionManagerImpl.getInstance().isDisposed(mTag);
    }

    /**
     * 错误/异常回调
     */
    protected abstract void onFail(Exception e);

    /**
     * 成功回调
     */
    protected abstract void onSuccess(T response);


    public String getTag() {
        return mTag;
    }


}