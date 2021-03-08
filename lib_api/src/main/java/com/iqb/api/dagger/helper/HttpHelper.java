package com.iqb.api.dagger.helper;


import com.iqb.api.net.rx.RestApi;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * http helper负责创建ApiService实例
 */
@Singleton
public class HttpHelper {
    public <T> T createApi(Class<T> serviceClass) {
        return RestApi.getInstance().create(serviceClass);
    }

    @Inject
    HttpHelper() {}
}
