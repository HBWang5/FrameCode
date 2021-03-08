package com.iqb.api.net.http.exception;


/**
 * 描述：
 * -回调统一请求异常
 */
public class ServerException extends RuntimeException {
    private final String code;
    private String extra;

    public ServerException(String code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public ServerException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}