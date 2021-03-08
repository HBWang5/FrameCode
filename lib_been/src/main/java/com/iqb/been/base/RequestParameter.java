package com.iqb.been.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * -
 */
public class RequestParameter {
    private Map<String, String> headerParams;
    private Map<String, Object> queryParams;
    private Map<String, Object> bodyParams;
    private Map<String, Object> formParams;

    public RequestParameter() {
        headerParams = new HashMap<>();
        queryParams = new HashMap<>();
        bodyParams = new HashMap<>();
        formParams = new HashMap<>();
    }

    public RequestParameter addHeader(String key, String value) {
        headerParams.put(key, value);
        return this;
    }

    public RequestParameter addQueryParameter(String key, Object value) {
        queryParams.put(key, value);
        return this;
    }

    public RequestParameter addBodyParameter(String key, Object value) {
        bodyParams.put(key, value);
        return this;
    }

    public RequestParameter addFormParameter(String key, Object value) {
        formParams.put(key, value);
        return this;
    }

    public Map<String, String> getHeaderParams() {
        return headerParams;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public Map<String, Object> getBodyParams() {
        return bodyParams;
    }

    public Map<String, Object> getFormParams() {
        return formParams;
    }

}
