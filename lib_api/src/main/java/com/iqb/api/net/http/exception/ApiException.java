package com.iqb.api.net.http.exception;

public class ApiException extends Exception {
    private final String code;

    public ApiException(Throwable throwable, String code) {
        super(throwable);
        this.code = code;
    }

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}