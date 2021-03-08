package com.iqb.api.net.rx.exception;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;


/**
 * Describe：
 * - 请求异常自动重新请求
 *
 */
public class RetryWhenNetworkException implements Function<Observable<? extends Throwable>, Observable<?>> {
    /**
     * //retry count
     */
    private int count = 3;
    /**
     * //delay time
     */
    private long delay = 3000;

    public RetryWhenNetworkException() {

    }

    public RetryWhenNetworkException(int count) {
        this.count = count;
    }

    public RetryWhenNetworkException(int count, long delay) {
        this.count = count;
        this.delay = delay;
    }

    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        return observable
                .zipWith(Observable.range(1, count + 1), new BiFunction<Throwable, Integer, Wrapper>() {

                    @Override
                    public Wrapper apply(Throwable throwable, Integer integer) throws Exception {
                        return new Wrapper(throwable, integer);
                    }
                }).flatMap((Function<Wrapper, ObservableSource<?>>) wrapper -> {
                    boolean isRetryException = wrapper.throwable instanceof ConnectException
                            || wrapper.throwable instanceof SocketTimeoutException
                            || wrapper.throwable instanceof TimeoutException;
                    if (isRetryException && wrapper.index < count + 1) {
                        return Observable.timer(delay + (wrapper.index - 1) * delay, TimeUnit.MILLISECONDS);
                    }
                    return Observable.error(wrapper.throwable);
                });
    }

    private class Wrapper {
        private int index;
        private Throwable throwable;

        Wrapper(Throwable throwable, int index) {
            this.index = index;
            this.throwable = throwable;
        }
    }
}