package com.iqb.api.net.rx.response;


import com.iqb.api.net.http.exception.ServerException;
import com.iqb.been.base.HttpResponseBean;

import io.reactivex.functions.Function;


/**
 * - 通用请求返回结果处理
 */
public class ResponseFunc<T extends HttpResponseBean> implements Function<T, T> {
    @Override
    public T apply(T httpResult) {

        if (httpResult.isS()) {
            return httpResult;
        } else {
            throw new ServerException(httpResult.getM(), httpResult.getM());
        }

    }

}