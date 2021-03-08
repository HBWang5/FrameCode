package com.iqb.api.net.rx;


import com.iqb.api.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iqb.api.net.http.factory.NullStringToEmptyAdapterFactory;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public abstract class RetrofitClient {

    private OkHttpClient okHttpClient;
    private static final int TIME_OUT = 15;
    private Gson gson;

    RetrofitClient() {
        okHttpClient = createOkHttpClient();
        gson = new GsonBuilder().registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        builder.addInterceptor(logging)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .proxy(Proxy.NO_PROXY);
        handlerBuilder(builder);
        return builder.build();
    }

    /**
     * 用于OkHttpClient扩展
     */
    protected abstract void handlerBuilder(OkHttpClient.Builder builder);

    public <T> T create(Class<T> clz) {
        return createApiClient().create(clz);
    }

    /**
     * 创建一个okhttp实例
     *
     * @return Retrofit
     */
    private Retrofit createApiClient() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
