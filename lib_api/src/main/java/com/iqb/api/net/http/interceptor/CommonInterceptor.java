package com.iqb.api.net.http.interceptor;

import android.annotation.SuppressLint;

import com.iqb.api.net.tools.RequestUtils;
import com.iqb.api.utils.SPHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * - okHttp拦截器，用于添加公共参数
 */
public class CommonInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
        Request.Builder builder = oldRequest.newBuilder();


        if (SPHelper.isLogin()) {
            builder.addHeader("Authorization", SPHelper.getAccessToken());

        }


        for (Map.Entry<String, Object> entry : getCommonParams(oldRequest).entrySet()) {
            if (!oldRequest.url().queryParameterNames().contains(entry.getKey())) {
                authorizedUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue().toString());
            } else {
                authorizedUrlBuilder.setQueryParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        HttpUrl httpUrl = authorizedUrlBuilder.build();
        builder.method(oldRequest.method(), oldRequest.body())
                .url(httpUrl);
        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }

    @SuppressLint("NewApi")
    private Map<String, Object> getCommonParams(Request request) {
        HttpUrl url = request.url();
        String path = url.url().getPath();
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        Map<String, Object> queryParams = new HashMap<>();
        Set<String> keys = url.queryParameterNames();
        if (!keys.isEmpty()) {
            for (String key : keys) {
                queryParams.put(key, Objects.requireNonNull(url.queryParameter(key)));
            }
        }
//        if (SPHelper.isLogin()) {
//            queryParams.put("token", SPHelper.getAccessToken());
//        }

        return RequestUtils.getQueryParams(path, queryParams);
    }
}
