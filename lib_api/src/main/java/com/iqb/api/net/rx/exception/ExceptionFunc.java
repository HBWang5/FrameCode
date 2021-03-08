package com.iqb.api.net.rx.exception;



import com.iqb.api.net.http.exception.FactoryException;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Describe：
 * - 异常处理
 *
 */
public class ExceptionFunc<T> implements Function<Throwable, Observable<T>> {

    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        return Observable.error(FactoryException.analysisException(throwable));
    }
}