package com.iqb.api.net.http.request;


import com.iqb.api.net.OnDataCallback;
import com.iqb.api.net.http.callback.IQBCallbackNet;
import com.iqb.been.base.NetworkingRespondEntity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/10.
 * 网络请求
 */

public class HttpExecute {
    private static HttpExecute mHttpExecute;

    private HttpExecute() {

    }

    public static synchronized HttpExecute getHttpExecute() {
        if (mHttpExecute == null) {
            mHttpExecute = new HttpExecute();
        }
        return mHttpExecute;
    }

    /**
     * 执行请求
     */
    public<T> void enqueue(Call<NetworkingRespondEntity> call, final OnDataCallback<T> callback) {
        call.enqueue(new IQBCallbackNet<NetworkingRespondEntity>() {
            @Override
            public void onSucceed(Object response) {
                callback.onSuccess(response);
            }

            @Override
            public void onFailure(String message) {
                callback.onError(message);
            }
        });
    }

    /**
     * 执行请求
     */
    public<T> Object execute(Call<ResponseBody> call, final OnDataCallback<T> callback, Class<T> mClsTo) throws IOException {
        Response<ResponseBody> execute = call.execute();
        return execute.body();
    }
}
