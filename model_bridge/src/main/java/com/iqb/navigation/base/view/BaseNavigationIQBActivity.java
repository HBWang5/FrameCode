package com.iqb.navigation.base.view;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.WindowManager;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.iqb.api.base.view.activity.BaseIQBActivity;
import com.iqb.api.utils.DialogUtils;
import com.iqb.api.utils.StatusBarUtil;
import com.iqb.src.widget.dialog.MProgressDialog;

public abstract class BaseNavigationIQBActivity extends BaseIQBActivity implements BaseNavigationView {

    @NonNull
    @Override
    public Activity getActivity() {
        return this;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //设置屏幕常亮
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        StatusBarUtil.setColor(this, Color.WHITE, 40);
    }

    @CallSuper
    @Override
    public void injectComponent() {
        BaseNavigationView.super.injectComponent();
    }

    @CallSuper
    @Override
    protected void initBeforeViewCreated() {
        super.initBeforeViewCreated();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        BaseNavigationView.super.onDestroy();
        super.onDestroy();
    }

    private MProgressDialog loadingDialog;

    @Override
    public void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = new MProgressDialog(this);
        }
        DialogUtils.safeShowDialog(loadingDialog);
    }

    @Override
    public void showLoading(String message) {
        if (loadingDialog == null) {
            loadingDialog = new MProgressDialog(this);
        }
        DialogUtils.safeShowDialog(loadingDialog);
    }

    @Override
    public void cancelLoading() {
    }

    @Override
    public void dismissLoading() {
        DialogUtils.safeCloseDialog(loadingDialog);
        loadingDialog = null;
    }
}
