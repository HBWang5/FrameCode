package com.iqb.api.net.rx;


import com.iqb.api.net.http.interceptor.CommonInterceptor;

import okhttp3.OkHttpClient;

/**
 * retrofit请求管理
 */
public class RestApi extends RetrofitClient {

    public static synchronized RestApi getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    protected void handlerBuilder(OkHttpClient.Builder builder) {
        //公共参数请求拦截请
        builder.addInterceptor(new CommonInterceptor());
    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final RestApi INSTANCE = new RestApi();

        private SingletonHolder() {
            throw new UnsupportedOperationException("u can't instantiate SingletonHolder...");
        }
    }


}
