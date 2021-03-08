package com.iqb.api.net.http.erro.filtration;


import com.iqb.api.net.http.callback.IQBCallbackNet;
import com.iqb.api.net.http.config.ErrorConfig;

/**
 * ----------Dragon be here!----------/
 * Created by HBWang on 2019/8/12-15:25
 */
class ErrorDataFiltration {

    private static ErrorDataFiltration mErrorDataFiltration;

    private ErrorDataFiltration() {
    }

    static ErrorDataFiltration getInstance() {
        if (mErrorDataFiltration == null) {
            mErrorDataFiltration = new ErrorDataFiltration();
        }
        return mErrorDataFiltration;
    }

    void produce(IQBCallbackNet mIQBCallbackNet, int type) {

        if (type == ErrorConfig.SUCCEED) {
        } else {
            mIQBCallbackNet.onFailure("请检查网络");
        }
    }
}
