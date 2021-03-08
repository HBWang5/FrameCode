package com.iqb.navigation.base.presenter;

import android.text.TextUtils;

import androidx.annotation.StringRes;

import com.iqb.api.base.model.BaseModel;
import com.iqb.api.base.presenter.BasePresenter;
import com.iqb.api.utils.ToastUtils;
import com.iqb.navigation.base.view.BaseNavigationView;

public abstract class BaseNavigationPresenter<T extends BaseNavigationView> implements BasePresenter {
    protected T view;

    private BaseModel model;

    protected BaseNavigationPresenter(T view) {
        this.view = view;
        model = bindModel();
    }

    public BaseModel getModel() {
        return model;
    }

    protected abstract BaseModel bindModel();

    protected void showToast(String message) {
        view.showToast(message);
    }

    protected void showToast(@StringRes int resId) {
        view.showToast(resId);
    }

    @Override
    public final void destroy() {
        onDestroy();
        view = null;
        model = null;
    }

    public void onDestroy() {

    }

    protected String getString(@StringRes int id) {
        return view.getString(id);
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

}