package com.iqb.api.net.http.exception;



import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;


/**
 * 描述：
 * -异常处理工厂
 * 主要是解析异常，输出自定义ApiException
 * 创建时间：2017/6/6
 */

public class FactoryException {
    private static final String HTTP_EXCEPTION_MSG = "网络异常，请稍后重试";
    private static final String CONNECT_EXCEPTION_MSG = "网络无连接，请检查网络后重试";
    private static final String JSON_EXCEPTION_MSG = "数据解析失败";
    private static final String UNKNOWN_EXCEPTION_MSG = "请求失败，请稍后重试";
    private static final String TIME_OUT_MSG = "请求超时，请稍后重试";

    private FactoryException() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 解析异常
     *
     * @param e
     * @return
     */
    public static Exception analysisException(Throwable e) {
        if (e.getMessage().contains("Unauthorized")) {
//            ARouter.getInstance().build(RouteActivityLoginURL.LOGIN_ACTIVITY).navigation_icon();
        }
        if (e instanceof HttpException) {
            /*网络异常*/
            return new ApiException(ApiExceptionCode.HTTP_ERROR, HTTP_EXCEPTION_MSG);
        } else if (e instanceof ConnectException) {
            /*链接异常*/
            return new ApiException(ApiExceptionCode.CONNECT_ERROR, UNKNOWN_EXCEPTION_MSG);
        } else if (e instanceof SocketTimeoutException || e instanceof TimeoutException) {
            /*请求超时异常*/
            return new ApiException(ApiExceptionCode.TIME_OUT_ERROR, TIME_OUT_MSG);
        } else if (e instanceof JsonParseException || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            return new ApiException(ApiExceptionCode.PARSE_ERROR, JSON_EXCEPTION_MSG);
        } else if (e instanceof UnknownHostException) {
            /*无法解析该域名异常*/
            return new ApiException(ApiExceptionCode.UNKNOWN_HOST_ERROR, CONNECT_EXCEPTION_MSG);
        } else if (e instanceof ServerException) {
            return (ServerException) e;
        } else if (e instanceof TokenException) {
            return (TokenException) e;
        } else {
            /*未知异常*/
            return new ApiException(ApiExceptionCode.UNKNOWN_ERROR, UNKNOWN_EXCEPTION_MSG);
        }
    }
}