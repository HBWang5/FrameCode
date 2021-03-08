package com.iqb.api.base.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iqb.api.base.app.ApiApplication;
import com.iqb.api.base.model.manager.DataManager;
import com.iqb.api.base.view.activity.BaseActivity;
import com.iqb.api.dagger.component.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    private View contentView;
    BaseActivity mActivity;
    private Unbinder unbinder;
    private DataManager mDataManager;

    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(bindLayoutId(), container, false);
        return contentView;
    }

    public abstract int bindLayoutId();

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataManager = getAppComponent().getDataManager();
        unbinder = ButterKnife.bind(this, view);
        initBeforeViewCreated();
    }

    protected AppComponent getAppComponent() {
        return ApiApplication.getApp().getAppComponent();
    }

    /**
     * 这里不推荐在view层做数据层操作
     */
    public DataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView(contentView);
        initOnClicked();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected abstract void initOnClicked();

    protected abstract void initView(View contentView);

    protected abstract void initData();

    protected void initBeforeViewCreated() {

    }
}
