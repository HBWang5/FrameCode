package com.iqb.api.net.http.exception;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ApiExceptionCode {

    /*网络错误*/
    public static final String CONNECT_ERROR = "net.01";
    /*http_错误*/
    public static final String HTTP_ERROR = "net.02";
    /*fastjson错误*/
    public static final String PARSE_ERROR = "net.03";
    /*请求超时*/
    public static final String TIME_OUT_ERROR = "net.04";
    /*未知错误*/
    public static final String UNKNOWN_ERROR = "net.05";
    /*运行时异常-包含自定义异常*/
    public static final String RUNTIME_ERROR = "net.06";
    /*无法解析该域名*/
    public static final String UNKNOWN_HOST_ERROR = "net.07";


    @StringDef({CONNECT_ERROR, HTTP_ERROR, RUNTIME_ERROR, UNKNOWN_ERROR, PARSE_ERROR, UNKNOWN_HOST_ERROR,
            TIME_OUT_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CodeCons {
    }

}