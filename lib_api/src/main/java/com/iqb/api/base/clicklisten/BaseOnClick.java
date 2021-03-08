package com.iqb.api.base.clicklisten;

import android.view.View;


public abstract class BaseOnClick<BasePresenter> implements View.OnClickListener {

    private BasePresenter mPresenter;

    public BasePresenter getPresenter() {
        return mPresenter;
    }

    public void initPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {

    }
}
