package com.iqb.api.net.http.interceptor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHeaderContextInterceptor implements Interceptor {

    //初始化Map集合
    Map<String, String> mParentMap;

    public RequestHeaderContextInterceptor(Map<String, String> parentMap) {
        mParentMap = parentMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //拿到request
        Request requestFu = chain.request();
        //用request获取网址
        String url = requestFu.url().toString();
        //判断集合是否非空
        if (mParentMap != null && mParentMap.size() > 0) {
            Request.Builder builder = requestFu.newBuilder();
            for (String user : mParentMap.keySet()) {
                //添加头部参数
                builder.addHeader(user, mParentMap.get(user));
            }

            Request build = builder.url(url).build();
            return chain.proceed(build);
        }
        return chain.proceed(requestFu);
    }
}
