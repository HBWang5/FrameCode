package com.iqb.api.net.rx;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述：
 * -处理Rx线程
 */
public class TransformUtils {

    public static <T> ObservableTransformer<T, T> ioToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> allIo() {
        return upstream -> upstream.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
    }
}
