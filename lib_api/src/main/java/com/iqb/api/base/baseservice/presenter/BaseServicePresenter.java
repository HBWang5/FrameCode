package com.iqb.api.base.baseservice.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.iqb.api.base.baseservice.view.IServiceFrame;
import com.iqb.api.base.baseservice.model.BaseServiceModel;
import com.iqb.api.utils.ToastUtils;

public abstract class BaseServicePresenter<M extends BaseServiceModel, V extends IServiceFrame> extends PresenterServiceCenter<V> {

    private Context context;

    private M model;

    public BaseServicePresenter(Context context) {
        this.context = context;
        getModel();
    }

    public Context getContext() {
        return context;
    }

    public boolean isHTTPOk(boolean s, String m) {
        String replace = getError(m);
        if (!s) {
            if (TextUtils.isEmpty(replace)) {
                ToastUtils.showShort(replace);
            }
            return true;
        }
        return false;
    }

    public String getError(String m) {
        return m.toLowerCase()
                    .replace("a", "")
                    .replace("b", "")
                    .replace("c", "")
                    .replace("d", "")
                    .replace("e", "")
                    .replace("f", "")
                    .replace("g", "")
                    .replace("h", "")
                    .replace("i", "")
                    .replace("j", "")
                    .replace("k", "")
                    .replace("l", "")
                    .replace("m", "")
                    .replace("n", "")
                    .replace("o", "")
                    .replace("p", "")
                    .replace("q", "")
                    .replace("r", "")
                    .replace("s", "")
                    .replace("t", "")
                    .replace("u", "")
                    .replace("v", "")
                    .replace("w", "")
                    .replace("x", "")
                    .replace("y", "")
                    .replace("z", "")
                    .replace(".", "");
    }

    public M getModel() {
        if (this.model == null) {
            this.model = bindModel();
        }
        return this.model;
    }

    public abstract M bindModel();

}
