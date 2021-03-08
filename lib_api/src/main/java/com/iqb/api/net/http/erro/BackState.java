package com.iqb.api.net.http.erro;



import com.iqb.api.net.http.callback.IQBCallbackNet;
import com.iqb.api.net.http.erro.filtration.ResponseCodeUtil;

/**
 * Created by Administrator on 2017/7/12.
 * 返回类型处理业务类
 */

public class BackState<T> implements IBackState<T> {
    private IQBCallbackNet mIQBCallbackNet;

    public BackState(IQBCallbackNet mIQBCallbackNet) {
        this.mIQBCallbackNet = mIQBCallbackNet;
    }

    /**
     * 返回类型处理
     */
    @Override
    public void responseCode(int code) {
        ResponseCodeUtil.getResponseCodeUtil().codeManage(code, mIQBCallbackNet);
    }

    /**
     * 返回类型处理
     */
    @Override
    public void responseErrorCode(int errorCode, String errorMessage) {
        if (errorCode != 0) {
            mIQBCallbackNet.onFailure(errorMessage);
        }
    }
}
