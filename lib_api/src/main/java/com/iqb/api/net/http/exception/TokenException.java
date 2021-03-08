package com.iqb.api.net.http.exception;


/**
 * 描述：
 * -回调统一请求异常
 */
public class TokenException extends RuntimeException {
    private final String code;
    private String desc;

    public TokenException(String code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public TokenException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}